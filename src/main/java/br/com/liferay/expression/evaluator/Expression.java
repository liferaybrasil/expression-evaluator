package br.com.liferay.expression.evaluator;

import org.apache.commons.lang3.StringUtils;

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
	
	private String expression;
}
