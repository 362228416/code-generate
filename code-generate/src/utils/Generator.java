package utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

import code_generate.Activator;

public class Generator {
	
	public static final VelocityEngine engine;
	
	static {
//		Properties prop = new Properties();
//		prop.put("classpath.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
//		prop.put("classpath.resource.loader.class", Generator.class.getClassLoader().getClass().getName());
//		engine = new VelocityEngine(prop);
		engine = new VelocityEngine();
	}
	
	public static final void generate(Template template, IFile ifile) throws Exception {
		Context context = null;
		generate(template, context, ifile);
	}
	
	public static final void generate(String template, Context context, IFile ifile) throws Exception {
//		generate(engine.getTemplate(template), context, ifile);
//		URL url = Activator.getDefault().getBundle().getEntry(template);
//		InputStreamReader reader = new InputStreamReader(url.openStream());
		
		// 以前是直接读文件的，现在改成直接读内容了
		StringReader reader = new StringReader(template);
		
		StringWriter writer = new StringWriter();
		engine.evaluate(context, writer, "", reader);
		if (ifile.exists()) {
			ifile.setContents(new ByteArrayInputStream(writer.toString().getBytes()), true, true, null);
		} else {
			IProject project = ifile.getProject();
			File file = (File) ifile;
			String[] paths = file.getProjectRelativePath().segments();
			StringBuffer pb = new StringBuffer();
			// 创建父类文件夹
			for (int i = 0; i < paths.length - 1; i++) {
				pb.append(paths[i] + "/");
				IFolder folder = project.getFolder(pb.toString());
				if (!folder.exists()) {
					folder.create(true, true, null);
				}
			}
			ifile.create(new ByteArrayInputStream(writer.toString().getBytes()), true, null);
		}
		reader.close();
		
	}
	
	public static final void generate(String template, Map<String,Object> map, IFile ifile) throws Exception {
		generate(template, new VelocityContext(map), ifile);
	}
	
	public static final void generate(Template template, Context context, IFile ifile) throws Exception {
		InputStream in = new ByteArrayInputStream(getContent(template, context).getBytes());
		ifile.setContents(in, true, true, null);
	}
	
	public static final void generate(Template template, Map<String, Object> map, IFile ifile) throws Exception {
		generate(template, new VelocityContext(map), ifile);
	}
	
	public static final void generate(Template template, IFile ifile, Map<String, Object> map) throws Exception {
		InputStream in = new ByteArrayInputStream(getContent(template, map).getBytes());
		ifile.setContents(in, true, true, null);
	}
	
	public static final void generate(Template template, IFile ifile, Context context) throws Exception {
		InputStream in = new ByteArrayInputStream(getContent(template, context).getBytes());
		ifile.setContents(in, true, true, null);
	}
	
	public static final void generate(String file, Writer writer) {
		generate(file, null, writer);
	}
	
	public static final String getContent(String file) {
		return getContent(file, null);
	}
	
	public static final void generate(Template template, Context context, Writer writer) {
		template.merge(context, writer);
	}
	
	public static final String getContent(Template template, Map<String, Object> map) {
		return getContent(template, new VelocityContext(map));
	}
	
	public static final String getContent(Template template, Context context) {
		StringWriter writer = new StringWriter();
		generate(template, context, writer);
		return writer.toString();
	}
	
	
	public static final String getContent(Template template) {
		StringWriter writer = new StringWriter();
		generate(template, null, writer);
		return writer.toString();
	}
	
	public static final void generate(String file, Context context, Writer writer) {
		generate(engine.getTemplate(file), context, writer);
	}
	
	public static final String getContent(String file, Map<String, Object> map) {
//		return getContent(engine.getTemplate(file), map);
		
		StringWriter writer = new StringWriter();
		
		try {
//			URL url = Activator.getDefault().getBundle().getEntry(file);
//			InputStreamReader reader = new InputStreamReader(url.openStream());
			
			// 不读取文件，改成直接读取内容了
			StringReader reader = new StringReader(file);
			
			engine.evaluate(new VelocityContext(map), writer, "", reader);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer.toString();
		
	}
	
	public static void main(String[] args) {
//		VelocityEngine engine = new VelocityEngine();
//		Template template = engine.getTemplate("resources/test");
//		Context context = new VelocityContext();
//		context.put("name", "zhangsan");
//		Writer writer = new StringWriter();
//		template.merge(context, writer);
//		System.out.println(writer.toString());
		
		///////////
		try {
			
			StringWriter writer = new StringWriter();
			StringReader in = new StringReader("hello ${name} !");
			Context context = new VelocityContext();
			context.put("name", "zhangsan");
			Generator.engine.evaluate(context, writer, "", in);
			System.out.println(writer.toString());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
}
