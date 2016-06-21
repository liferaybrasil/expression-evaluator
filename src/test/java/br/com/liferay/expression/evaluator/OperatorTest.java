package br.com.liferay.expression.evaluator;

import org.junit.Assert;
import org.junit.Test;

import br.com.liferay.expression.evaluator.operator.NegationOperator;
import br.com.liferay.expression.evaluator.operator.Operator;
import br.com.liferay.expression.evaluator.operator.ParenthesisOperator;
import br.com.liferay.expression.evaluator.operator.arithmetic.AdditionOperator;
import br.com.liferay.expression.evaluator.operator.arithmetic.DivisionOperator;
import br.com.liferay.expression.evaluator.operator.arithmetic.ModulusOperator;
import br.com.liferay.expression.evaluator.operator.arithmetic.MultiplicationOperator;
import br.com.liferay.expression.evaluator.operator.conditional.AndOperator;
import br.com.liferay.expression.evaluator.operator.conditional.OrOperator;
import br.com.liferay.expression.evaluator.operator.relational.EqualityOperator;
import br.com.liferay.expression.evaluator.operator.relational.GreaterThanEqualOperator;
import br.com.liferay.expression.evaluator.operator.relational.GreaterThanOperator;
import br.com.liferay.expression.evaluator.operator.relational.LessThanEqualOperator;
import br.com.liferay.expression.evaluator.operator.relational.LessThanOperator;
import br.com.liferay.expression.evaluator.operator.relational.NotEqualsOperator;

/**
 * @author Leonardo Barros
 */
public class OperatorTest {

	@Test
	public void testEquals() throws Exception {
		Operator operator1 = Operator.create("-");
		Operator operator2 = Operator.create("-");
		Assert.assertNotNull(operator1);
		Assert.assertNotNull(operator2);
		Assert.assertEquals(operator1, operator2);
		Assert.assertEquals(operator1.hashCode(), operator2.hashCode());
	}
	
	@Test
	public void testEquals2() throws Exception {
		Operator operator1 = Operator.create("-");
		Assert.assertEquals(operator1, operator1);
		Assert.assertEquals(operator1.hashCode(), operator1.hashCode());
	}
	
	@Test
	public void testNotEquals() throws Exception {
		Operator operator1 = Operator.create("*");
		Operator operator2 = Operator.create("/");
		Assert.assertNotNull(operator1);
		Assert.assertNotNull(operator2);
		Assert.assertNotEquals(operator1, operator2);
		Assert.assertNotEquals(operator1.hashCode(), operator2.hashCode());
	}
	
	@Test
	public void testNotEquals3() throws Exception {
		Operator operator1 = Operator.create("*");
		Operator operator2 = Operator.create("#");
		Assert.assertNotEquals(operator1, operator2);
	}
	
	@Test
	public void testNotEquals2() throws Exception {
		Operator operator1 = Operator.create("*");
		Assert.assertNotEquals(operator1, null);
	}
	
	@Test
	public void testInvalidOperator() throws Exception {
		Operator operator1 = Operator.create("#");
		Assert.assertNull(operator1);
	}
	
	@Test
	public void testCreate1() throws Exception {
		Operator operator1 = Operator.create("+");
		Assert.assertEquals(AdditionOperator.class, operator1.getClass());
		Assert.assertEquals("+", operator1.getText());
	}
	
	@Test
	public void testCreate2() throws Exception {
		Operator operator1 = Operator.create("-");
		Assert.assertEquals(AdditionOperator.class, operator1.getClass());
		Assert.assertEquals("-", operator1.getText());
	}
	
	@Test
	public void testCreate3() throws Exception {
		Operator operator1 = Operator.create("*");
		Assert.assertEquals(MultiplicationOperator.class, operator1.getClass());
		Assert.assertEquals("*", operator1.getText());
	}
	
	@Test
	public void testCreate4() throws Exception {
		Operator operator1 = Operator.create("/");
		Assert.assertEquals(DivisionOperator.class, operator1.getClass());
		Assert.assertEquals("/", operator1.getText());
	}
	
	@Test
	public void testCreate5() throws Exception {
		Operator operator1 = Operator.create("/");
		Assert.assertEquals(DivisionOperator.class, operator1.getClass());
		Assert.assertEquals("/", operator1.getText());
	}
	
	@Test
	public void testCreate6() throws Exception {
		Operator operator1 = Operator.create("%");
		Assert.assertEquals(ModulusOperator.class, operator1.getClass());
		Assert.assertEquals("%", operator1.getText());
	}
	
