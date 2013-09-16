package ${target};

import static junit.framework.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ${domainPkg}.${domain};

@ContextConfiguration(locations = "META-INF/spring/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ${class} {

	@Autowired
	${dao} ${dao?uncap_first};
	
	@Before
	public void setUp() throws Exception {
		for (int i = 0; i < 20; i++) {
			${domain} ${domain?uncap_first} = getRandom${domain}();
			${dao?uncap_first}.save(${domain?uncap_first});
		}
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testSave() throws Exception {
		${domain} ${domain?uncap_first} = getRandom${domain}();
		${dao?uncap_first}.save(${domain?uncap_first});
		assertFalse(${domain?uncap_first}.getId() == 0);
	}
	
	@Test
	public void testDelete() throws Exception {
		${domain} ${domain?uncap_first} = getRandom${domain}();
		${dao?uncap_first}.save(${domain?uncap_first});
		assertFalse(${domain?uncap_first}.getId() == 0);
		${dao?uncap_first}.delete(${domain?uncap_first});
		${domain} ${domain?uncap_first}2 = ${dao?uncap_first}.find(${domain?uncap_first}.getId());
		assertNull(${domain?uncap_first}2);
	}
	
	@Test
	public void testUpdate() throws Exception {
		
	}
	
	@Test
	public void testFind() throws Exception {
		${domain} ${domain?uncap_first} = getRandom${domain}();
		${dao?uncap_first}.save(${domain?uncap_first});
		assertFalse(${domain?uncap_first}.getId() == 0);
		${domain} ${domain?uncap_first}2 = ${dao?uncap_first}.find(${domain?uncap_first}.getId());
		assertNotNull(${domain?uncap_first}2);
	}
	
	@Test
	public void testFindAll() throws Exception {
		long count = ${dao?uncap_first}.findAll().size();
		assertEquals(${dao?uncap_first}.count(), count);
	}
	
	@Test
	public void testFind(int firstResult, int maxResults) throws Exception {
		int count = ${dao?uncap_first}.find(0, 10).size();
		assertTrue(count == 10);
	}
	
	@Test
	public void testCount() throws Exception {
		long count = ${dao?uncap_first}.count();
		assertTrue(count != 0);
		assertEquals(${dao?uncap_first}.findAll().size(), count);
	}
	
	${domain} getRandom${domain}() {
		${domain} ${domain?uncap_first} = new ${domain}();
		return ${domain?uncap_first};
	}

}