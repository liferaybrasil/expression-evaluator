package br.com.liferay.expression.evaluator.operator;

/**
 * @author Leonardo Barros
 */
@FunctionalInterface
public interface BinaryInterface {
	public Object evaluate(Object n1, Object n2);
}
