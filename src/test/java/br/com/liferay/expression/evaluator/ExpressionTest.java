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
	
	@Test
	public void testGreaterThanEqual() throws Exception {
		Expression e = new Expression("5 >= 5");
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testGreaterThanEqual2() throws Exception {
		Expression e = new Expression("6 >= 5");
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testGreaterThanEqual3() throws Exception {
		Expression e = new Expression("4 >= 5");
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testLessThan() throws Exception {
		Expression e = new Expression("6 < 7");
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testLessThan2() throws Exception {
		Expression e = new Expression("6 < 6");
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testLessThan3() throws Exception {
		Expression e = new Expression("7 < 6");
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testLessThanEqual() throws Exception {
		Expression e = new Expression("8 <= 8");
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testLessThanEqual2() throws Exception {
		Expression e = new Expression("9 <= 8");
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testLessThanEqual3() throws Exception {
		Expression e = new Expression("7 <= 8");
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testAnd() throws Exception {
		Expression e = new Expression("4 > 3 && 3 >= 2");
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testAnd2() throws Exception {
		Expression e = new Expression("2 > 3 && 3 >= 2");
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testAnd3() throws Exception {
		Expression e = new Expression("4 > 3 && 3 >= 4");
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testOr() throws Exception {
		Expression e = new Expression("5 > 3 || 2 <= 4");
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testOr2() throws Exception {
		Expression e = new Expression("6 > 3 || 2 >= 4");
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testOr3() throws Exception {
		Expression e = new Expression("6 <= 3 || 7 > 5");
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testOr4() throws Exception {
		Expression e = new Expression("6 <= 3 || 9 >= 11");
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testNegation() throws Exception {
		Expression e = new Expression("!(6 <= 3 || 9 >= 11)");
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testNegation2() throws Exception {
		Expression e = new Expression("!(4 > 3 && 3 >= 4)");
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testEquality() throws Exception {
		Expression e = new Expression("4 == 4");
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testEquality2() throws Exception {
		Expression e = new Expression("2 > 3 == 5 <= 7");
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testEquality3() throws Exception {
		Expression e = new Expression("5 > 4 == 9 >= 7");
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testEquality4() throws Exception {
		Expression e = new Expression("\"hello\" == \"hello\"");
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testNotEquals() throws Exception {
		Expression e = new Expression("2 > 3 != 5 <= 7");
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testNotEquals2() throws Exception {
		Expression e = new Expression("5 > 4 != 9 >= 7");
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testNotEquals3() throws Exception {
		Expression e = new Expression("\"hello\" != \"world\"");
		Assert.assertEquals(true, e.evaluate());
	}
}
