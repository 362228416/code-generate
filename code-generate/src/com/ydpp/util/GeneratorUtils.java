package com.ydpp.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.internal.core.CompilationUnit;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.internal.PluginAction;


@SuppressWarnings("restriction")
public class GeneratorUtils {
	
	public static void generate(IAction action, String template, String suffix) {
//		PluginAction a = (PluginAction) action;
//		StructuredSelection select = (StructuredSelection) a.getSelection();
//		Object obj = select.getFirstElement();
//		if (obj instanceof CompilationUnit) {
//			try {
//				ClassInfo info = new ClassInfo(PluginUtils.getSourceType(obj), suffix);
//				IProject project = PluginUtils.getProject(obj);
//				String classFile = "src/" + info.getPackageName() + "." + info.getClassName() + suffix;
//				IFile ifile = PluginUtils.createJavaFile(project, classFile);
//				Map<String, Object> map = Collections.singletonMap("info", (Object)info);
//				Generator.generate(template, map, ifile);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		PluginAction a = (PluginAction) action;
		StructuredSelection select = (StructuredSelection) a.getSelection();
		Object obj = select.getFirstElement();
		if (obj instanceof CompilationUnit) {
			try {
//				ClassInfo info = new ClassInfo(PluginUtils.getSourceType(obj), suffix);
				ClassInfo info = PluginUtils.getClassInfo(obj);
				info.setSuffix(suffix);
				IProject project = PluginUtils.getProject(obj);
//				String classFile = info.getPath() + info.getPackageName() + "." + info.getClassName() + suffix;
				String classFile = info.getPath() + "/" + info.getClassName() + suffix;
				IFile ifile = PluginUtils.createJavaFile(project, classFile);
//				System.out.println(ifile.getFullPath());
//				System.out.println(ifile.getName());
//				System.out.println(ifile.exists());
				
				
//				Map<String, Object> map = Collections.singletonMap("info", (Object)info);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("info", info);
				addVariable(map, info);
				
				Generator.generate(template, map, ifile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void generate(IAction action, String template, String packageName, String suffix) throws Exception {
		List<CompilationUnit> list = PluginUtils.getCompilationUnits(action);
		for (CompilationUnit file : list) {
			ClassInfo info = PluginUtils.getClassInfo(file);
//			info.setPackageName(packageName);
			info.setTargetPackage(packageName);
			info.setSuffix(suffix);
			IProject project = PluginUtils.getProject(file);
			String classFile = info.getPath() + "/" + info.getClassName() + suffix;
			IFile ifile = PluginUtils.createJavaFile(project, classFile);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("info", info);
			addVariable(map, info);
			
			// 添加
			
			Generator.generate(template, map, ifile);
		}
	}
	
	/**
	 * <p>
	 * 添加模板变量<br/>
	 * 在这里添加的变量模板中都可以通过${变量名} 拿到值<br/>
	 * 如： ${class}
	 * </p>
	 * @param map
	 * @param info
	 */
	static void addVariable(Map<String, Object> map, ClassInfo info) {
		map.put("simpleName", info.getSimpleName());
		map.put("class", info.getClassName());
		map.put("fullName", info.getPackageName() + "." + info.getClassName());
		map.put("path", info.getPath());
		map.put("suffix", info.getSuffix());
		map.put("package", info.getPackageName());
		map.put("target", info.getTargetPackage());
		map.put("time", new Date(System.currentTimeMillis()));
	}
	
}
