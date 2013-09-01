package utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.CompilationUnit;
import org.eclipse.jdt.internal.core.CompilationUnitElementInfo;
import org.eclipse.jdt.internal.core.Openable;
import org.eclipse.jdt.internal.core.PackageDeclaration;
import org.eclipse.jdt.internal.core.SourceType;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.internal.ObjectPluginAction;

@SuppressWarnings("restriction")
public class PluginUtils {

	public static SourceType getSourceType(Object obj) {
//		SourceType type = null;
//		try {
//			if (obj instanceof ObjectPluginAction) {
//				ObjectPluginAction opa = (ObjectPluginAction) obj;
//				ISelection select = opa.getSelection();
//				if (select instanceof StructuredSelection) {
//					StructuredSelection sselect = (StructuredSelection) select;
//					for (Iterator iterator = sselect.iterator(); iterator
//							.hasNext();) {
//						CompilationUnit file = (CompilationUnit) iterator.next();
//						CompilationUnitElementInfo info = (CompilationUnitElementInfo) file.getElementInfo();
//						IJavaElement[] es = info.getChildren();
//						for (int i = 0; i < es.length;i++) {
//							if (es[i] instanceof SourceType) {
//								type = (SourceType) es[i];
//								break;
//							}
//						}
//					}
//				}
//			}
//			if (obj instanceof CompilationUnit) {
//				CompilationUnit file = (CompilationUnit) obj;
//				CompilationUnitElementInfo info = (CompilationUnitElementInfo) file.getElementInfo();
//				IJavaElement[] es = info.getChildren();
//				for (int i = 0; i < es.length;i++) {
//					if (es[i] instanceof SourceType) {
//						type = (SourceType) es[i];
//						break;
//					}
//				}
//				
//			}
////			type = (SourceType) es[1].getPrimaryElement();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return type;
		List<SourceType> types = getSourceTypes(obj);
		if (types.isEmpty()) {
			return null;
		}
		return types.get(0);
	}
	
