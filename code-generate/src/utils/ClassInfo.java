package utils;

import org.eclipse.jdt.internal.core.SourceType;

@SuppressWarnings("restriction")
public class ClassInfo {
	
	private String packageName;
	private String targetPackage;
	private String className;
	private String suffix;
	private String path;
	private String simpleName;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ClassInfo(SourceType info, String suffix) {
		this.suffix = suffix == null ? "" : suffix;
		this.packageName = info.getParent().getParent().getElementName();
		this.className = info.getElementName().replace(".java", "");
	}
	
	public ClassInfo(SourceType info, String suffix, String path) {
		this.suffix = suffix == null ? "" : suffix;
		this.packageName = info.getParent().getParent().getElementName();
		this.className = info.getElementName().replace(".java", "");
		this.path = path;
		
		String first = String.valueOf(className.charAt(0));
		this.simpleName = className.replaceFirst(first, first.toLowerCase());
		
	}
	
	public ClassInfo() {}

	public String getPackageName() {
		return packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setPackageName(String packageName) {
//		String oldPath = this.packageName.replaceAll("[.]", "/");
//		String newPath = packageName.replaceAll("[.]", "/");
//		this.path = path.replace(oldPath, newPath);
		this.packageName = packageName;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getSimpleName() {
		return simpleName;
	}
	
	public String getTargetPackage() {
		return targetPackage;
	}
	
	public void setTargetPackage(String targetPackage) {
		String oldPath = this.packageName.replaceAll("[.]", "/");
		String newPath = targetPackage.replaceAll("[.]", "/");
		this.path = path.replace(oldPath, newPath);
		this.targetPackage = targetPackage;
	}
}
