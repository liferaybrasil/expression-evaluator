package br.com.liferay.expression.evaluator.operator.relational;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import br.com.liferay.expression.evaluator.operator.ArithmeticRelationalOperator;
import br.com.liferay.expression.evaluator.operator.BinaryInterface;

/**
 * @author Leonardo Barros
 */
public class EqualityOperator extends ArithmeticRelationalOperator {
	private static BinaryInterface OPERATION = (n1, n2) -> n1.equals(n2);
	
	private static final Map<Class<?>, BinaryInterface> map;
	
	static {
		map = new HashMap<Class<?>, BinaryInterface>();
		map.put(Integer.class, OPERATION);
		map.put(Long.class, OPERATION);
		map.put(Float.class, OPERATION);
		map.put(Double.class, OPERATION);
		map.put(BigInteger.class, OPERATION);
		map.put(BigDecimal.class, OPERATION);
		map.put(Boolean.class, OPERATION);
	}

	public EqualityOperator(String text) {
		super(text, map);
	}

	@Override
	public int getPrecedence() {
		return 0;
	}
}
