package br.com.liferay.expression.evaluator;

import java.util.HashMap;
import java.util.Map;

import br.com.liferay.expression.evaluator.function.Function;

/**
 * @author Leonardo Barros
 */
public class ExpressionBuilder {

	public ExpressionBuilder expression(String expression) {
		this.expression = expression;
		return this;
	}
	
	public ExpressionBuilder functions(Map<String,Function> functions) {
		if(functions != null) {
			this.functions.putAll(functions);	
		}
		return this;
	}
	
	public ExpressionBuilder variables(Map<String,Object> variables) {
		if(variables != null) {
			this.variables.putAll(variables);
		}
		return this;
	}
	
	public Expression buildExpression() {
		return new Expression(expression, functions, variables);
	}
	
	private String expression;
	private Map<String,Object> variables = new HashMap<>();
	private Map<String,Function> functions = new HashMap<>();
}
