package br.com.liferay.expression.evaluator.operand;

/**
 * @author Leonardo Barros
 */
public class FloatOperand extends NumberOperand {

	public FloatOperand(String text) {
		super(text);
	}
	
	@Override
	public Class<?> getType() {
		return Float.class;
	}

	@Override
	public Object getValue() {
		return Float.parseFloat(getText());
	}

}
