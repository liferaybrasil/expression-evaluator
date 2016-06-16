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
public class DivisionOperator extends ArithmeticRelationalOperator {
	private static final Map<Class<?>, BinaryInterface> map;
	
	static {
		map = new HashMap<Class<?>, BinaryInterface>();
		map.put(Integer.class, (n1, n2) -> (Integer)n2 / (Integer)n1);
		map.put(Long.class, (n1, n2) -> (Long)n2 / (Long)n1);
		map.put(Float.class, (n1, n2) -> (Float)n2 / (Float)n1);
		map.put(Double.class, (n1, n2) -> (Double)n2 / (Double)n1);
		map.put(BigInteger.class, (n1, n2) -> ((BigInteger)n2).divide((BigInteger)n1));
		map.put(BigDecimal.class, (n1, n2) -> ((BigDecimal)n2).divide((BigDecimal)n1));
	}

	public DivisionOperator(String text) {
		super(text, map);
	}

	@Override
	public int getPrecedence() {
		return 3;
	}
	
}
