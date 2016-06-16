package br.com.liferay.expression.evaluator.operand;

/**
 * @author Leonardo Barros
 */
public class StringOperand extends Operand {

	public StringOperand(String text) {
		super(text);
	}
	
	public StringOperand(String text, boolean variable) {
		this(text);
		this.variable = variable;
	}

	@Override
	public Class<?> getType() {
		return String.class;
	}

	@Override
	public Object getValue() {
		return getText();
	}
	
	public boolean isVariable() {
		return variable;
	}
	
	private boolean variable;

}
