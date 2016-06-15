package br.com.liferay.expression.evaluator.operand;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author Leonardo Barros
 */
public abstract class Operand {

	public Operand(String text) {
		this.text = text;
	}
	
	public static Operand create(String text) {
		text = text.toLowerCase();
		
		if(NumberUtils.isNumber(text)) {
			if(text.startsWith("0") || text.startsWith("0.")) {
				return new IntegerOperand("0");
			}
			else if(!text.contains("l") && NumberUtils.toInt(text) != 0) {
				return new IntegerOperand(text);
			}
			else if(NumberUtils.toLong(text) != 0) {
				return new LongOperand(text);
			}
			else if(!text.contains(".")) {
				return new BigIntegerOperand(text);
			}
			else if(!text.contains("d") && NumberUtils.toFloat(text) != 0) {
				return new FloatOperand(text);
			}
			else if(NumberUtils.toDouble(text) != 0) {
				return new DoubleOperand(text);
			}
			else if(text.contains(".")) {
				return new BigDecimalOperand(text);
			}
		}
		else if(text.contains("true") || text.contains("false")) {
			return new BooleanOperand(text);
		}
		
		return new StringOperand(text);
	}
	
	public String getText() {
		return this.text;
	}

	public abstract Class<?> getType();
	public abstract Object getValue();
	
	private String text;
}
