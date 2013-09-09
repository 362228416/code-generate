package code_generate.preferences;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import code_generate.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
//		store.setDefault(PreferenceConstants.P_BOOLEAN, true);
//		store.setDefault(PreferenceConstants.P_CHOICE, "choice2");
//		store.setDefault(PreferenceConstants.P_STRING, "Default value");
		
		store.setDefault("controller", getContent("resources/controller"));
		store.setDefault("repository", getContent("resources/repository"));
		store.setDefault("repositoryImpl", getContent("resources/repositoryImpl"));
		store.setDefault("service", getContent("resources/service"));
		store.setDefault("serviceImpl", getContent("resources/serviceImpl"));
		store.setDefault("toJson", getContent("resources/methods/toJson"));
		store.setDefault("textArea", "");
		store.setDefault("template", "repository;repositoryImpl;service;serviceImpl;controller;toJson;");
		
	}
	
	String getContent(String template) {
//		InputStream in = this.getClass().getClassLoader().getResourceAsStream("resources/" + template);
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(template);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		String content = null;
		try {
			int b = 0;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			content = out.toString();
			if (in != null) {
				in.close(); 
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

}
