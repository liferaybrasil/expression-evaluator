package br.com.liferay.expression.evaluator.operator.conditional;

/**
 * @author Leonardo Barros
 */
@FunctionalInterface
public interface ConditionalInterface {
	boolean evaluate(boolean value1, boolean value2);
}
