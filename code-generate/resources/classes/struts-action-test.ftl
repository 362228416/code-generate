package ${target};

import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.*;

public class ${class} {

	${domain} ${simpleName};
	
	@Before
	public void setUp() throws Exception {
		${simpleName} = new ${domain}();
	}

	<#list methods as method>
	@Test
	public void test${method?cap_first}() throws Exception {
		${simpleName}.${method}();
	}
	</#list>

}