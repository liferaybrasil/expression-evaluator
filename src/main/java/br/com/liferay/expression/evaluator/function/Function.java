package br.com.liferay.expression.evaluator.function;

/**
 * @author Leonardo Barros
 */
abstract class Function {
	
	Function(String name) {
		this.name = name;
	}
	
	protected String getName() {
		return this.name;
	}
	
	private String name;
}
