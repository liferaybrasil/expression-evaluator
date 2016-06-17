package br.com.liferay.expression.evaluator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import br.com.liferay.expression.evaluator.function.BinaryFunction;
import br.com.liferay.expression.evaluator.function.Function;
import br.com.liferay.expression.evaluator.function.UnaryFunction;
import br.com.liferay.expression.evaluator.operand.Operand;
import br.com.liferay.expression.evaluator.operator.FunctionOperator;
import br.com.liferay.expression.evaluator.operator.Operator;
import br.com.liferay.expression.evaluator.operator.ParenthesisOperator;

/**
 * @author Leonardo Barros
 */
public class Expression {

	Expression(String expression, Map<String,Function> functions, Map<String,Object> variables) {
		this.expression = expression;
		
		if(functions != null) {
			this.functions.putAll(functions);
		}
		
		if(variables != null) {
			this.variables.putAll(variables);
		}
		
		if(StringUtils.isBlank(expression)) {
			throw new IllegalArgumentException(
				"An expression can't be null or empty.");
		}
	}
	
	public Object evaluate() throws ExpressionException {
		try {
			for(int i = 0; i < expression.length(); i++) {
				char character = expression.charAt(i);
				if(character == '\"') {
					i = processString(i, expression);
					continue;
				}
				processCharacter(character);
			}
			
			addOperand();
			
			while(!operators.isEmpty()) {
				Operator operator = operators.pop();
				
				evaluate(operator);
			}
			
			return operands.pop().getValue();
		
		}
		catch(Exception ex) {
			throw new ExpressionException("Invalid expression", ex);
		}
	}
	
	protected void addOperand() {
		if(currentToken.length() == 0) {
			return;
		}
		
		operands.push(createOperand(currentToken.toString()));
		
		lastToken = TOKEN_OPERAND;
		
		if(operators.size() > 1) {
			verifyOperatorPrecedence();
		}
		
		currentToken.delete(0, currentToken.length());
	}
	
	protected void addFunction(String functionName) {
		Operator newFunction = new FunctionOperator(functionName);
		
		if(!operators.isEmpty()) {
			if(operators.peek().compareTo(newFunction) >= 0) {
				evaluate(operators.pop());
			}
		}
		
		operators.push(newFunction);
		
		lastToken = TOKEN_OPERATOR;
	}
	
	protected void addOperator(String operator) {
		Operator newOperator = Operator.create(operator);
		
		if(!operator.equals("(") && !operators.isEmpty()) {
			if(operators.peek().compareTo(newOperator) >= 0) {
				evaluate(operators.pop());
			}
		}
		
		operators.push(newOperator);
		
		lastToken = TOKEN_OPERATOR;
	}
	
	protected void addCharacter(char token) {
		currentToken.append(token);
		String value = currentToken.toString();
		if(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
			addOperand();
		}
	}
	
	protected Operand createOperand(String operandText) {
		if(variables.containsKey(operandText)) {
			return Operand.create(variables.get(operandText).toString());
		}
		
		return Operand.create(operandText);
	}
	
	protected void evaluate(Operator operator) {
		Operand operand = null;
		if(operator.isBinary()) {
			operand = operator.evaluate(operands.pop(), operands.pop());
		}
		else {
			operand = operator.evaluate(operands.pop());
		}
		operands.push(operand);	
	}
	
	protected void evaluateFuntion(Operator operator) {
		Operand operand = null;
		
		Function function = functions.get(operator.getText());
		
		if(function instanceof UnaryFunction) {
			operand = operator.evaluate(operands.pop());
		}
		else if(function instanceof BinaryFunction) {
			operand = executeBinaryFunction(function);
		}
		else {
			operand = operator.evaluate(operands.pop(), operands.pop(), operands.pop());
		}
		operands.push(operand);
	}
	
	protected Operand executeBinaryFunction(Function function) {
		Object value2 = operands.pop().getValue();
		Object value1 = operands.pop().getValue();
		if(variables.containsKey(value1.toString())) {
			value1 = variables.get(value1.toString());
		}
		if(variables.containsKey(value2.toString())) {
			value2 = variables.get(value2.toString());
		}
		Object result = ((BinaryFunction)function).evaluate(value1, value2);
		Operand operand = null;
		if(result != null) {
			operand = Operand.create(result.toString());
		}
		else {
			operand = Operand.create("");
		}
		return operand;
	}
	
