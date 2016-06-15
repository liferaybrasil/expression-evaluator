package br.com.liferay.expression.evaluator.operand;

/**
 * @author Leonardo Barros
 */
public class IntegerOperand extends NumberOperand {

	public IntegerOperand(String text) {
		super(text);
	}

	@Override
	public Class<?> getType() {
		return Integer.class;
	}

	@Override
	public Integer getValue() {
		return Integer.parseInt(getText());
	}
	
}
