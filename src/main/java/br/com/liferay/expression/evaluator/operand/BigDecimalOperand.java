package br.com.liferay.expression.evaluator.operand;

import java.math.BigDecimal;

/**
 * @author Leonardo Barros
 */
public class BigDecimalOperand extends NumberOperand {

	public BigDecimalOperand(String text) {
		super(text);
	}

	@Override
	public Class<?> getType() {
		return BigDecimal.class;
	}

	@Override
	public Object getValue() {
		return new BigDecimal(getText());
	}

}
