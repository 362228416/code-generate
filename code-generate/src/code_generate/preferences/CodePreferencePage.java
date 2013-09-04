package code_generate.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import code_generate.Activator;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class CodePreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public CodePreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
//		setDescription("A demonstration of a preference page implementation");
		setDescription("Code generate config");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {
//		addField(new DirectoryFieldEditor(PreferenceConstants.P_PATH,
//				"&Directory preference:", getFieldEditorParent()));
//		addField(new BooleanFieldEditor(PreferenceConstants.P_BOOLEAN,
//				"&An example of a boolean preference", getFieldEditorParent()));
//
//		addField(new RadioGroupFieldEditor(PreferenceConstants.P_CHOICE,
//				"An example of a multiple-choice preference", 1,
//				new String[][] { { "&Choice 1", "choice1" },
//						{ "C&hoice 2", "choice2" } }, getFieldEditorParent()));
//		addField(new StringFieldEditor(PreferenceConstants.P_STRING,
//				"A &text preference:", getFieldEditorParent()));
		
//		FieldEditor
		
//		addField(new PathEditor("dao", "DAO", "SS", getFieldEditorParent()));
//		addField(new DirectoryFieldEditor("dir", "DIR", getFieldEditorParent()));
//		addField(new FileFieldEditor("file", "File", getFieldEditorParent()));
//		addField(new StringFieldEditor("str", "STR", getFieldEditorParent()));
//		
		
//		Composite parent = getFieldEditorParent();
//		Composite panel = new Composite(parent, SWT.NONE);
//		GridLayout layout = new GridLayout();
//		layout.marginHeight = 0;
//		layout.marginWidth = 0;
//		panel.setLayout(layout);
//		
//		Text text = new Text(panel, SWT.BORDER | SWT.MULTI | SWT.WRAP);
//		text.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		addField(new FileEditor("template", "Template", "template", getFieldEditorParent()));
		addField(new TextAreaEditor("textarea", "Preview", getFieldEditorParent()));
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}