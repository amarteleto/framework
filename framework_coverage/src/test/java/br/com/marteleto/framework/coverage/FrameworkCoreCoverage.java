package br.com.marteleto.framework.coverage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.marteleto.framework.core.exception.FrameworkException;
import br.com.marteleto.framework.core.exception.ProducerException;
import br.com.marteleto.framework.core.util.ConverterUtil;
import br.com.marteleto.framework.core.util.CoreMessageCode;
import br.com.marteleto.framework.core.util.DateUtil;
import br.com.marteleto.framework.core.util.NumberUtil;
import br.com.marteleto.framework.core.util.ObjectUtil;
import br.com.marteleto.framework.core.util.PropertyUtil;
import br.com.marteleto.framework.core.util.ResourceUtil;
import br.com.marteleto.framework.core.util.SecurityUtil;
import br.com.marteleto.framework.enums.TestType;
import br.com.marteleto.framework.listener.TestListener;
import br.com.marteleto.framework.vo.TestEntity;

@SuppressWarnings("unused")
public class FrameworkCoreCoverage implements Serializable {
	private static final long serialVersionUID = 1L;
	protected final transient Logger logger = Logger.getLogger(FrameworkCoreCoverage.class.getName());
	
	@Test
	public void testCoreMessageCode() throws Exception {
		CoreMessageCode coreMessageCode = new CoreMessageCode();
		String temp = null;
		temp = CoreMessageCode.CORE_MESSAGE_0001;
		temp = CoreMessageCode.CORE_MESSAGE_0002;
		temp = CoreMessageCode.CORE_MESSAGE_0003;
		temp = CoreMessageCode.CORE_MESSAGE_0004;
	}
	
	@Test
	public void testTestListener() throws Exception {
		TestListener testListener = new TestListener();
		assertTrue(testListener.executeTest());
	}
	
	@Test
	public void testTestEntity() throws Exception {
		TestEntity value1 = null;
		TestEntity value2 = new TestEntity();
		value2.setId(1l);
		value2.toString();
		value2.hashCode();
		assertFalse(value2.equals(value1));
		assertFalse(value2.equals(new Object()));
		value1 = new TestEntity();
		value1.toString();
		value1.hashCode();
		assertFalse(value1.equals(value2));
		assertFalse(value2.equals(value1));
		value1.setId(1l);
		assertTrue(value1.equals(value2));
		value1 = value2;
		assertTrue(value1.equals(value2));
	}
	
	@Test
	public void testException() throws Exception {
		IOException IOException = new IOException();
		FrameworkException exception = new FrameworkException();
		exception = new FrameworkException("test 2");
		exception = new FrameworkException("test 2",IOException);
		exception = new FrameworkException(IOException);
		IOException = new IOException("test 2");
		exception = new FrameworkException(IOException);
		assertNotNull(exception.getError());
		assertNotNull(exception.getMessage());
		ProducerException producerException = new ProducerException();
		producerException = new ProducerException("test 2");
		producerException = new ProducerException("test 2",IOException);
		producerException = new ProducerException(IOException);
		IOException = new IOException("test 2");
		producerException = new ProducerException(IOException);
		assertNotNull(exception.getError());
		assertNotNull(exception.getMessage());
	}
	
	@Test
	public void testPropertyUtil() throws Exception {
		PropertyUtil propertyUtil = new PropertyUtil();
		PropertyUtil.addProperty("remote.properties");
		PropertyUtil.setProperty(PropertyUtil.VERSION, "0.0.0");
		assertEquals("0.0.0",PropertyUtil.getVersion());
		assertEquals("Framework Core Test",PropertyUtil.getApplication());
		PropertyUtil.removeProperty(PropertyUtil.APPLICATION);
		assertNull("",PropertyUtil.getApplication());
	}
	
	@Test
	public void testResourceUtil() throws Exception {
		ResourceUtil resourceUtil = new ResourceUtil();
		ResourceUtil.addResource("messages");
		ResourceUtil.setLocale(new Locale("pt","BR"));
		ResourceUtil.getLocale();
		String [] param = {"test","test"};
		assertEquals("{0} - {1} Successfully initialized.",ResourceUtil.getResource("CORE_MESSAGE_0001"));
		assertEquals("test - {1} Successfully initialized.",ResourceUtil.getResource("CORE_MESSAGE_0001","test"));
		assertEquals("test - test Successfully initialized.",ResourceUtil.getResource("CORE_MESSAGE_0001",param));
		assertEquals("xxxx",ResourceUtil.getResource("xxxx"));
	}
	
