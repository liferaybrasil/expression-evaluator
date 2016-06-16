package br.com.liferay.expression.evaluator.operator.arithmetic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import br.com.liferay.expression.evaluator.operator.ArithmeticRelationalOperator;
import br.com.liferay.expression.evaluator.operator.BinaryInterface;

/**
 * @author Leonardo Barros
 */
public class MultiplicationOperator extends ArithmeticRelationalOperator {
	private static final Map<Class<?>, BinaryInterface> map;
	
	static {
		map = new HashMap<Class<?>, BinaryInterface>();
		map.put(Integer.class, (n1, n2) -> (Integer)n1 * (Integer)n2);
		map.put(Long.class, (n1, n2) -> (Long)n1 * (Long)n2);
		map.put(Float.class, (n1, n2) -> (Float)n1 * (Float)n2);
		map.put(Double.class, (n1, n2) -> (Double)n1 * (Double)n2);
		map.put(BigInteger.class, (n1, n2) -> ((BigInteger)n1).multiply((BigInteger)n2));
		map.put(BigDecimal.class, (n1, n2) -> ((BigDecimal)n1).multiply((BigDecimal)n2));
	}

	public MultiplicationOperator(String text) {
		super(text, map);
	}

	@Override
	public int getPrecedence() {
		return 3;
	}
}
