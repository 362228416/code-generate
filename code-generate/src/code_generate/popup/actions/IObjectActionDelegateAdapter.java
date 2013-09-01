package code_generate.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class IObjectActionDelegateAdapter implements IObjectActionDelegate {

	@Override
	public void run(IAction action) {
		try {
			execute(action);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void execute(IAction action) throws Exception {
		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart workbenchPart) {
		this.workbenchPart = workbenchPart;
	}
	
	IWorkbenchPart workbenchPart;
	ISelection selection;
	

}
