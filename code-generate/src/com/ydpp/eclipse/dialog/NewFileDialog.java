package com.ydpp.eclipse.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.PathEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;
import org.eclipse.ui.part.FileEditorInput;

/**
 *
 * @author john
 */
public class NewFileDialog extends Dialog {

	protected NewFileDialog(Shell parentShell) {
		super(parentShell);
	}

	public String getStringValue() {
		return "";
	}
	
	@Override
	protected Control createContents(Composite parent) {
		Composite panel = new Composite(parent, SWT.NONE);
//		new PathEditor("filePath", "Directory", "Directory:", parent);
//		IPathEditorInput
		
//		new Filein
		
		
//		dirEditor = new DirectoryFieldEditor("exportFile", "Directory", panel);
		return super.createContents(parent);
	}
	
}
