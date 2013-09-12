package com.ydpp.eclipse.actions.classes;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;

import com.ydpp.eclipse.actions.IObjectActionDelegateAdapter;
import com.ydpp.util.GeneratorUtils;

/**
 * 抽象的类生成器，该类提供一个基本功能就是弹出输入框，让用户输入
 * 包信息，在用户点击确定后将会调用run方法，生成代码操作可在run方法
 * 中进行，子类通过重写getDefaultValue方法来给输入框设置默认值
 * @author john
 */
public abstract class SimpleClassGenerateor extends
		IObjectActionDelegateAdapter {
	
	@Override
	public void execute(IAction action) throws Exception {
		InputDialog dialog = new InputDialog(null, "Press", "Package Name", getDefaultValue(), new IInputValidator() {
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

	protected String getDefaultValue() {
		return "";
	}
	
	protected void generate(IAction action, String key, String packageName, String suffix) throws Exception {
		GeneratorUtils.generate(action, getString(key), packageName, suffix);
	}
	
	protected abstract void run(IAction action, String packageName) throws Exception;
	
	
}
