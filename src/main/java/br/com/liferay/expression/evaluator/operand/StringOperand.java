package br.com.liferay.expression.evaluator.operand;

/**
 * @author Leonardo Barros
 */
public class StringOperand extends Operand {

	public StringOperand(String text) {
		super(text);
	}

	@Override
	public Class<?> getType() {
		return String.class;
	}

	@Override
	public Object getValue() {
		return getText();
	}

}
