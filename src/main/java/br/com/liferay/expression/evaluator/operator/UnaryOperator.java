package br.com.liferay.expression.evaluator.operator;

import br.com.liferay.expression.evaluator.operand.Operand;

/**
 * @author Leonardo Barros
 */
public abstract class UnaryOperator extends Operator {

	protected UnaryOperator(String text, UnaryInterface unaryInterface) {
		super(text);
		this.unaryInterface = unaryInterface;
	}
	
	@Override
	public Operand evaluate(Operand... operands) {
		Object result = this.unaryInterface.evaluate(operands[0].getValue());
		
		return Operand.create(result.toString());
	}

	@Override
	public boolean isBinary() {
		return false;
	}
	
	private final UnaryInterface unaryInterface;
}
