package com.ydpp.util;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.internal.core.CompilationUnit;
import org.eclipse.jdt.internal.core.SourceType;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.internal.PluginAction;

import com.ydpp.eclipse.config.CodeConfig;

/**
 * 生成器辅助类
 * @author john
 *
 */
@SuppressWarnings("restriction")
public class GeneratorUtils {
	
	/**
	 * 
	 * @param action
	 * @param template
	 * @param suffix
	 */
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
				
				Map<String, Object> map = new HashMap<String, Object>();
				ClassInfo info = addVariable(map, obj, "", suffix);
				
//				ClassInfo info = PluginUtils.getClassInfo(obj);
//				info.setSuffix(suffix);
				IProject project = PluginUtils.getProject(obj);
				String classFile = info.getPath() + "/" + info.getClassName() + suffix;
				IFile ifile = PluginUtils.createJavaFile(project, classFile);
				
				
//				map.put("info", info);
				
//				addVariable(map, info);
				
				Generator.generate(template, map, ifile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 生成Class文件
	 * @param action
	 * @param template
	 * @param packageName
	 * @param suffix
	 * @throws Exception
	 */
	public static void generate(IAction action, String template, String packageName, String suffix) throws Exception {
		List<CompilationUnit> list = PluginUtils.getCompilationUnits(action);
		for (CompilationUnit file : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			ClassInfo info = addVariable(map, file, packageName, suffix);
			
			IProject project = PluginUtils.getProject(file);
			String classFile = info.getPath() + "/" + info.getClassName();
			IFile ifile = PluginUtils.createJavaFile(project, classFile);
			
			String domain, dao, service, domainPkg, daoPkg, servicePkg;
			domain = dao = service = domainPkg = daoPkg = servicePkg = null;
			
//			String id = action.getId();
			if (suffix.contains("dao") && !suffix.contains("spring")) {
				domain = info.getDomain();
				domainPkg = info.getPackageName();
				if (suffix.contains("impl")) {
					
				} else {
					dao = info.getClassName();
					daoPkg = info.getTargetPackage();
				}
			}
			if (suffix.contains("spring") && suffix.contains("service")) {
				domain = info.getDomain();
				domainPkg = info.getPackageName();
				if (suffix.contains("impl")) {
					
				} else {
					service = info.getClassName();
					servicePkg = info.getTargetPackage();
				}
			}
			
			if (domain == null) {
				dao = info.getDomain();
//				domain = 
			}
			
//			if (suffix.contains("test") && !suffix.contains(""))
			
			// update and find history
			CodeHistoryManager.putHistory(action, domain, dao, service, domainPkg, daoPkg, servicePkg);
			History histry = CodeHistoryManager.getHistry(action, domain, dao, service);
			map.put("domain", histry.domain == null ? info.getDomain() : histry.getDomain());
			map.put("dao", histry.dao);
			map.put("service", histry.service);
			map.put("domainPkg", histry.domainPkg);
			map.put("daoPkg", histry.daoPkg);
			map.put("servicePkg", histry.servicePkg);
			
			Generator.generate(template, map, ifile);
		}
	}
	
//	
//	public static void generateFile(IAction action, String content, String dir, String fileName) {
//		
//		
//	}
	
	
	
	/**
	 * <p>
	 * 添加模板变量<br/>
	 * 在这里添加的变量模板中都可以通过${变量名} 拿到值<br/>
	 * 如： ${class}
	 * </p>
	 * @param map
	 * @param info
	 * @throws Exception 
	 */
	public static ClassInfo addVariable(Map<String, Object> map, Object obj, String packageName, String suffix) throws Exception {
		ClassInfo info = PluginUtils.getClassInfo(obj);
		info.setTargetPackage(packageName);
		info.setSuffix(suffix);
		info.setClassName(CodeConfig.getMessage(suffix, info.getClassName()));
		
		
		// 类字段与方法
		SourceType type = PluginUtils.getSourceType(obj);
		if (type != null) {
			List<String> fieldList = new LinkedList<String>();			// 类字段
			List<String> allMethodList = new LinkedList<String>();		// 类所有方法
			List<String> methodList = new LinkedList<String>();			// 类方法，不含包get,set
			
			IField[] fields = type.getFields();
			for (IField iField : fields) {
				fieldList.add(iField.getElementName());
			}
			IMethod[] methods = type.getMethods();
			for (IMethod iMethod : methods) {
				String name = iMethod.getElementName();
				allMethodList.add(name);
				if (!name.startsWith("get") && !name.startsWith("set")) {
					methodList.add(name);
				}
			}
			
			map.put("fields", fieldList);
			map.put("allMethods", allMethodList);
			map.put("methods", methodList);
			
		}
		
		// 短变量名
		map.put("info", info);
		map.put("simpleName", info.getSimpleName());
		map.put("class", info.getClassName());
		map.put("domain", info.getDomain());
		map.put("fullName", info.getPackageName() + "." + info.getDomain());
		map.put("path", info.getPath());
		map.put("suffix", info.getSuffix());
		map.put("package", info.getPackageName());
		map.put("target", info.getTargetPackage());
		map.put("time", new Date(System.currentTimeMillis()));
		return info;
	}
	
	
	/**
	 * 添加模板类变量到上下文中，合成模板，返回结果
	 * @param map
	 * @param obj
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static String getContent(Map<String, Object> map, Object obj, String content) throws Exception {
		addVariable(map, obj, "", "");
		return Generator.getContent(content, map);
	}
	
	
	
	
	
	
}
