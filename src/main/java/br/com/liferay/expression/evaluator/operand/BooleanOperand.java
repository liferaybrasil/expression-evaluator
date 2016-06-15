package br.com.liferay.expression.evaluator.operand;

/**
 * @author Leonardo Barros
 */
public class BooleanOperand extends Operand {

	public BooleanOperand(String text) {
		super(text);
	}

	@Override
	public Class<?> getType() {
		return Boolean.class;
	}

	@Override
	public Object getValue() {
		return Boolean.valueOf(getText());
	}

}
