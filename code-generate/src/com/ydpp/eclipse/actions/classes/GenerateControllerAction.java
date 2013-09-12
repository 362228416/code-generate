package com.ydpp.eclipse.actions.classes;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;

import com.ydpp.eclipse.actions.IObjectActionDelegateAdapter;
import com.ydpp.util.GeneratorUtils;

/**
 * 
 * @author john
 *
 */
public class GenerateControllerAction extends IObjectActionDelegateAdapter {

	@Override
	public void execute(IAction action) throws Exception {
		
		InputDialog dlg = new InputDialog(null, "Press", "Package Name", "web", new IInputValidator() {
			@Override
			public String isValid(String name) {
				if (name == null || name.trim().isEmpty()) {
					return "package name not empty";
				}
				return null;
			}
		});
		
		// OK
		if (dlg.open() == 0) {
			String packageName = dlg.getValue();
			GeneratorUtils.generate(action, getString("controller"), packageName, "Controller");
		}
		
		
	}

	
}
