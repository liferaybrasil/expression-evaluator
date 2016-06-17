package br.com.liferay.expression.evaluator;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import br.com.liferay.expression.evaluator.function.BinaryFunction;
import br.com.liferay.expression.evaluator.function.Function;

/**
 * @author Leonardo Barros
 */
public class ExpressionTest {

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyExpression() throws Exception {
		new ExpressionBuilder().buildExpression();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyExpression2() throws Exception {
		new ExpressionBuilder().expression("").buildExpression();
	}
	
	@Test
	public void testAddition() throws Exception {
		Expression expression = new ExpressionBuilder().expression("1 + 3").buildExpression();
		Assert.assertEquals(4, expression.evaluate());
	}
	
	@Test
	public void testAddition2() throws Exception {
		Expression expression = new ExpressionBuilder().expression("1 + 3").buildExpression();
		Expression expression2 = new ExpressionBuilder().expression("3 + 1").buildExpression();
		Assert.assertEquals(expression.evaluate(), expression2.evaluate());
	}
	
	@Test
	public void testAddition3() throws Exception {
		Expression expression = new ExpressionBuilder().expression("2 + 5 + 7").buildExpression();
		Assert.assertEquals(14, expression.evaluate());
	}
	
	@Test
	public void testSubtraction() throws Exception {
		Expression e = new ExpressionBuilder().expression("5 - 4").buildExpression();
		Assert.assertEquals(1, e.evaluate());
	}
	
	@Test
	public void testSubtraction2() throws Exception {
		Expression e = new ExpressionBuilder().expression("10 - 4 - 9").buildExpression();
		Assert.assertEquals(-3, e.evaluate());
	}
	
	@Test
	public void testSubtraction3() throws Exception {
		Expression e = new ExpressionBuilder().expression("3 - 6").buildExpression();
		Assert.assertEquals(-3, e.evaluate());
	}
	
	@Test
	public void testMultiplication() throws Exception {
		Expression e = new ExpressionBuilder().expression("2 * 3").buildExpression();
		Assert.assertEquals(6, e.evaluate());
	}
	
	@Test
	public void testMultiplication2() throws Exception {
		Expression e = new ExpressionBuilder().expression("2 * (-3)").buildExpression();
		Assert.assertEquals(-6, e.evaluate());
	}
	
	@Test
	public void testMultiplication3() throws Exception {
		Expression e = new ExpressionBuilder().expression("4 * 2 * 3").buildExpression();
		Assert.assertEquals(24, e.evaluate());
	}
	
	@Test
	public void testMultiplication4() throws Exception {
		Expression e = new ExpressionBuilder().expression("2.5 * 4").buildExpression();
		Assert.assertEquals(new Float(10), e.evaluate());
	}
	
	@Test
	public void testMultiplication5() throws Exception {
		Expression e = new ExpressionBuilder().expression("2.5d * 4").buildExpression();
		Assert.assertEquals(new Double(10), e.evaluate());
	}
	
	@Test
	public void testDivision() throws Exception {
		Expression e = new ExpressionBuilder().expression("6 / 2").buildExpression();
		Assert.assertEquals(3, e.evaluate());
	}
	
	@Test
	public void testDivision2() throws Exception {
		Expression e = new ExpressionBuilder().expression("7 / 6").buildExpression();
		Assert.assertEquals(1, e.evaluate());
	}
	
	@Test
	public void testDivision3() throws Exception {
		Expression e = new ExpressionBuilder().expression("10.0 / 4").buildExpression();
		Assert.assertEquals(new Float(2.5), e.evaluate());
	}
	
	@Test
	public void testPrecedence() throws Exception {
		Expression e = new ExpressionBuilder().expression("2 + 3 * 4").buildExpression();
		Assert.assertEquals(14, e.evaluate());
	}
	
	@Test
	public void testPrecedence2() throws Exception {
		Expression e = new ExpressionBuilder().expression("4 - 8 / 2").buildExpression();
		Assert.assertEquals(0, e.evaluate());
	}
	
	@Test
	public void testPrecedence3() throws Exception {
		Expression e = new ExpressionBuilder().expression("3 * 8 / 2").buildExpression();
		Assert.assertEquals(12, e.evaluate());
	}
	
	@Test
	public void testPrecedence4() throws Exception {
		Expression e = new ExpressionBuilder().expression("5 + 7 - 1").buildExpression();
		Assert.assertEquals(11, e.evaluate());
	}
	
	@Test
	public void testModulus() throws Exception {
		Expression e = new ExpressionBuilder().expression("7 % 6").buildExpression();
		Assert.assertEquals(1, e.evaluate());
	}
	
	@Test
	public void testModulus2() throws Exception {
		Expression e = new ExpressionBuilder().expression("7 % 7").buildExpression();
		Assert.assertEquals(0, e.evaluate());
	}
	
	@Test
	public void testGreaterThan() throws Exception {
		Expression e = new ExpressionBuilder().expression("3 > 2").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testGreaterThan2() throws Exception {
		Expression e = new ExpressionBuilder().expression("3 > 3").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testGreaterThan3() throws Exception {
		Expression e = new ExpressionBuilder().expression("1 > 3").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testGreaterThanEqual() throws Exception {
		Expression e = new ExpressionBuilder().expression("5 >= 5").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testGreaterThanEqual2() throws Exception {
		Expression e = new ExpressionBuilder().expression("6 >= 5").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testGreaterThanEqual3() throws Exception {
		Expression e = new ExpressionBuilder().expression("4 >= 5").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testLessThan() throws Exception {
		Expression e = new ExpressionBuilder().expression("6 < 7").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testLessThan2() throws Exception {
		Expression e = new ExpressionBuilder().expression("6 < 6").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testLessThan3() throws Exception {
		Expression e = new ExpressionBuilder().expression("7 < 6").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testLessThanEqual() throws Exception {
		Expression e = new ExpressionBuilder().expression("8 <= 8").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testLessThanEqual2() throws Exception {
		Expression e = new ExpressionBuilder().expression("9 <= 8").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testLessThanEqual3() throws Exception {
		Expression e = new ExpressionBuilder().expression("7 <= 8").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testAnd() throws Exception {
		Expression e = new ExpressionBuilder().expression("4 > 3 && 3 >= 2").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testAnd2() throws Exception {
		Expression e = new ExpressionBuilder().expression("2 > 3 && 3 >= 2").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testAnd3() throws Exception {
		Expression e = new ExpressionBuilder().expression("4 > 3 && 3 >= 4").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testOr() throws Exception {
		Expression e = new ExpressionBuilder().expression("5 > 3 || 2 <= 4").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testOr2() throws Exception {
		Expression e = new ExpressionBuilder().expression("6 > 3 || 2 >= 4").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testOr3() throws Exception {
		Expression e = new ExpressionBuilder().expression("6 <= 3 || 7 > 5").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testOr4() throws Exception {
		Expression e = new ExpressionBuilder().expression("6 <= 3 || 9 >= 11").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testNegation() throws Exception {
		Expression e = new ExpressionBuilder().expression("!(6 <= 3 || 9 >= 11)").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testNegation2() throws Exception {
		Expression e = new ExpressionBuilder().expression("!(4 > 3 && 3 >= 4)").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testEquality() throws Exception {
		Expression e = new ExpressionBuilder().expression("4 == 4").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testEquality2() throws Exception {
		Expression e = new ExpressionBuilder().expression("2 > 3 == 5 <= 7").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testEquality3() throws Exception {
		Expression e = new ExpressionBuilder().expression("5 > 4 == 9 >= 7").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testEquality4() throws Exception {
		Expression e = new ExpressionBuilder().expression("\"hello\" == \"hello\"").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testNotEquals() throws Exception {
		Expression e = new ExpressionBuilder().expression("2 > 3 != 5 <= 7").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testNotEquals2() throws Exception {
		Expression e = new ExpressionBuilder().expression("5 > 4 != 9 >= 7").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testNotEquals3() throws Exception {
		Expression e = new ExpressionBuilder().expression("\"hello\" != \"world\"").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testVariables() throws Exception {
		Map<String,Object> variables = new HashMap<>();
		variables.put("a", 1);
		variables.put("b", 5);
		Expression e = new ExpressionBuilder().expression("a + b").variables(variables).buildExpression();
		Assert.assertEquals(6, e.evaluate());
	}
	
	@Test
	public void testVariables2() throws Exception {
		Map<String,Object> variables = new HashMap<>();
		variables.put("a", 2);
		Expression e = new ExpressionBuilder().expression("a * 7").variables(variables).buildExpression();
		Assert.assertEquals(14, e.evaluate());
	}
	
	@Test
	public void testContainsFunction() throws Exception {
		Map<String,Object> variables = new HashMap<>();
		variables.put("field0", "hello world");
		variables.put("field1", "hello");
		
		Function containsFunctions = new BinaryFunction() {
			
			@Override
			public Object evaluate(Object param1, Object param2) {
				String str1 = param1.toString();
				String str2 = param2.toString();
				return str1.contains(str2);
			}
		};
		
		Map<String,Function> functions = new HashMap<>();
		functions.put("contains", containsFunctions);
		
		Expression e = new ExpressionBuilder().expression("contains(field0,field1)").functions(functions).variables(variables).buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
}
