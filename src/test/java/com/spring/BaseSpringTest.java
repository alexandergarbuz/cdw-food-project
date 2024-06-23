package com.spring;

import java.io.InputStream;

import junit.framework.TestCase;

import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.food.util.SpringConstants;

public abstract class BaseSpringTest extends TestCase {
	
	protected ApplicationContext ctx = null;

	protected void setUp() throws Exception {
		super.setUp();
		ctx = new ClassPathXmlApplicationContext(SpringConstants.SPRING_CONFIGS);
	}
	protected void tearDown() throws Exception {
		ctx = null;
		super.tearDown();
	}
	/**
	 * Returns bean from spring context or throws AssertionFailedError if bean is NULL.
	 * @param beanName name of the bean to find
	 * @param errorMessageIfNull error message to display if bean is undefined.
	 * @return bean from the Spring context.
	 */
	public Object getBean(final String beanName, final String errorMessageIfNull) {
		Object obj = ctx.getBean(beanName);
		assertNotNull(errorMessageIfNull, obj);
		return obj;
	}
	/**
	 * Reads the content of the file from the classpath into a string 
	 * for future comparison with expected test output.  
	 * @param pathToFile path to the file.
	 * @return content of the file.
	 * @throws Exception in case unable to read the file.
	 */
	protected final String readFileContent(final String pathToFile) throws Exception {
		InputStream io = this.getClass().getResourceAsStream(pathToFile);
		String output = IOUtils.toString(io);
		IOUtils.closeQuietly(io);
		return output;
	}

}