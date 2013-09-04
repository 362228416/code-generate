package code_generate.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ConfigPage extends PreferencePage implements IWorkbenchPreferencePage{

	@Override
	protected Control createContents(Composite parent) {
		
		Composite panel = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		panel.setLayout(layout);
		
		Text text = new Text(panel, SWT.BORDER | SWT.MULTI | SWT.WRAP);
		text.setLayoutData(new GridData(GridData.FILL_BOTH));
		return panel;
	}

	@Override
	public void init(IWorkbench workbench) {
		
	}

	
}
