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
	
	@Test
	public void testAddition3() throws Exception {
		Expression expression = new Expression("2 + 5 + 7");
		Assert.assertEquals(14, expression.evaluate());
	}
	
	@Test
	public void testSubtraction() throws Exception {
		Expression e = new Expression("5 - 4");
		Assert.assertEquals(1, e.evaluate());
	}
	
	@Test
	public void testSubtraction2() throws Exception {
		Expression e = new Expression("10 - 4 - 9");
		Assert.assertEquals(-3, e.evaluate());
	}
	
	@Test
	public void testSubtraction3() throws Exception {
		Expression e = new Expression("3 - 6");
		Assert.assertEquals(-3, e.evaluate());
	}
	
	@Test
	public void testMultiplication() throws Exception {
		Expression e = new Expression("2 * 3");
		Assert.assertEquals(6, e.evaluate());
	}
	
	@Test
	public void testMultiplication2() throws Exception {
		Expression e = new Expression("2 * (-3)");
		Assert.assertEquals(-6, e.evaluate());
	}
	
	@Test
	public void testMultiplication3() throws Exception {
		Expression e = new Expression("4 * 2 * 3");
		Assert.assertEquals(24, e.evaluate());
	}
	
	@Test
	public void testMultiplication4() throws Exception {
		Expression e = new Expression("2.5 * 4");
		Assert.assertEquals(new Float(10), e.evaluate());
	}
	
	@Test
	public void testMultiplication5() throws Exception {
		Expression e = new Expression("2.5d * 4");
		Assert.assertEquals(new Double(10), e.evaluate());
	}
	
	@Test
	public void testDivision() throws Exception {
		Expression e = new Expression("6 / 2");
		Assert.assertEquals(3, e.evaluate());
	}
	
	@Test
	public void testDivision2() throws Exception {
		Expression e = new Expression("7 / 6");
		Assert.assertEquals(1, e.evaluate());
	}
	
	@Test
	public void testDivision3() throws Exception {
		Expression e = new Expression("10.0 / 4");
		Assert.assertEquals(new Float(2.5), e.evaluate());
	}
	
	@Test
	public void testPrecedence() throws Exception {
		Expression e = new Expression("2 + 3 * 4");
		Assert.assertEquals(14, e.evaluate());
	}
	
	@Test
	public void testPrecedence2() throws Exception {
		Expression e = new Expression("4 - 8 / 2");
		Assert.assertEquals(0, e.evaluate());
	}
	
	@Test
	public void testPrecedence3() throws Exception {
		Expression e = new Expression("3 * 8 / 2");
		Assert.assertEquals(12, e.evaluate());
	}
	
	@Test
	public void testPrecedence4() throws Exception {
		Expression e = new Expression("5 + 7 - 1");
		Assert.assertEquals(11, e.evaluate());
	}
	
	@Test
	public void testModulus() throws Exception {
		Expression e = new Expression("7 % 6");
		Assert.assertEquals(1, e.evaluate());
	}
	
	@Test
	public void testModulus2() throws Exception {
		Expression e = new Expression("7 % 7");
		Assert.assertEquals(0, e.evaluate());
	}
	
	@Test
	public void testGreaterThan() throws Exception {
		Expression e = new Expression("3 > 2");
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testGreaterThan2() throws Exception {
		Expression e = new Expression("3 > 3");
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testGreaterThan3() throws Exception {
		Expression e = new Expression("1 > 3");
		Assert.assertEquals(false, e.evaluate());
	}
}
