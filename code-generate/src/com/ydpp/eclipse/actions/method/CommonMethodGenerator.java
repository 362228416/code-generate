package com.ydpp.eclipse.actions.method;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jdt.internal.core.CompilationUnit;
import org.eclipse.jdt.internal.core.SourceType;
import org.eclipse.jface.action.IAction;

import com.ydpp.eclipse.actions.IObjectActionDelegateAdapter;
import com.ydpp.util.GeneratorUtils;
import com.ydpp.util.PluginUtils;

/**
 *
 * @author john
 */
@SuppressWarnings("restriction")
public class CommonMethodGenerator extends IObjectActionDelegateAdapter {

	@Override
	public void execute(IAction action) throws Exception {
		
		List<CompilationUnit> files = PluginUtils.getCompilationUnits(action);
		for (CompilationUnit file : files) {
			
			SourceType type = PluginUtils.getSourceType(file);
			Map<String, Object> map = new HashMap<String, Object>();
			
			String fileContent = getString(action.getId());
			String[] imports = null;
			String content = null;
			
			String[] ips = StringUtils.substringsBetween(fileContent, "<import>", "</import>");
			if (ips.length > 0) {
				imports = ips[0].split(",");
			}
			
			String[] cs = StringUtils.substringsBetween(fileContent, "<content>", "</content>");
			if (cs.length > 0) {
				content = cs[0];
			}
			
			
			if (content != null) {
				// 添加方法
				String methods = GeneratorUtils.getContent(map, action, content);
				type.createMethod(methods, null, true, null);
			}
			
			
			// 导入包
			if (imports != null) {
				for (String p : imports) {
					file.createImport(p.trim(), null, null);
				}
			}
			
			
			file.save(null, true);
		}
		
	}
	
}
