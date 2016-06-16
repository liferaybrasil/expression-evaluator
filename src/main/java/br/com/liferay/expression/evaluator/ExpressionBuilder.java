package br.com.liferay.expression.evaluator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leonardo Barros
 */
public class ExpressionBuilder {

	public ExpressionBuilder expression(String expression) {
		this.expression = expression;
		return this;
	}
	
	public ExpressionBuilder variables(Map<String, Object> variables) {
		if(variables != null) {
			this.variables.putAll(variables);
		}
		return this;
	}
	
	public Expression buildExpression() {
		return new Expression(expression, variables);
	}
	
	private String expression;
	private Map<String, Object> variables = new HashMap<>();
}