	public static List<SourceType> getSourceTypes(Object obj) {
		List<CompilationUnit> files = getCompilationUnits(obj);
		List<SourceType> types = new ArrayList<SourceType>();
		for (CompilationUnit file : files) {
			try {
				CompilationUnitElementInfo info = (CompilationUnitElementInfo) file.getElementInfo();
				IJavaElement[] es = info.getChildren();
				for (int i = 0; i < es.length;i++) {
					if (es[i] instanceof SourceType) {
						types.add((SourceType) es[i]);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return types;
	}
	
	public static CompilationUnit getCompilationUnit(Object obj) {
		List<CompilationUnit> list = getCompilationUnits(obj);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public static List<CompilationUnit> getCompilationUnits(Object obj) {
		List<CompilationUnit> files = new ArrayList<CompilationUnit>();
		if (obj instanceof CompilationUnit) {
			files.add((CompilationUnit) obj);
		} else if (obj instanceof ObjectPluginAction) {
			ObjectPluginAction opa = (ObjectPluginAction) obj;
			ISelection select = opa.getSelection();
			if (select instanceof StructuredSelection) {
				StructuredSelection sselect = (StructuredSelection) select;
				for (Iterator iterator = sselect.iterator(); iterator
						.hasNext();) {
					files.add((CompilationUnit) iterator.next());
				}
			}
		}
		
		return files;
	}
	
	public static ClassInfo getClassInfo(Object obj) {
		ClassInfo classInfo = null;
		try {
//			CompilationUnit file = (CompilationUnit) obj;
			CompilationUnit file = getCompilationUnit(obj);
			
			CompilationUnitElementInfo info = (CompilationUnitElementInfo) file.getElementInfo();
			IJavaElement[] es = info.getChildren();
			SourceType type = null;
			PackageDeclaration pk = null;
			String path = null;
			for (int i = 0; i < es.length;i++) {
				Object o = es[i];
				if (o instanceof PackageDeclaration) {
					pk = (PackageDeclaration) o;
				}
				if (es[i] instanceof SourceType) {
					type = (SourceType) es[i];
				}
			}
//			System.out.println(pk.getParent().getParent().getPath().toString());
			IProject project = getProject(obj);
			// ant 跟 maven 目录
			path = pk == null ? "src/" : pk.getParent().getParent().getPath().toString().replace("/"+ project.getName() + "/", "");
//			IFolder folder = project.getFolder(path.replace("/"+ project.getName(), ""));
//			IFolder folder = project.getFolder(pk.getParent().getParent().getPath().toString().replace("/"+ project.getName() + "/", ""));
//			System.out.println(project.getName() + "--" + folder.exists());
			classInfo = new ClassInfo(type, "", path);
//			System.out.println(type.getPackageFragment());
//			IPackageFragment pp = type.getPackageFragment();
//			System.out.println(pp.getParent());
//			System.out.println(type.getPackageFragment().getPath());
//			System.out.println(pk.getPath());
//			type = (SourceType) es[1].getPrimaryElement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classInfo;
	}
	
	public static ClassInfo getClassInfo(Object obj, String packageName) {
		ClassInfo classInfo = null;
		try {
			CompilationUnit file = getCompilationUnit(obj);
			CompilationUnitElementInfo info = (CompilationUnitElementInfo) file.getElementInfo();
			IJavaElement[] es = info.getChildren();
			SourceType type = null;
			PackageDeclaration pk = null;
			String path = null;
			for (int i = 0; i < es.length;i++) {
				Object o = es[i];
				if (o instanceof PackageDeclaration) {
					pk = (PackageDeclaration) o;
				}
				if (es[i] instanceof SourceType) {
					type = (SourceType) es[i];
				}
			}
//			System.out.println(pk.getParent().getParent().getPath().toString());
			IProject project = getProject(obj);
			// ant 跟 maven 目录
			path = pk == null ? "src/" : pk.getParent().getParent().getPath().toString().replace("/"+ project.getName() + "/", "");
//			IFolder folder = project.getFolder(path.replace("/"+ project.getName(), ""));
//			IFolder folder = project.getFolder(pk.getParent().getParent().getPath().toString().replace("/"+ project.getName() + "/", ""));
//			System.out.println(project.getName() + "--" + folder.exists());
			classInfo = new ClassInfo(type, "", path);
//			type = (SourceType) es[1].getPrimaryElement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classInfo;
	}

	public static String getFullPath(Object obj) {
		String path = null;
		try {
			if (obj instanceof Project) {
				Project file = (Project) obj;
				path = file.getLocation().toOSString();
			} else if (obj instanceof IResource) {
				IResource file = (IResource) obj;
				path = file.getLocation().toOSString();
			} else if (obj instanceof CompilationUnit) {
				CompilationUnit file = (CompilationUnit) obj;
				path = file.getUnderlyingResource().getLocation().toOSString();
			} else if (obj instanceof Openable) {
				Openable dir = (Openable) obj;
				path = dir.getUnderlyingResource().getLocation().toOSString();
			}
		} catch (Exception e) {
		}
		return path;
	}

	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}
	
	public static IWorkspaceRoot getWorkspaceRoot() {
		return getWorkspace().getRoot();
	}
	
	public static IProject getProject(Object obj) {
		IProject project = null;
		if (obj instanceof Project) {
			 project = (Project) obj;
		} else if (obj instanceof IResource) {
			IResource file = (IResource) obj;
			file.getProject();
		} else if (obj instanceof CompilationUnit) {
			CompilationUnit file = (CompilationUnit) obj;
			project = file.getJavaProject().getProject();
		} else if (obj instanceof Openable) {
			Openable dir = (Openable) obj;
			project = dir.getJavaProject().getProject();
		}
		return project;
	}
	
	public static IPath getPath(Object obj) {
		IPath path = null;
		try {
			if (obj instanceof Project) {
				Project file = (Project) obj;
				path = file.getLocation();
			} else if (obj instanceof IResource) {
				IResource file = (IResource) obj;
				path = file.getLocation();
			} else if (obj instanceof CompilationUnit) {
				CompilationUnit file = (CompilationUnit) obj;
				path = file.getUnderlyingResource().getLocation();
			} else if (obj instanceof Openable) {
				Openable dir = (Openable) obj;
				path = dir.getUnderlyingResource().getLocation();
			}
		} catch (Exception e) {
		}
		return path;
		
	}
	
	public static IFile getFile(Object obj) {
		if (obj instanceof IFile) {
			return (IFile) obj;
		}
		return null;
	}

	public static IFile createFile(IProject project, String filename) throws Exception {
		IFile file = project.getFile(filename);
		file.create(null, true, null);
		return	file;
	}
	
	public static IFile createJavaFile(IProject project, String filename) throws Exception {
		filename = filename.replaceAll("[.]", "/") + ".java";
		IFile file = project.getFile(filename);
//		file.create(null, true, null);
		return	file;
	}
	
	public static IFolder createFolder(IProject project, String foldername) throws Exception {
		IFolder folder = project.getFolder(foldername);
		return folder;
	}
	
}
