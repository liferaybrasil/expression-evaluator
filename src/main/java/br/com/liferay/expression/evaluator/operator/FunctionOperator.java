package br.com.liferay.expression.evaluator.operator;

/**
 * @author Leonardo Barros
 */
public class FunctionOperator extends Operator {

	public FunctionOperator(String text) {
		super(text);
	}

	@Override
	public int getPrecedence() {
		return 99;
	}
}