	@Test
	public void testCreate7() throws Exception {
		Operator operator1 = Operator.create(">");
		Assert.assertEquals(GreaterThanOperator.class, operator1.getClass());
		Assert.assertEquals(">", operator1.getText());
	}
	
	@Test
	public void testCreate8() throws Exception {
		Operator operator1 = Operator.create(">=");
		Assert.assertEquals(GreaterThanEqualOperator.class, operator1.getClass());
		Assert.assertEquals(">=", operator1.getText());
	}

	@Test
	public void testCreate9() throws Exception {
		Operator operator1 = Operator.create("<");
		Assert.assertEquals(LessThanOperator.class, operator1.getClass());
		Assert.assertEquals("<", operator1.getText());
	}
	
	@Test
	public void testCreate10() throws Exception {
		Operator operator1 = Operator.create("<=");
		Assert.assertEquals(LessThanEqualOperator.class, operator1.getClass());
		Assert.assertEquals("<=", operator1.getText());
	}
	
	@Test
	public void testCreate11() throws Exception {
		Operator operator1 = Operator.create("==");
		Assert.assertEquals(EqualityOperator.class, operator1.getClass());
		Assert.assertEquals("==", operator1.getText());
	}
	
	@Test
	public void testCreate12() throws Exception {
		Operator operator1 = Operator.create("!=");
		Assert.assertEquals(NotEqualsOperator.class, operator1.getClass());
		Assert.assertEquals("!=", operator1.getText());
	}
	
	@Test
	public void testCreate13() throws Exception {
		Operator operator1 = Operator.create("&&");
		Assert.assertEquals(AndOperator.class, operator1.getClass());
		Assert.assertEquals("&&", operator1.getText());
	}
	
	@Test
	public void testCreate14() throws Exception {
		Operator operator1 = Operator.create("||");
		Assert.assertEquals(OrOperator.class, operator1.getClass());
		Assert.assertEquals("||", operator1.getText());
	}
	
	@Test
	public void testCreate15() throws Exception {
		Operator operator1 = Operator.create("!");
		Assert.assertEquals(NegationOperator.class, operator1.getClass());
		Assert.assertEquals("!", operator1.getText());
	}
	
	@Test
	public void testCreate16() throws Exception {
		Operator operator1 = Operator.create("(");
		Assert.assertEquals(ParenthesisOperator.class, operator1.getClass());
		Assert.assertEquals("(", operator1.getText());
	}
	
	@Test
	public void testCompareTo() throws Exception {
		Operator operator1 = Operator.create("+");
		Operator operator2 = Operator.create("-");
		Assert.assertEquals(0, operator1.compareTo(operator2));
	}
	
	@Test
	public void testCompareTo2() throws Exception {
		Operator operator1 = Operator.create("*");
		Operator operator2 = Operator.create("/");
		Assert.assertEquals(0, operator1.compareTo(operator2));
	}
	
	@Test
	public void testCompareTo3() throws Exception {
		Operator operator1 = Operator.create("*");
		Operator operator2 = Operator.create("+");
		Assert.assertTrue(operator1.compareTo(operator2) > 0);
	}
	
	@Test
	public void testCompareTo4() throws Exception {
		Operator operator1 = Operator.create("*");
		Operator operator2 = Operator.create("&&");
		Assert.assertTrue(operator1.compareTo(operator2) > 0);
	}
	
	@Test
	public void testCompareTo5() throws Exception {
		Operator operator1 = Operator.create("||");
		Operator operator2 = Operator.create("&&");
		Assert.assertEquals(0, operator1.compareTo(operator2));
	}
	
	@Test
	public void testCompareTo6() throws Exception {
		Operator operator1 = Operator.create("/");
		Operator operator2 = Operator.create("!");
		Assert.assertTrue(operator1.compareTo(operator2) < 0);
	}
	
	@Test
	public void testIsBinary() throws Exception {
		Operator operator1 = Operator.create("!");
		Assert.assertFalse(operator1.isBinary());
	}
	
	@Test
	public void testIsBinary2() throws Exception {
		Operator operator1 = Operator.create("&&");
		Assert.assertTrue(operator1.isBinary());
	}
	
	@Test
	public void testIsBinary3() throws Exception {
		Operator operator1 = Operator.create("+");
		Assert.assertTrue(operator1.isBinary());
	}
}
