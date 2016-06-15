package br.com.liferay.expression.evaluator.operand;

/**
 * @author Leonardo Barros
 */
public class DoubleOperand extends NumberOperand {

	public DoubleOperand(String text) {
		super(text);
	}
	
	@Override
	public Class<?> getType() {
		return Double.class;
	}

	@Override
	public Object getValue() {
		return Double.parseDouble(getText());
	}

}
