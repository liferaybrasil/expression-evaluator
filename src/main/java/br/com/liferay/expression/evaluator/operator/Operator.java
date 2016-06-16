package br.com.liferay.expression.evaluator.operator;

import br.com.liferay.expression.evaluator.operand.Operand;
import br.com.liferay.expression.evaluator.operator.arithmetic.AdditionOperator;
import br.com.liferay.expression.evaluator.operator.arithmetic.MultiplicationOperator;

/**
 * @author Leonardo Barros
 */
public class Operator implements Comparable<Operator> {
	
	protected Operator(String text) {
		this.text = text;
	}
	
	public static Operator create(String text) {
		
		switch(text) {
		case "+":
			return new AdditionOperator(text);
		case "-":
			return new AdditionOperator(text, true);	
		case "*":
			return new MultiplicationOperator(text);
		case "(":
			return new ParenthesisOperator(text);	
		}
		
		return null;
	}
	
	public int compareTo(Operator operator) {
        return this.getPrecedence() - operator.getPrecedence();
    }
	
	public Operand evaluate(Operand... operands) {
		return null;
	}
	
	public int getPrecedence() {
		return -1;
	}

	public String getText() {
		return this.text;
	}
	
	public boolean isBinary() {
		return false;
	}
	
	private String text;
}
