package br.com.liferay.expression.evaluator;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Leonardo Barros
 */
public class ExpressionBuilder {

	public ExpressionBuilder expression(String expression) {
		this.expression = expression;
		return this;
	}
	
	public ExpressionBuilder variables(Set<String> variables) {
		if(variables != null) {
			variables.addAll(variables);
		}
		return this;
	}
	
	public Expression buildExpression() {
		return new Expression(expression, variables);
	}
	
	private String expression;
	private Set<String> variables = new HashSet<>();
}
