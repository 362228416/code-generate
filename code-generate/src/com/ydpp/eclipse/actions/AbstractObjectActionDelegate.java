package com.ydpp.eclipse.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public abstract class AbstractObjectActionDelegate implements
		IObjectActionDelegate {

	@Override
	public void run(IAction action) {
		execute(action);
		flush();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {

	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart workbenchPart) {

	}
	
	protected abstract void execute(IAction action);

	protected void flush() {
		
	}
	
}
