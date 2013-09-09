package ${target};

import ${fullName};


import java.util.List;
import java.io.Serializable;

public interface ${class}Repository {
	
	void save(${class} ${simpleName});
	
	void delete(${class} ${simpleName});
	
	void delete(Serializable id);
	
	void update(${class} ${simpleName});
	
	${class} find(Serializable id);
	
	List<${class}> findAll();
	
	List<${class}> find${class}s(int firstResult, int maxResult);
	
	List<${class}> find${class}sForPage(int page, int size);
	
	List<${class}> find${class}sForPage(int page);

	long count();
		
}