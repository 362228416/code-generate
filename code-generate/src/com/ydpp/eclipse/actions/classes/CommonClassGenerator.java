package com.ydpp.eclipse.actions.classes;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;

import com.ydpp.eclipse.preferences.PreferenceInitializer;

/**
 * <p>
 * 通用的Class生成器
 * 通过获取被点击菜单项的ID，自动搜索本插件Store所有以该该ID开头的key
 * 按照约定key所对应的模板文件应该位于项目的reources/classes目录，名称
 * 命名为：ID.vm，如：struts-action.vm
 * 控制器会根据resources/config.properties配置文件的找到该ID所对应的后缀，以及默认值包名称
 * 后缀规则如下：ID = 后缀，默认值包名称规则：ID-package = 默认值包名称
 * 如:  struts-action-suffix = Action
 * 		struts-action-package = actions
 * </p>
 * @author john
 */
public class CommonClassGenerator extends SimpleClassGenerateor {

	@Override
	protected void run(IAction action, String packageName) throws Exception {
		String id = action.getId();
		final String name = "-name";
		for (String key : PreferenceInitializer.STORE_KEYS) {
			if (key.startsWith(id) && key.endsWith(name)) {
				generate(action, key.replace(name, ""), packageName, key);
			}
		}
	}
	
	@Override
	public void execute(IAction action) throws Exception {
		InputDialog dialog = new InputDialog(null, "Press", "Package Name", getString(action.getId() + "-package"), new IInputValidator() {
			@Override
			public String isValid(String name) {
				if (name == null || name.trim().isEmpty()) {
					return "package name not empty";
				}
				return null;
			}
		});
		if (dialog.open() == 0) {
			run(action, dialog.getValue());
		}
	}
	
}
