package com.ydpp.eclipse.config;

import org.eclipse.jface.preference.IPreferenceStore;

import com.ydpp.eclipse.CodeGeneratePlugin;

/**
 * 全局配置
 * @author john
 */
public class CodeConfig {

	public static IPreferenceStore store = CodeGeneratePlugin.getDefault().getPreferenceStore();
	
	public static String getString(String key) {
		return store.getString(key);
	}
	
	public static String getDefaultString(String key) {
		return store.getDefaultString(key);
	}
	
}
