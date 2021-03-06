package utils;

import java.util.Collections;
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
				
				
				Map<String, Object> map = Collections.singletonMap("info", (Object)info);
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
			Map<String, Object> map = Collections.singletonMap("info", (Object)info);
			Generator.generate(template, map, ifile);
		}
	}
	
}
