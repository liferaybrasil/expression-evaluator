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
		String lowerText = text.toLowerCase();
		
		if(NumberUtils.isNumber(lowerText)) {
			if(lowerText.startsWith("0") || lowerText.startsWith("0.")) {
				return new IntegerOperand("0");
			}
			else if(!lowerText.contains("l") && NumberUtils.toInt(lowerText) != 0) {
				return new IntegerOperand(lowerText);
			}
			else if(lowerText.contains("l") && NumberUtils.toLong(lowerText.substring(0, lowerText.length()-1)) != 0) {
				return new LongOperand(lowerText.substring(0, lowerText.length()-1));
			}
			else if(!lowerText.contains(".")) {
				return new BigIntegerOperand(lowerText);
			}
			else if(!lowerText.contains("d") && Float.compare(NumberUtils.toFloat(lowerText),Float.POSITIVE_INFINITY) != 0) {
				return new FloatOperand(lowerText);
			}
			else if(Double.compare(NumberUtils.toDouble(lowerText), Double.POSITIVE_INFINITY) != 0) {
				return new DoubleOperand(lowerText);
			}
			else if(lowerText.contains(".")) {
				return new BigDecimalOperand(lowerText);
			}
		}
		else if(lowerText.contains("true") || lowerText.contains("false")) {
			return new BooleanOperand(lowerText);
		}
		
		return new StringOperand(lowerText);
	}
	
	public String getText() {
		return this.text;
	}

	public abstract Class<?> getType();
	public abstract Object getValue();
	
	private String text;
}
