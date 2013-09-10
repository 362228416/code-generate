package com.ydpp.eclipse.dialog;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.layout.CellLayout;
import org.eclipse.ui.internal.layout.Row;

@SuppressWarnings("restriction")
public class NewDialog extends WizardDialog {

	NewWizard newWizard;
	String top, repository, service, controller;

	public NewDialog(Shell parentShell, IWizard newWizard) {
		super(parentShell, newWizard);
		this.newWizard = (NewWizard) newWizard;
	}

	public NewDialog(Shell parentShell) {
		this(parentShell, new NewWizard());
	}

	public String getTopPackage() {
		return top;
	}

	public String getRepositoryPackage() {
		return repository;
	}

	public String getServicePackage() {
		return service;
	}

	public String getControllerPackage() {
		return controller;
	}

	public boolean selectRepository() {
		return true;
	}

	public boolean selectService() {
		return true;
	}

	public boolean selectController() {
		return true;
	}
	
	@Override
	protected void finishPressed() {
		top = newWizard.text.getText();
		repository = newWizard.text1.getText();
		service = newWizard.text2.getText();
		controller = newWizard.text3.getText();
		super.finishPressed();
		
	}

}

@SuppressWarnings("restriction")
class NewWizard extends Wizard {

	Label label;
	Label label1;
	Label label2;
	Label label3;
	Text text;
	Text text1;
	Text text2;
	Text text3;

	String oldVal = "";

	@Override
	public boolean performFinish() {
		return true;
	}

	@Override
	public void createPageControls(Composite parent) {
		CellLayout layout = new CellLayout(2);
		parent.setLayout(layout);
		layout.setColumn(0, new Row(120));

		label = new Label(parent, 1);
		text = new Text(parent, 1);
		label1 = new Label(parent, 1);
		text1 = new Text(parent, 1);
		label2 = new Label(parent, 1);
		text2 = new Text(parent, 1);
		label3 = new Label(parent, 1);
		text3 = new Text(parent, 1);

		label.setText("Top package name");
		label1.setText("Repository package");
		label2.setText("Service package");
		label3.setText("Controller package");

		text1.setText("repository");
		text2.setText("service");
		text3.setText("web");

		// Add Event
		text.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						String newVal = text.getText();
						queue.add(new UpdateEventEntry(oldVal, newVal));
						oldVal = newVal;
						update();
					}
				});
			}
		});

		text.addVerifyListener(new VerifyListener() {
			@Override
			public void verifyText(VerifyEvent e) {
				int len = text.getText().trim().length();
				boolean valid = len == 0 && e.text.matches("[a-zA-Z]{0,}")
						|| len > 0 && e.text.matches("[a-zA-Z0-9.]{0,}");
				if (!valid) {
					e.doit = false;
				}
			}
		});

		setWindowTitle("Code generate");
		setDefaultPageImageDescriptor(new ImageDescriptor() {
			@Override
			public ImageData getImageData() {
				return new ImageData("icons/sample.gif");
			}
		});
		setHelpAvailable(false);

	}

	Queue<UpdateEventEntry> queue = new ConcurrentLinkedQueue<UpdateEventEntry>();

	// 更新页面
	void update() {
		UpdateEventEntry event;
		event = queue.remove();
		text1.setText(swap(text1.getText(), event.oldval, event.newVal));
		text2.setText(swap(text2.getText(), event.oldval, event.newVal));
		text3.setText(swap(text3.getText(), event.oldval, event.newVal));
	}

	// 替换包名
	String swap(String text, String oldVal, String newVal) {
		String result = null;
		if (!oldVal.endsWith(".")) {
			oldVal = oldVal + ".";
		}
		if (!newVal.endsWith(".")) {
			newVal = newVal + ".";
		}
		if (text.startsWith(oldVal)) {
			result = text.replaceFirst(oldVal, newVal);
		} else {
			result = newVal + text;
		}
		if (newVal.equals("") || newVal.equals(".")) {
			while (result.startsWith(".")) {
				result = result.replaceFirst("[.]", "");
			}
		}
		return result;
	}

	@Override
	public boolean canFinish() {
		return super.canFinish();
	}

}

class UpdateEventEntry {
	String oldval;
	String newVal;

	public UpdateEventEntry(String oldval, String newVal) {
		this.oldval = oldval;
		this.newVal = newVal;
	}
}
