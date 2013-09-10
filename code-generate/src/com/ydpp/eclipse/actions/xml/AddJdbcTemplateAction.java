package com.ydpp.eclipse.actions.xml;

import org.eclipse.jface.action.IAction;

import com.ydpp.eclipse.actions.IObjectActionDelegateAdapter;
import com.ydpp.util.SpringXmlUtils;

/**
 * 
 * @author john
 *
 */
public class AddJdbcTemplateAction extends IObjectActionDelegateAdapter {

	@Override
	public void run(IAction action) {
		SpringXmlUtils.addBean(action, "jdbcTemplate");

		// new CodeFormatterApplication()
		;
//		Map options = DefaultCodeFormatterConstants.getEclipseDefaultSettings();
//		options.put(
//				DefaultCodeFormatterConstants.FORMATTER_ALIGNMENT_FOR_ENUM_CONSTANTS,
//				DefaultCodeFormatterConstants.createAlignmentValue(
//				true,
//				DefaultCodeFormatterConstants.WRAP_ONE_PER_LINE,
//				DefaultCodeFormatterConstants.INDENT_ON_COLUMN));
		
//		String source = "<b><a></a></b>";
//		final CodeFormatter codeFormatter = ToolFactory
//				.createCodeFormatter(options);
//		final TextEdit edit = codeFormatter.format(
//				CodeFormatter.K_COMPILATION_UNIT, // format a compilation unit
//				source, // source to format
//				0, // starting position
//				source.length(), // length
//				0, // initial indentation
//				System.getProperty("line.separator") // line separator
//				);
//
//		IDocument document = new Document(source);
//		try {
//			edit.apply(document);
//		} catch (MalformedTreeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (BadLocationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// DocumentFactory f = DocumentFactory.getInstance();
		// Element bean = f.createElement("bean");
		// bean.addAttribute("id", "jdbcTemplate");
		// bean.addAttribute("class",
		// "org.springframework.jdbc.core.JdbcTemplate");
		// Element prop = bean.addElement("property");
		// prop.addAttribute("name", "dataSource");
		// prop.addAttribute("ref", "dataSource");
		// SpringXmlUtils.addBean(action, bean);

	}

}
