package br.com.liferay.expression.evaluator.operand;

import java.math.BigInteger;

/**
 * @author Leonardo Barros
 */
public class BigIntegerOperand extends NumberOperand {

	public BigIntegerOperand(String text) {
		super(text);
	}
	
	@Override
	public Class<?> getType() {
		return BigInteger.class;
	}

	@Override
	public Object getValue() {
		return new BigInteger(getText());
	}

}