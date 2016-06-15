package br.com.liferay.expression.evaluator.operand;

/**
 * @author Leonardo Barros
 */
public class LongOperand extends NumberOperand {

	public LongOperand(String text) {
		super(text);
	}
	
	@Override
	public Class<?> getType() {
		return Long.class;
	}

	@Override
	public Object getValue() {
		return Long.parseLong(getText());
	}

}
