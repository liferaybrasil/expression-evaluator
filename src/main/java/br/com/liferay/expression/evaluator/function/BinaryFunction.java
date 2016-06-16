package br.com.liferay.expression.evaluator.function;

/**
 * @author Leonardo Barros
 */
abstract class BinaryFunction extends Function {
	
	public BinaryFunction(String name) {
		super(name);
	}
	
	public abstract Object evaluate(Object n1, Object n2);
}
