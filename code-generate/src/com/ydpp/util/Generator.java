package com.ydpp.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;

/**
 * 代码生成器
 * @author john
 *
 */
@SuppressWarnings("restriction")
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
		
		StringWriter writer = new StringWriter();
		
		
		engine.evaluate(context, writer, "", new StringReader(template));
		
		if (ifile.exists()) {
			ifile.setContents(new ByteArrayInputStream(writer.toString().getBytes()), true, true, null);
		} else {
			IProject project = ifile.getProject();
			File file = (File) ifile;
			
			String[] paths = file.getProjectRelativePath().segments();
			StringBuffer pb = new StringBuffer();
			
			// 创建父类文件夹
//			for (int i = 0; i < paths.length - 1; i++) {
//				String str = paths[i] + "/"; 
//				pb.append(str);
//				IFolder folder = project.getFolder(pb.toString());
//				if (!folder.exists()) {
//					folder.create(true, true, null);
//				}
//			}
			// 以上代码编译，没问题运行也没问题，但是导出的时候就报错，原因不详暂且留着
			int i, count = paths.length - 1;
			for (i = 0; i < count; i++) {
				String str = paths[i] + "/"; 
				pb.append(str);
				IFolder folder = project.getFolder(pb.toString());
				if (!folder.exists()) {
					folder.create(true, true, null);
				}
			}
			
			ifile.create(new ByteArrayInputStream(writer.toString().getBytes()), true, null);
		}
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
			
			engine.evaluate(new VelocityContext(map), writer, "", new StringReader(file));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer.toString();
		
	}
	
	
}
