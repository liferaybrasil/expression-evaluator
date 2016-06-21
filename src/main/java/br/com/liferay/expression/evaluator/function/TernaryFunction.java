package br.com.liferay.expression.evaluator.function;

/**
 * @author Leonardo Barros
 */
@FunctionalInterface
public interface TernaryFunction extends Function {
	public Object evaluate(Object param1, Object param2, Object param3);
}