	@Test
	public void testConverterUtil() throws Exception {
		ConverterUtil converterUtil = new ConverterUtil();
		assertEquals(Double.valueOf(10d),ConverterUtil.convert(BigDecimal.valueOf(10d), Double.class));
		assertEquals(BigDecimal.valueOf(10d), ConverterUtil.bigDecimalToBigDecimal(BigDecimal.valueOf(10d)));
		assertEquals(Double.valueOf(10d), ConverterUtil.bigDecimalToDouble(BigDecimal.valueOf(10d)));
		assertEquals(Integer.valueOf(10), ConverterUtil.bigDecimalToInteger(BigDecimal.valueOf(10d)));
		assertEquals(Long.valueOf(10), ConverterUtil.bigDecimalToLong(BigDecimal.valueOf(10d)));
		assertEquals(String.valueOf(10.0), ConverterUtil.bigDecimalToString(BigDecimal.valueOf(10d)));
		assertEquals(Integer.valueOf(10), ConverterUtil.bigIntegerToInteger(BigInteger.valueOf(10)));
		assertEquals(Long.valueOf(10), ConverterUtil.bigIntegerToLong(BigInteger.valueOf(10)));
		assertEquals(Integer.valueOf(1), ConverterUtil.booleanToInteger(true));
		assertEquals(Integer.valueOf(0), ConverterUtil.booleanToInteger(false));
		assertEquals(String.valueOf("true"), ConverterUtil.booleanToString(true));
		assertEquals(String.valueOf("false"), ConverterUtil.booleanToString(false));
		assertEquals(BigDecimal.valueOf(10.0), ConverterUtil.doubleToBigDecimal(10d));
		assertEquals(Long.valueOf(10), ConverterUtil.doubleToLong(10d));
		assertEquals(BigDecimal.valueOf(10), ConverterUtil.integerToBigDecimal(10));
		assertEquals(Boolean.valueOf("true"), ConverterUtil.integerToBoolean(1));
		assertEquals(Boolean.valueOf("false"), ConverterUtil.integerToBoolean(0));
		assertEquals(Long.valueOf(10), ConverterUtil.integerToLong(10));
		assertEquals(String.valueOf(10), ConverterUtil.integerToString(10));
		assertEquals(Integer.valueOf(10), ConverterUtil.longToInteger(10l));
		assertEquals(BigDecimal.valueOf(10), ConverterUtil.stringToBigDecimal("10"));
		assertEquals(Boolean.valueOf("true"), ConverterUtil.stringToBoolean("true"));
		assertEquals(Boolean.valueOf("false"), ConverterUtil.stringToBoolean("false"));
		assertEquals(Integer.valueOf(10), ConverterUtil.stringToInteger("10"));
		assertEquals(TestType.A, ConverterUtil.convert("A",TestType.class));
		assertEquals(TestType.A, ConverterUtil.stringToEnum("A",TestType.class));
		assertEquals(TestType.B, ConverterUtil.characterToEnum(Character.valueOf("B".toCharArray()[0]),TestType.class));
		assertEquals("A", ConverterUtil.characterToString(Character.valueOf("A".toCharArray()[0])));
		assertEquals(Double.valueOf(10d),ConverterUtil.convert(Double.valueOf(10d), Double.class));
		assertNull("",ConverterUtil.convert(null, Double.class));
		assertNull("",ConverterUtil.stringToInteger(null));
		assertNull("",ConverterUtil.stringToInteger(""));
		assertNull("",ConverterUtil.stringToBigDecimal(null));
		assertNull("",ConverterUtil.stringToBigDecimal(""));
		assertNull("",ConverterUtil.bigDecimalToString(null));
	}
	
