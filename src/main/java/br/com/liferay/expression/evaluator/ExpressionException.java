package br.com.liferay.expression.evaluator;

/**
 * @author Leonardo Barros
 */
public class ExpressionException extends Exception {
	private static final long serialVersionUID = 4977798278096948928L;

	public ExpressionException(String message) {
		super(message);
	}

	public ExpressionException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
