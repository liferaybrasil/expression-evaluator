package br.com.liferay.expression.evaluator.operator.conditional;

import br.com.liferay.expression.evaluator.operand.Operand;
import br.com.liferay.expression.evaluator.operator.Operator;

/**
 * @author Leonardo Barros
 */
public class ConditionalOperator extends Operator {

	protected ConditionalOperator(String text, ConditionalInterface conditionalInterface) {
		super(text);
		this.conditionalInterface = conditionalInterface;
	}
	
	@Override
	public Operand evaluate(Operand... operands) {
		return Operand.create(Boolean.toString(conditionalInterface.evaluate((Boolean)operands[0].getValue(), (Boolean)operands[1].getValue())));
	}

	@Override
	public boolean isBinary() {
		return true;
	}

	@Override
	public int getPrecedence() {
		return 0;
	}

	private final ConditionalInterface conditionalInterface;
}
