package com.ydpp.util;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 代码生成历史管理
 * @author john
 */
public class CodeHistoryManager {

	static Queue<History> histories = new LinkedList<History>();

	public static void addHistory(String project, String domain, String packageName, String className, CodeType type) {
		if (histories.size() == 1000) {
			histories.remove();
		}
		History history = new History();
		history.project = project;
		history.packageName = packageName;
		history.domain = domain;
		history.className = className;
		history.type = type;
		histories.add(history);
	}

}

class History {
	String project, domain, packageName, className;
	CodeType type;
}

enum CodeType {
	DAO, SERVICE, CONTROLLER
}