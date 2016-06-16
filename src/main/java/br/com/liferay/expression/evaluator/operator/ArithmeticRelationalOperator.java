package br.com.liferay.expression.evaluator.operator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

import br.com.liferay.expression.evaluator.operand.Operand;

/**
 * @author Leonardo Barros
 */
public abstract class ArithmeticRelationalOperator extends Operator {

	protected ArithmeticRelationalOperator(
		String text, Map<Class<?>, BinaryInterface> map) {
		
		super(text);
		this.map = map;
	}

	@Override
	public boolean isBinary() {
		return true;
	}
	
	protected String applyPostfix(Object result) {
		Class<?> clazz = result.getClass();
		
		String postfix = "";
		
		if(clazz.equals(Double.class)) {
			postfix = "D";
		}
		else if(clazz.equals(Float.class)) {
			postfix = "F";
		}
		else if(clazz.equals(Long.class)) {
			postfix = "L";
		}
		
		return result.toString() + postfix;
	}
	
	protected Class<?> computeType(Operand operand1, Operand operand2) {
		if(operand1.getType().equals(operand2.getType())) {
			return operand1.getType(); 
		}
		if(operand1.getType().equals(String.class) ||
				operand2.getType().equals(String.class)) {
			return String.class;
		}
		else if(operand1.getType().equals(BigDecimal.class) ||
				operand2.getType().equals(BigDecimal.class)) {
			return BigDecimal.class;
		}
		else if(operand1.getType().equals(Double.class) ||
				operand2.getType().equals(Double.class)) {
			return Double.class;
		}
		else if(operand1.getType().equals(Float.class) ||
				operand2.getType().equals(Float.class)) {
			return Float.class;
		}
		else if(operand1.getType().equals(BigInteger.class) ||
				operand2.getType().equals(BigInteger.class)) {
			return BigInteger.class;
		}
		else if(operand1.getType().equals(Integer.class) ||
				operand2.getType().equals(Integer.class)) {
			return Integer.class;
		}
		else if(operand1.getType().equals(Long.class) ||
				operand2.getType().equals(Long.class)) {
			return Long.class;
		}
		
		return BigDecimal.class;
	}
	
	@Override
	public Operand evaluate(Operand... operands) {
		Class<?> clazz = computeType(operands[0], operands[1]);
		
		Object value1 = convert(operands[0], clazz);
		Object value2 = convert(operands[1], clazz);
		
		BinaryInterface binaryInterface = map.get(clazz);
		
		Object result = binaryInterface.evaluate(value1, value2);
		
		return Operand.create(applyPostfix(result));
	}
	
	protected Object convert(Operand operand, Class<?> clazz) {
		Object value = operand.getValue();
		if(value.getClass().equals(clazz)) {
			return value;
		}
		else if(clazz.equals(Integer.class)) {
			return new Integer(value.toString());
		}
		else if(clazz.equals(Long.class)) {
			return new Long(value.toString());
		}
		else if(clazz.equals(BigInteger.class)) {
			return new BigInteger(value.toString());
		}
		else if(clazz.equals(Float.class)) {
			return new Float(value.toString());
		}
		else if(clazz.equals(Double.class)) {
			return new Double(value.toString());
		}
		else if(clazz.equals(String.class)) {
			return value.toString();
		}
		return new BigDecimal(value.toString());
	}
	
	private final Map<Class<?>, BinaryInterface> map;

}
