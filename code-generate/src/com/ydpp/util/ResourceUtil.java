package com.ydpp.util;

import java.io.InputStream;

/**
 *
 * @author john
 */
public class ResourceUtil {
	
	public static InputStream getResourceAsStream(String resource) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
	}
	
}
