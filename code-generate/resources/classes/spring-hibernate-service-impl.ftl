package ${target};

import ${domainPkg}.${domain};
import ${daoPkg}.${dao};

public class ${class} implements ${domain}Service{
	
	${dao} ${dao?uncap_first};
	
	public void save${domain}(${domain} ${domain?uncap_first}) {
		${dao?uncap_first}.save(${domain?uncap_first});
	}
	
}