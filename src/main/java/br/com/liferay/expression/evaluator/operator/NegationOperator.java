package br.com.liferay.expression.evaluator.operator;

/**
 * @author Leonardo Barros
 */
public class NegationOperator extends UnaryOperator {
	private static final UnaryInterface OPERATION = v -> !((Boolean)v);

	public NegationOperator(String text) {
		super(text, OPERATION);
	}

	@Override
	public int getPrecedence() {
		return 5;
	}
}
