package code_generate.popup.actions;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;
import java.util.HashMap;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.junit.Before;
import org.junit.Test;

import utils.Generator;

public class GeneratorTest {

	Generator generator;
	
	String file;
	
	VelocityEngine engine;
	
	Template template;
	
	@Before
	public void setUp() {
		engine = new VelocityEngine();
		generator = new Generator();
		file = "resources/test";
		template = engine.getTemplate(file);
	}
	
	@Test
	public void testGenerateTemplateContextWriter() {
		Context context = new VelocityContext();
		context.put("name", "zhangsan");
		StringWriter writer = new StringWriter(); 
		Generator.generate(template, context, writer);
		assertEquals("Hello, zhangsan!", writer.toString());
	}

	@Test
	public void testGetContentTemplateMapOfStringObject() {
		HashMap<String, Object> context = new HashMap<String,Object>();
		context.put("name", "zhangsan");
		String result = Generator.getContent(template, context);
		assertEquals("Hello, zhangsan!", result);
	}
	

	@Test
	public void testGenerateStringContextWriter() {
		Context context = new VelocityContext();
		context.put("name", "zhangsan");
		StringWriter writer = new StringWriter();
		Generator.generate(file, context, writer);
		assertEquals("Hello, zhangsan!", writer.toString());
	}

	@Test
	public void testGetContentStringMapOfStringObject() {
		HashMap<String, Object> context = new HashMap<String,Object>();
		context.put("name", "zhangsan");
		String result = Generator.getContent(file, context);
		assertEquals("Hello, zhangsan!", result);
	}

}
