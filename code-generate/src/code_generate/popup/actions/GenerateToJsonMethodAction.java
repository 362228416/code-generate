package code_generate.popup.actions;

import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.internal.core.CompilationUnit;
import org.eclipse.jdt.internal.core.SourceType;
import org.eclipse.jface.action.IAction;

import sun.misc.IOUtils;
import utils.ClassInfo;
import utils.Generator;
import utils.GeneratorUtils;
import utils.PluginUtils;

@SuppressWarnings({ "restriction", "unused" })
public class GenerateToJsonMethodAction extends IObjectActionDelegateAdapter {

	@Override
	public void execute(IAction action) throws Exception {
//		SourceType type = PluginUtils.getSourceType(action);
		List<SourceType> types = PluginUtils.getSourceTypes(action);
		if (!types.isEmpty()) {
			for (SourceType type : types) {
				String methods = Generator.getContent("resources/methods/toJson", Collections.singletonMap("class", (Object)type.getElementName()));
				type.createMethod(methods, null, true, null);
				
			}
		}
		
		
		List<CompilationUnit> files = PluginUtils.getCompilationUnits(action);
		for (CompilationUnit file : files) {
			file.createImport("flexjson.JSONDeserializer", null, null);
			file.createImport("flexjson.JSONSerializer", null, null);
			file.createImport("java.util.ArrayList", null, null);
			file.createImport("java.util.Collection", null, null);
			file.createImport("java.util.List", null, null);
			file.save(null, true);
		}
		
	}
	
}
