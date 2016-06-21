package br.com.liferay.expression.evaluator.operator;

import br.com.liferay.expression.evaluator.operand.Operand;
import br.com.liferay.expression.evaluator.operator.arithmetic.AdditionOperator;
import br.com.liferay.expression.evaluator.operator.arithmetic.DivisionOperator;
import br.com.liferay.expression.evaluator.operator.arithmetic.ModulusOperator;
import br.com.liferay.expression.evaluator.operator.arithmetic.MultiplicationOperator;
import br.com.liferay.expression.evaluator.operator.conditional.AndOperator;
import br.com.liferay.expression.evaluator.operator.conditional.OrOperator;
import br.com.liferay.expression.evaluator.operator.relational.EqualityOperator;
import br.com.liferay.expression.evaluator.operator.relational.GreaterThanEqualOperator;
import br.com.liferay.expression.evaluator.operator.relational.GreaterThanOperator;
import br.com.liferay.expression.evaluator.operator.relational.LessThanEqualOperator;
import br.com.liferay.expression.evaluator.operator.relational.LessThanOperator;
import br.com.liferay.expression.evaluator.operator.relational.NotEqualsOperator;

/**
 * @author Leonardo Barros
 */
public abstract class Operator implements Comparable<Operator> {
	
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
		case "/":
			return new DivisionOperator(text);
		case "%": 
			return new ModulusOperator(text);
		case ">":
			return new GreaterThanOperator(text);
		case ">=":
			return new GreaterThanEqualOperator(text);
		case "<":
			return new LessThanOperator(text);
		case "<=":
			return new LessThanEqualOperator(text);	
		case "==":
			return new EqualityOperator(text);	
		case "!=":
			return new NotEqualsOperator(text);	
		case "&&":
			return new AndOperator(text);
		case "||":
			return new OrOperator(text);
		case "!":
			return new NegationOperator(text);
		case "(":
			return new ParenthesisOperator(text);	
		default:
			return null;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operator other = (Operator) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
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
