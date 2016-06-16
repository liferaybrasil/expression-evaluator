package br.com.liferay.expression.evaluator.operator;

/**
 * @author Leonardo Barros
 */
@FunctionalInterface
public interface UnaryInterface {
	public Object evaluate(Object value);
}