	@Test
	public void testObjectUtil() throws Exception {
		ObjectUtil objectUtil = new ObjectUtil();
		String methodName = "id";
		Long result1 = 2l;
		Long result2 = 3l;
		TestEntity value1 = new TestEntity();
		TestEntity value2 = new TestEntity();
		value2.setId(1l);
		ObjectUtil.copy(value1, value2);
		ObjectUtil.copy(value1, null);
		
		assertEquals(value1, value2);
		assertNotNull("",ObjectUtil.getFieldByName(TestEntity.class, methodName));
		assertNotNull("",ObjectUtil.findMethodGet(value1, methodName));
		assertNotNull("",ObjectUtil.findMethodSet(value1, methodName));
		assertNotNull("",ObjectUtil.findMethodSet(value1, methodName,Long.class));
		
		ObjectUtil.setObjectValue(value1, methodName, result1);
		ObjectUtil.setObjectValue(value2, methodName, Long.class, result2);
		assertEquals(result1, ObjectUtil.getObjectValue(value1, methodName));
		assertEquals(result2, ObjectUtil.getObjectValue(value2, methodName));
		
		assertNull("",ObjectUtil.getFieldByName(null, null));
		assertNull("",ObjectUtil.getFieldByName(TestEntity.class, "xxxx"));
		assertNull("",ObjectUtil.findMethodGet(null, null));
		assertNull("",ObjectUtil.findMethodGet(value1, "xxxx"));
		
		ObjectUtil.setObjectValue(null, null, null);
		assertEquals(result1, ObjectUtil.getObjectValue(value1, methodName));
		ObjectUtil.setObjectValue(value1, "xxxx", "10");
		assertEquals(result1, ObjectUtil.getObjectValue(value1, methodName));
		assertNull("",ObjectUtil.findMethodGet(null, null));
	}
	
	@Test
	public void testNumberUtil() throws Exception {
		NumberUtil numberUtil = new NumberUtil();
		Double value = 100300.25d;
		assertEquals("100.300,25", NumberUtil.convertNumberToString("#,###.##", value));
		assertEquals("R$ 100.300,25", NumberUtil.formatCurrency(value));
		assertNull("",NumberUtil.convertNumberToString("", null));
		assertNull("",NumberUtil.convertNumberToString("", value));
		assertNull("",NumberUtil.convertNumberToString(null, null));
		assertNull("",NumberUtil.formatCurrency(null));
		assertNull("",NumberUtil.convertNumberToString("a", "a"));
		assertNull("",NumberUtil.formatCurrency("a"));
	}
	
	@Test
	public void testDateUtil() throws Exception {
		DateUtil dateUtil = new DateUtil();
		DateUtil.currentDate();
		Long diff = 1l;
		Long diffInMillis = diff * 24 * 60 * 60 * 1000;
		Date value1 = DateUtil.convertStringToDate("dd/MM/yyyy","11/12/2017");
		Date value2 = DateUtil.dateAdd(value1, diff.intValue());
		assertEquals("11/12/2017", DateUtil.convertDateToString("dd/MM/yyyy", value1));
		assertEquals("12/12/2017", DateUtil.convertDateToString("dd/MM/yyyy", value2));
		assertEquals(value1, DateUtil.convertStringToDate("dd/MM/yyyy","11/12/2017"));
		assertEquals(value1, DateUtil.formatDate("dd/MM/yyyy",value1));
		assertEquals(diffInMillis, DateUtil.dateDiff(value1,value2));
		assertNull("",DateUtil.dateDiff(null, value1));
		assertNull("",DateUtil.dateDiff(value2, null));
		assertNull("",DateUtil.dateDiff(null, null));
		assertNull("",DateUtil.formatDate(null, null));
		assertNull("",DateUtil.convertStringToDate(null, null));
		assertNull("",DateUtil.convertDateToString(null, null));
	}
	
	@Test
	public void testSecurityUtil() throws Exception {
		SecurityUtil securityUtil = new SecurityUtil();
		String value = "123456";
		assertEquals("e10adc3949ba59abbe56e057f20f883e", SecurityUtil.convertStringToMd5(value));
		assertEquals("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92", SecurityUtil.convertStringToSHA256(value));
		assertEquals("ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413", SecurityUtil.convertStringToSHA512(value));
		assertNull("",SecurityUtil.convertStringToMd5(""));
		assertNull("",SecurityUtil.convertStringToMd5(null));
		String key ="0123456789012345";
		String token = SecurityUtil.encryptAES(value, key);
		String tokenDecode = SecurityUtil.decryptAES(token, key);
		assertEquals(value, tokenDecode);
	}
}
