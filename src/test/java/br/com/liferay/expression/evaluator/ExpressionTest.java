package br.com.liferay.expression.evaluator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import br.com.liferay.expression.evaluator.function.BinaryFunction;
import br.com.liferay.expression.evaluator.function.Function;
import br.com.liferay.expression.evaluator.function.TernaryFunction;
import br.com.liferay.expression.evaluator.function.UnaryFunction;

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
	public void testAddition4() throws Exception {
		Expression expression = new ExpressionBuilder().expression("(2 + 5) - (7 + 3)").buildExpression();
		Assert.assertEquals(-3, expression.evaluate());
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
	public void testSubtraction4() throws Exception {
		Expression e = new ExpressionBuilder().expression("(8 - 6) - (7 + 1) * (4 - 3)").buildExpression();
		Assert.assertEquals(-6, e.evaluate());
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
		
		Expression e = new ExpressionBuilder().expression("contains(field0, field1)").functions(functions).variables(variables).buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testConcatFunction() throws Exception {
		Map<String,Object> variables = new HashMap<>();
		variables.put("field0", "hello");
		variables.put("field1", "world");
		
		Function concatFunction = new BinaryFunction() {
			
			@Override
			public Object evaluate(Object param1, Object param2) {
				String str1 = param1.toString();
				String str2 = param2.toString();
				return str1.concat(" ").concat(str2);
			}
		};
		
		Map<String,Function> functions = new HashMap<>();
		functions.put("concat", concatFunction);
		
		Expression e = new ExpressionBuilder().expression("concat(field0, field1)").functions(functions).variables(variables).buildExpression();
		Assert.assertEquals("hello world", e.evaluate());
	}
	
	@Test
	public void testConcatFunction2() throws Exception {
		Map<String,Object> variables = new HashMap<>();
		variables.put("field0", "hello");
		variables.put("field1", "world");
		
		Function concatFunction = new BinaryFunction() {
			
			@Override
			public Object evaluate(Object param1, Object param2) {
				String str1 = param1.toString();
				String str2 = param2.toString();
				return str1.concat(str2);
			}
		};
		
		Map<String,Function> functions = new HashMap<>();
		functions.put("concat", concatFunction);
		
		Expression e = new ExpressionBuilder().expression("concat(field0,concat(\" \", field1))").functions(functions).variables(variables).buildExpression();
		Assert.assertEquals("hello world", e.evaluate());
	}
	
	@Test
	public void testBetweenFunction() throws Exception {
		Function betweenFunction = new TernaryFunction() {
			
			@Override
			public Object evaluate(Object param1, Object param2, Object param3) {
				Integer val1 = Integer.valueOf(param1.toString());
				Integer val2 = Integer.valueOf(param2.toString());
				Integer val3 = Integer.valueOf(param3.toString());
				return val1 >= val2 && val1 <= val3;
			}
		};
		
		Map<String,Function> functions = new HashMap<>();
		functions.put("between", betweenFunction);
		
		Expression e = new ExpressionBuilder().expression("between(5,(2+1),(4+3))").functions(functions).buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testNotFunction() throws Exception {
		Function notFunction = new UnaryFunction() {
			
			@Override
			public Object evaluate(Object param1) {
				return !Boolean.valueOf(param1.toString());
			}
		};
		
		Map<String,Function> functions = new HashMap<>();
		functions.put("not", notFunction);
		
		Expression e = new ExpressionBuilder().expression("not((4 * 5) >= (40 / 2))").functions(functions).buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testNotFunction2() throws Exception {
		Function notFunction = new UnaryFunction() {
			
			@Override
			public Object evaluate(Object param1) {
				return !Boolean.valueOf(param1.toString());
			}
		};
		
		Map<String,Function> functions = new HashMap<>();
		functions.put("not", notFunction);
		
		Expression e = new ExpressionBuilder().expression("not((4 * 5) > (40 / 2))").functions(functions).buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testSumFunction() throws Exception {
		Map<String,Object> variables = new HashMap<>();
		variables.put("field0", "20.5");
		variables.put("field1", "12.8");
		variables.put("field2", "3.2");
		
		Function sumFunction = new TernaryFunction() {
			
			@Override
			public Object evaluate(Object param1, Object param2, Object param3) {
				Float val1 = Float.valueOf(param1.toString());
				Float val2 = Float.valueOf(param2.toString());
				Float val3 = Float.valueOf(param3.toString());
				return val1 + val2 + val3;
			}
		};
		
		Map<String,Function> functions = new HashMap<>();
		functions.put("sum", sumFunction);
		
		Expression e = new ExpressionBuilder().expression("sum(field0,field1,field2)").functions(functions).variables(variables).buildExpression();
		Assert.assertEquals(36.5F, e.evaluate());
	}
	
	@Test
	public void testSumFunction2() throws Exception {
		Function sumFunction = new TernaryFunction() {
			
			@Override
			public Object evaluate(Object param1, Object param2, Object param3) {
				Integer val1 = Integer.valueOf(param1.toString());
				Integer val2 = Integer.valueOf(param2.toString());
				Integer val3 = Integer.valueOf(param3.toString());
				return val1 + val2 + val3;
			}
		};
		
		Map<String,Function> functions = new HashMap<>();
		functions.put("sum", sumFunction);
		
		Expression e = new ExpressionBuilder().expression("sum((30 + 50),(50 / 2 * 3),(8 - 2 - 9))").functions(functions).buildExpression();
		Assert.assertEquals(152, e.evaluate());
	}
	
	@Test
	public void testSumFunction3() throws Exception {
		Function sumFunction = new TernaryFunction() {
			
			@Override
			public Object evaluate(Object param1, Object param2, Object param3) {
				Integer val1 = Integer.valueOf(param1.toString());
				Integer val2 = Integer.valueOf(param2.toString());
				Integer val3 = Integer.valueOf(param3.toString());
				return val1 + val2 + val3;
			}
		};
		
		Map<String,Function> functions = new HashMap<>();
		functions.put("sum", sumFunction);
		
		Expression e = new ExpressionBuilder().expression("sum(20,(24/2),(13%2)) + 0").functions(functions).buildExpression();
		Assert.assertEquals(33, e.evaluate());
	}
	
	@Test
	public void testEqualsFunction() throws Exception {
		Map<String,Object> variables = new HashMap<>();
		variables.put("field0", "hello");
		
		Function equalsFunctions = new BinaryFunction() {
			
			@Override
			public Object evaluate(Object param1, Object param2) {
				String str1 = param1.toString();
				String str2 = param2.toString();
				return str1.equals(str2);
			}
		};
		
		Map<String,Function> functions = new HashMap<>();
		functions.put("equals", equalsFunctions);
		
		Expression e = new ExpressionBuilder().expression("equals(field0,\"hello\")").functions(functions).variables(variables).buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}

	@Test
	public void testInvalidStringConstant() throws Exception {
		Map<String,Object> variables = new HashMap<>();
		variables.put("field0", "hello");
		
		Function equalsFunctions = new BinaryFunction() {
			
			@Override
			public Object evaluate(Object param1, Object param2) {
				String str1 = param1.toString();
				String str2 = param2.toString();
				return str1.equals(str2);
			}
		};
		
		Map<String,Function> functions = new HashMap<>();
		functions.put("equals", equalsFunctions);
		Expression e = new ExpressionBuilder().expression("equals(fields0,\"hello)").functions(functions).variables(variables).buildExpression();
		try {
			e.evaluate();
			Assert.fail();
		}
		catch(ExpressionException ee) {
			Assert.assertEquals("An invalid String constant was found.", ee.getMessage());
		}
	}
	
	@Test
	public void testInvalidAndOperator() throws Exception {
		Expression e = new ExpressionBuilder().expression("1 & 2").buildExpression();
		try {
			e.evaluate();
			Assert.fail();
		}
		catch(ExpressionException ee) {
			Assert.assertEquals("The conditional operator is '&&'", ee.getMessage());
		}
	}
	
	@Test
	public void testInvalidOrOperator() throws Exception {
		Expression e = new ExpressionBuilder().expression("1 | 2").buildExpression();
		try {
			e.evaluate();
			Assert.fail();
		}
		catch(ExpressionException ee) {
			Assert.assertEquals("The conditional operator is '||'", ee.getMessage());
		}
	}
	
	@Test
	public void testInvalidToken() throws Exception {
		Expression e = new ExpressionBuilder().expression("1 # 2").buildExpression();
		try {
			e.evaluate();
			Assert.fail();
		}
		catch(ExpressionException ee) {
			Assert.assertEquals("Invalid token found: " + '#', ee.getMessage());
		}
	}
	
	@Test
	public void testModulusPrecedence() throws Exception {
		Expression e = new ExpressionBuilder().expression("8 / 7 % 3 * 3").buildExpression();
		Assert.assertEquals(24, e.evaluate());
	}
	
	@Test
	public void testLongOperation() throws Exception {
		Expression e = new ExpressionBuilder().expression("8l * 4").buildExpression();
		Assert.assertEquals(32L, e.evaluate());
	}
	
	@Test
	public void testBigIntegerOperation() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984798471297157918571985719857918571985719857195 + 1").buildExpression();
		Assert.assertEquals(new BigInteger("312793281798471982471984798471297157918571985719857918571985719857196"), e.evaluate());
	}
	
	@Test
	public void testBigIntegerOperation2() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984798471297157918571985719857918571985719857196 >= 312793281798471982471984798471297157918571985719857918571985719857195").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testBigIntegerOperation3() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984798471297157918571985719857918571985719857196 >= 312793281798471982471984798471297157918571985719857918571985719857198").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testBigIntegerOperation4() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984798471297157918571985719857918571985719857196 > 312793281798471982471984798471297157918571985719857918571985719857198").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testBigIntegerOperation5() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984798471297157918571985719857918571985719857199 > 312793281798471982471984798471297157918571985719857918571985719857198").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}

	@Test
	public void testBigIntegerOperation6() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984798471297157918571985719857918571985719857199 > 312793281798471982471984798471297157918571985719857918571985719857199").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testBigIntegerOperation7() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984798471297157918571985719857918571985719857199 < 312793281798471982471984798471297157918571985719857918571985719857199").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testBigIntegerOperation8() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984798471297157918571985719857918571985719857198 < 312793281798471982471984798471297157918571985719857918571985719857199").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testBigIntegerOperation9() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984798471297157918571985719857918571985719857198 <= 312793281798471982471984798471297157918571985719857918571985719857197").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testBigIntegerOperation10() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984798471297157918571985719857918571985719857198 <= 312793281798471982471984798471297157918571985719857918571985719857199").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}

	@Test
	public void testBigDecimalOperation() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564712.0 + 1").buildExpression();
		Assert.assertEquals(new BigDecimal("312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564713.0"), e.evaluate());
	}
	
	@Test
	public void testBigDecimalOperation2() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564712.0 >= 312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564712.5").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testBigDecimalOperation3() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564713.0 >= 312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564712.5").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testBigDecimalOperation4() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564713.0 > 312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564712.5").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testBigDecimalOperation5() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564714.0 < 312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564712.5").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testBigDecimalOperation6() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564714.0 < 312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564714.0").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testBigDecimalOperation7() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564714.0 <= 312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564714.0").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testBigDecimalOperation8() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564714.0 <= 312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564713.0").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testBigDecimalOperation9() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564713.0 > 312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564713.0").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}

	@Test
	public void testBigDecimalOperation10() throws Exception {
		Expression e = new ExpressionBuilder().expression("312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564714.0 < 312793281798471982471984799471947894712984712984791749817948719824712984791791749817498172498712984721987491872498719827981749817248917498712489798479817498172498179878471297157918571985719857918571985719857195038201983091280482098091809851905819058190584879187498172498271498714981274981274981724917249172948721984719487198274291847914879195081031257645125621676717641672546712564715.0").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testBooleanConstant() throws Exception {
		Expression e = new ExpressionBuilder().expression("true && true").buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testBooleanConstant2() throws Exception {
		Expression e = new ExpressionBuilder().expression("true && false").buildExpression();
		Assert.assertEquals(false, e.evaluate());
	}
	
	@Test
	public void testStringConcatenation() throws Exception {
		Expression e = new ExpressionBuilder().expression("\"hello\" + 1").buildExpression();
		Assert.assertEquals("hello1", e.evaluate());
	}
	
	@Test
	public void testTernaryFunctionWithVariables() throws Exception {
		Map<String,Object> variables = new HashMap<>();
		variables.put("a", "10");
		variables.put("b", "9");
		variables.put("c", "8");
		
		Function ternaryFunction = new TernaryFunction() {
			
			@Override
			public Object evaluate(Object param1, Object param2, Object param3) {
				Integer val1 = Integer.valueOf(param1.toString());
				Integer val2 = Integer.valueOf(param2.toString());
				Integer val3 = Integer.valueOf(param3.toString());
				return val1 + val2 + val3;
			}
		};
		
		Map<String,Function> functions = new HashMap<>();
		functions.put("sum", ternaryFunction);
		
		Expression e = new ExpressionBuilder().expression("sum(a,b,c)").functions(functions).variables(variables).buildExpression();
		Assert.assertEquals(27, e.evaluate());
	}
	
	@Test
	public void testBinaryFunctionWithNullReturn() throws Exception {
		Function binaryFunction = new BinaryFunction() {
			
			@Override
			public Object evaluate(Object param1, Object param2) {
				return null;
			}
		};
		
		Map<String,Function> functions = new HashMap<>();
		functions.put("test", binaryFunction);
		
		Expression e = new ExpressionBuilder().expression("test(1,2)").functions(functions).buildExpression();
		Assert.assertEquals("", e.evaluate());
	}
	
	@Test
	public void testTernaryFunctionWithNullReturn() throws Exception {
		Function ternaryFunction = new TernaryFunction() {
			
			@Override
			public Object evaluate(Object param1, Object param2, Object param3) {
				return null;
			}
		};
		
		Map<String,Function> functions = new HashMap<>();
		functions.put("test", ternaryFunction);
		
		Expression e = new ExpressionBuilder().expression("test(1,2,3)").functions(functions).buildExpression();
		Assert.assertEquals("", e.evaluate());
	}
	
	@Test
	public void testUnaryFunctionWithNullReturn() throws Exception {
		Function unaryFunction = new UnaryFunction() {
			
			@Override
			public Object evaluate(Object param1) {
				return null;
			}
		};
		
		Map<String,Function> functions = new HashMap<>();
		functions.put("test", unaryFunction);
		
		Expression e = new ExpressionBuilder().expression("test(1)").functions(functions).buildExpression();
		Assert.assertEquals("", e.evaluate());
	}
	
	@Test
	public void testExpressionBuilder() throws Exception {
		Expression e = new ExpressionBuilder().expression("1 + 1").functions(null).variables(null).buildExpression();
		Assert.assertEquals(2, e.evaluate());
	}
	
	@Test
	public void testClosingParenthesis() throws Exception {
		Expression e = new ExpressionBuilder().expression("1 + 2)").buildExpression();
		try {
			e.evaluate();
			Assert.fail();
		}
		catch(ExpressionException ee) {
			Assert.assertEquals("It wasn't found an opening parenthesis", ee.getMessage());
		}
	}
	
	@Test
	public void testInvalidExpression() throws Exception {
		Expression e = new ExpressionBuilder().expression("1 + + 2").buildExpression();
		try {
			e.evaluate();
			Assert.fail();
		}
		catch(ExpressionException ee) {
			Assert.assertEquals("Invalid expression", ee.getMessage());
			Assert.assertNotNull(ee.getCause());
		}
	}
	
	@Test
	public void testFunctionAndBoolean() throws Exception {
		Function unaryFunction = new UnaryFunction() {
			
			@Override
			public Object evaluate(Object param1) {
				return true;
			}
		};
		
		Map<String,Function> functions = new HashMap<>();
		functions.put("test", unaryFunction);
		
		Expression e = new ExpressionBuilder().expression("test(1) && true").functions(functions).buildExpression();
		Assert.assertEquals(true, e.evaluate());
	}
	
	@Test
	public void testFunctionWithoutDefinition() throws Exception {
		Expression e = new ExpressionBuilder().expression("test(1)").buildExpression();
		try {
			e.evaluate();
			Assert.fail();
		}
		catch(ExpressionException ee) {
			Assert.assertEquals("Operator '(' not expected.", ee.getMessage());
		}
	}
}
