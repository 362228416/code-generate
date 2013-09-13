package ${target};

import static junit.framework.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(locations = "META-INF/spring/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ${class} {

	@Autowired
	${domain} ${simpleName};
	
	@Before
	public void setUp() throws Exception {

	}
	
	@After
	public void tearDown() throws Exception {
	}

	<#list methods as method>
	@Test
	public void test${method?cap_first}() throws Exception {
		${domain} ${simpleName} = getRandom${domain}();
		${simpleName}.${method}(${simpleName});
	}
	</#list>

	${domain} getRandom${domain}() {
		${domain} ${simpleName} = new ${domain}();
		return ${simpleName};
	}

}