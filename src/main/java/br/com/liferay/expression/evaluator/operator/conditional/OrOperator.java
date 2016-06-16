package br.com.liferay.expression.evaluator.operator.conditional;

/**
 * @author Leonardo Barros
 */
public class OrOperator extends ConditionalOperator {
	private static ConditionalInterface OPERATION = (n1, n2) -> n1 || n2;
	
	public OrOperator(String text) {
		super(text, OPERATION);
	}

}