	protected String extractFunctionName() {
		String str = currentToken.toString();
		
		if(functions.containsKey(str)) {
			currentToken.delete(0, currentToken.length());
			return str;
		}
		
		return null;
	}
	
	protected void processClosingParenthesis() throws ExpressionException {
		addOperand();
		
		while(!(operators.peek() instanceof ParenthesisOperator)) {
			
			evaluate(operators.pop());
			
			if(operators.isEmpty()) {
				throw new ExpressionException("It wasn't found an opening parenthesis");
			}
		}
		
		operators.pop();
		
		if(!operators.isEmpty() && operators.peek() instanceof FunctionOperator) {
			evaluateFuntion(operators.pop());
		}
	}
	
	protected void processOpeningParenthesis() throws ExpressionException {
		if(currentToken.length() > 0) {
			String foundFunction = extractFunctionName();
			
			if(foundFunction != null) {
				addFunction(foundFunction);
			}
			else if(lastChar != '-') {
				throw new ExpressionException("Operator '(' not expected.");
			}
		}
		addOperator("(");
	}
	
	protected int processString(int currendIndex, CharSequence expression) throws ExpressionException {
		int nextIndex = StringUtils.indexOf(expression, '\"', currendIndex);
		
		if(nextIndex == -1) {
			throw new ExpressionException("An invalid String constant was found.");
		}
		
		String stringOperand = StringUtils.substring(expression.toString(), currendIndex + 1, nextIndex);
		
		currentToken.append(stringOperand);
		
		return nextIndex;
	}
	
	protected void processCharacter(char character) throws ExpressionException {
		if(character == ' ') {
			return;
		}
		
		if(CharUtils.isAsciiAlphanumeric(character) || character == '.') {
			addCharacter(character);
		}
		else if(character == ',') {
			if(operators.peek() instanceof ParenthesisOperator) {
				addOperand();
			}
		}
		else if(character == '=' && lastChar == '>') {
			operators.pop();
			addOperator(">=");
		}
		else if(character == '=' && lastChar == '<') {
			operators.pop();
			addOperator("<=");
		}
		else if(character == '=' && lastChar == '=') {
			processOperator("==");
		}
		else if(character == '=' && lastChar == '!') {
			operators.pop();
			addOperator("!=");
		}
		else if(character == '&' && lastChar == '&') {
			processOperator("&&");
		}
		else if(character == '|' && lastChar == '|') {
			processOperator("||");
		}
		else if(lastChar == '&' && character != '&') {
			throw new ExpressionException("The conditional operator is '&&'");
		}
		else if(lastChar == '|' && character != '|') {
			throw new ExpressionException("The conditional operator is '||'");
		}
		else if(character == '=' || character == '&' || character == '|') {
			lastChar = character;
		}
		else if(character == '!') {
			addOperand();
			addOperator("!");
		}
		else if(character == '+' || character == '-' || character == '*' || character == '/' || character == '%' || character == '>' || character == '<') {
			processOperator(String.valueOf(character));
		}
		else if(character == '(') {
			processOpeningParenthesis();
		}
		else if(character == ')') {
			processClosingParenthesis();
		}
		else {
			throw new ExpressionException("Invalid token found: " + character);
		}
		
		lastChar = character;
	}
	
	protected void processOperator(String operator) throws ExpressionException {
		addOperand();
		
		if(operator.equals("-")) {
			if(lastToken == TOKEN_OPERATOR) {
				currentToken.append("-");
				return;
			}
		}
		addOperator(operator);
	}
	
	protected void verifyOperatorPrecedence() {
		if(operators.peek() instanceof ParenthesisOperator) {
			return;
		}
		
		Operator lastOperator = operators.pop();
		if(!(operators.peek() instanceof ParenthesisOperator) 
				&& lastOperator.compareTo(operators.peek()) > 0) {
			evaluate(lastOperator);
		}
		else {
			operators.push(lastOperator);
		}
	}

	private static final int TOKEN_OPERATOR = 1;
	private static final int TOKEN_OPERAND = 2;
	private StringBuilder currentToken = new StringBuilder();
	private String expression;
	private char lastChar = '\0';
	private int lastToken = 0;
	private Stack<Operand> operands = new Stack<>();
	private Stack<Operator> operators = new Stack<>();
	private Map<String,Object> variables = new HashMap<>();
	private Map<String,Function> functions = new HashMap<>();
}
