package br.com.liferay.expression.evaluator;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Leonardo Barros
 */
public class ExpressionTest {

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyExpression() throws Exception {
		new Expression(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyExpression2() throws Exception {
		new Expression("");
	}
	
	@Test
	public void testAddition() throws Exception {
		Expression expression = new Expression("1 + 3");
		Assert.assertEquals(4, expression.evaluate());
	}
	
	@Test
	public void testAddition2() throws Exception {
		Expression expression = new Expression("1 + 3");
		Expression expression2 = new Expression("3 + 1");
		Assert.assertEquals(expression.evaluate(), expression2.evaluate());
	}
}
