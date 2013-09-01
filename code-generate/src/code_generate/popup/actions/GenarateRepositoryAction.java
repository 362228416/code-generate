package code_generate.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;

import utils.GeneratorUtils;

public class GenarateRepositoryAction extends IObjectActionDelegateAdapter {


	@Override
	public void execute(IAction action) throws Exception {
		
		InputDialog dlg = new InputDialog(null, "Press", "Package Name", "repository", new IInputValidator() {
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
			GeneratorUtils.generate(action, "resources/repository", packageName, "Repository");
			GeneratorUtils.generate(action, "resources/repositoryImpl", packageName, "RepositoryImpl");
		}
		
		
	}


}
