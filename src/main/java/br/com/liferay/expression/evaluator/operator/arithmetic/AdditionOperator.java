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
public class AdditionOperator extends ArithmeticRelationalOperator {
	private static final Map<Class<?>, BinaryInterface> map;
	private static final Map<Class<?>, BinaryInterface> mapNegative;
	
	static {
		map = new HashMap<Class<?>, BinaryInterface>();
		map.put(String.class, (n1, n2) -> ((String)n2).concat((String)n1));
		map.put(Integer.class, (n1, n2) -> (Integer)n1 + (Integer)n2);
		map.put(Long.class, (n1, n2) -> (Long)n1 + (Long)n2);
		map.put(Float.class, (n1, n2) -> (Float)n1 + (Float)n2);
		map.put(Double.class, (n1, n2) -> (Double)n1 + (Double)n2);
		map.put(BigInteger.class, (n1, n2) -> ((BigInteger)n1).add((BigInteger)n2));
		map.put(BigDecimal.class, (n1, n2) -> ((BigDecimal)n1).add((BigDecimal)n2));
		
		mapNegative = new HashMap<Class<?>, BinaryInterface>();
		mapNegative.put(Integer.class, (n1, n2) -> (Integer)n2 - (Integer)n1);
		mapNegative.put(Long.class, (n1, n2) -> (Long)n2 - (Long)n1);
		mapNegative.put(Float.class, (n1, n2) -> (Float)n2 - (Float)n1);
		mapNegative.put(Double.class, (n1, n2) -> (Double)n2 - (Double)n1);
		mapNegative.put(BigInteger.class, (n1, n2) -> ((BigInteger)n2).subtract((BigInteger)n1));
		mapNegative.put(BigDecimal.class, (n1, n2) -> ((BigDecimal)n2).subtract((BigDecimal)n1));
	}
	
	public AdditionOperator(String text) {
		super(text, map);
	}
	
	public AdditionOperator(String text, boolean negative) {
		super(text, mapNegative);
	}

	@Override
	public int getPrecedence() {
		return 2;
	}

}
