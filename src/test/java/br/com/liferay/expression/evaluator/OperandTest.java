package br.com.liferay.expression.evaluator;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

import br.com.liferay.expression.evaluator.operand.BigDecimalOperand;
import br.com.liferay.expression.evaluator.operand.BigIntegerOperand;
import br.com.liferay.expression.evaluator.operand.BooleanOperand;
import br.com.liferay.expression.evaluator.operand.DoubleOperand;
import br.com.liferay.expression.evaluator.operand.FloatOperand;
import br.com.liferay.expression.evaluator.operand.IntegerOperand;
import br.com.liferay.expression.evaluator.operand.LongOperand;
import br.com.liferay.expression.evaluator.operand.Operand;
import br.com.liferay.expression.evaluator.operand.StringOperand;

/**
 * @author Leonardo Barros
 */
public class OperandTest {

	@Test
	public void testCreate() throws Exception {
		Operand operand1 = Operand.create("test");
		Assert.assertNotNull(operand1);
		Assert.assertEquals(StringOperand.class, operand1.getClass());
		Assert.assertEquals(String.class, operand1.getType());
		Assert.assertEquals("test", operand1.getText());
	}
	
	@Test
	public void testCreate2() throws Exception {
		Operand operand1 = Operand.create("true");
		Assert.assertNotNull(operand1);
		Assert.assertEquals(BooleanOperand.class, operand1.getClass());
		Assert.assertEquals(Boolean.class, operand1.getType());
	}
	
	@Test
	public void testCreate3() throws Exception {
		Operand operand1 = Operand.create("false");
		Assert.assertNotNull(operand1);
		Assert.assertEquals(BooleanOperand.class, operand1.getClass());
		Assert.assertEquals(Boolean.class, operand1.getType());
	}
	
	@Test
	public void testCreate4() throws Exception {
		Operand operand1 = Operand.create("123");
		Assert.assertNotNull(operand1);
		Assert.assertEquals(IntegerOperand.class, operand1.getClass());
		Assert.assertEquals(Integer.class, operand1.getType());
	}
	
	@Test
	public void testCreate5() throws Exception {
		Operand operand1 = Operand.create("0");
		Assert.assertNotNull(operand1);
		Assert.assertEquals(IntegerOperand.class, operand1.getClass());
		Assert.assertEquals(Integer.class, operand1.getType());
	}
	
	@Test
	public void testCreate6() throws Exception {
		Operand operand1 = Operand.create("25L");
		Assert.assertNotNull(operand1);
		Assert.assertEquals(LongOperand.class, operand1.getClass());
		Assert.assertEquals(Long.class, operand1.getType());
	}
	
	@Test
	public void testCreate7() throws Exception {
		Operand operand1 = Operand.create("4981274891274981749812749821798712984719847918758957895718951789571285921758971598715928715981725987125981275821791857");
		Assert.assertNotNull(operand1);
		Assert.assertEquals(BigIntegerOperand.class, operand1.getClass());
		Assert.assertEquals(BigInteger.class, operand1.getType());
	}
	
	@Test
	public void testCreate8() throws Exception {
		Operand operand1 = Operand.create("2.3");
		Assert.assertNotNull(operand1);
		Assert.assertEquals(FloatOperand.class, operand1.getClass());
		Assert.assertEquals(Float.class, operand1.getType());
	}
	
	@Test
	public void testCreate9() throws Exception {
		Operand operand1 = Operand.create("3.7d");
		Assert.assertNotNull(operand1);
		Assert.assertEquals(DoubleOperand.class, operand1.getClass());
		Assert.assertEquals(Double.class, operand1.getType());
	}
	
	@Test
	public void testCreate10() throws Exception {
		Operand operand1 = Operand.create("39873128937129874981274714719284781924789124791827895719857189275819275891275891758971895718925719857918275891758917258917598719285718927598157895789179874958347589374896739867935978471897981274981479814718927589157987984579487398475893475893478957349857934875983475897248917894719847298572985748397534895743985793487538947589347589347598734958734587349.7");
		Assert.assertNotNull(operand1);
		Assert.assertEquals(BigDecimalOperand.class, operand1.getClass());
		Assert.assertEquals(BigDecimal.class, operand1.getType());
	}
}
