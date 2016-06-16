package br.com.liferay.expression.evaluator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import br.com.liferay.expression.evaluator.operand.Operand;
import br.com.liferay.expression.evaluator.operator.FunctionOperator;
import br.com.liferay.expression.evaluator.operator.Operator;
import br.com.liferay.expression.evaluator.operator.ParenthesisOperator;

/**
 * @author Leonardo Barros
 */
public class Expression {

	public Expression(String expression) {
		this.expression = expression;
		
		if(StringUtils.isBlank(expression)) {
			throw new IllegalArgumentException(
				"An expression can't be null or empty.");
		}
	}
	
	public Object evaluate() throws ExpressionException {
		try {
			for(int i = 0; i < expression.length(); i++) {
				char character = expression.charAt(i);
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
	
	protected String extractFunctionName() {
		String str = currentToken.toString();
		
		for(String func : SUPPORTED_FUNCTIONS) {
			if(str.contains(func)) {
				currentToken.delete(0, currentToken.length());
				if(str.indexOf('.') != -1) {
					currentToken.append(str.substring(0, str.indexOf('.')));
					addOperand();
				}
				return func;
			}
		}
		
		currentToken.delete(0, currentToken.length());
		
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
			evaluate(operators.pop());
		}
	}
	
	protected void processOpeningParenthesis() throws ExpressionException {
		if(currentToken.length() > 0) {
			String foundFunction = extractFunctionName();
			
			if(foundFunction != null) {
				addOperator(foundFunction);
			}
			else if(lastChar != '-') {
				throw new ExpressionException("Operator '(' not expected.");
			}
		}
		addOperator("(");
	}
	
	protected void processCharacter(char character) throws ExpressionException {
		if(character == ' ') {
			return;
		}
		
		if(CharUtils.isAsciiAlphanumeric(character) || character == '.') {
			addCharacter(character);
		}
		else if(character == '=' && lastChar == '>') {
			operators.pop();
			addOperator(">=");
		}
		else if(character == '=' && lastChar == '<') {
			operators.pop();
			addOperator("<=");
		}
		else if(character == '&' && lastChar == '&') {
			processOperator("&&");
		}
		else if(character == '+' || character == '-' || character == '*' || character == '/' || character == '%' || character == '>' || character == '<') {
			processOperator(String.valueOf(character));
		}
		else if(lastChar == '&' && character != '&') {
			throw new ExpressionException("The conditional operator is '&&'");
		}
		else if(character == '=' || character == '&' || character == '|') {
			lastChar = character;
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

	private static final String[] SUPPORTED_FUNCTIONS = new String[] {};
	private static final int TOKEN_OPERATOR = 1;
	private static final int TOKEN_OPERAND = 2;
	private StringBuilder currentToken = new StringBuilder();
	private String expression;
	private char lastChar = '\0';
	private int lastToken = 0;
	private Stack<Operand> operands = new Stack<>();
	private Stack<Operator> operators = new Stack<>();
	private Map<String, Object> variables = new HashMap<>();
}
