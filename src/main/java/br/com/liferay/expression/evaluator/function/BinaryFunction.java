package br.com.liferay.expression.evaluator.function;

/**
 * @author Leonardo Barros
 */
public interface BinaryFunction extends Function {
	public Object evaluate(Object param1, Object param2);
}
