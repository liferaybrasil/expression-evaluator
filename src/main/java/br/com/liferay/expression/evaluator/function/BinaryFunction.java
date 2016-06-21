package br.com.liferay.expression.evaluator.function;

/**
 * @author Leonardo Barros
 */
@FunctionalInterface
public interface BinaryFunction extends Function {
	public Object evaluate(Object param1, Object param2);
}
