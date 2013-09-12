package com.ydpp.eclipse.actions.file;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.internal.core.CompilationUnit;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

import com.ydpp.eclipse.actions.IObjectActionDelegateAdapter;
import com.ydpp.eclipse.preferences.PreferenceInitializer;
import com.ydpp.util.ClassInfo;
import com.ydpp.util.Generator;
import com.ydpp.util.GeneratorUtils;
import com.ydpp.util.PluginUtils;

/**
 * 通用的文件生成器 <br/>
 * 通过在plugin.xml里面配置Action <br/>
 * 生成器会根据该Action的ID从Store中获取模板数据 <br/>
 * 并且按照约定会到Store中找到以ID-suffix的命名的文件名后缀 <br/>
 * 如：myibatis-mapper-suffix=Mapper.xml  <br/>
 * 那么生成的文件名如下：domain+suffix, 如UserMapper.xml <br/>
 * suffix在resources/config.properties中配置
 * @author john
 */
@SuppressWarnings("restriction")
public class CommonFileGenerator extends IObjectActionDelegateAdapter {

	
	@Override
	public void execute(IAction action) throws Exception {
		final String id = action.getId();
		IProject project = PluginUtils.getProject(action); 
		IContainer container = PluginUtils.getContainer(action);
		
		ContainerSelectionDialog dialog = new ContainerSelectionDialog(getShell(), container, true, "Select path");
		if (dialog.open() == 0) {
			Object[] result = dialog.getResult();
			Path path = (Path) result[0];
			List<CompilationUnit> list = PluginUtils.getCompilationUnits(action);
			for (CompilationUnit file : list) {
				
				final String name = "-name";
				for (String key : PreferenceInitializer.STORE_KEYS) {
					if (key.startsWith(id) && key.endsWith(name)) {
						Map<String, Object> map = new HashMap<String, Object>();
						ClassInfo info = GeneratorUtils.addVariable(map, file, "", "");
						String fileName = path.toString().replace("/" + project.getName(), "") + "/" + getMessage(key, info.getClassName());
						IFile ifile = PluginUtils.createFile(project, fileName);
						Generator.generate(getString(key.replace(name, "")), map, ifile);
					}
				}
				
			}
			
		}
		
		
	}
	
	
}
