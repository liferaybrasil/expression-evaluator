package br.com.liferay.expression.evaluator.function;

/**
 * @author Leonardo Barros
 */
@FunctionalInterface
public interface UnaryFunction extends Function {
	public Object evaluate(Object param1);
}
