package ${target};

import ${fullName};
import repository.${class}Repository;

import java.util.List;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ${class}${suffix} implements ${class}Service {
	
	@Autowired
	private ${class}Repository repository;
	
	public ${class}ServiceImpl(){}
	
	public ${class}ServiceImpl(${class}Repository repository) {
		this.repository = repository;
	}
	
	public void save${class}(${class} ${simpleName}) {
		repository.save(${simpleName});
	}
	
	public void delete${class}(${class} ${simpleName}) {
		repository.delete(${simpleName});
	}
	
	public void delete${class}(Serializable id) {
		repository.delete(id);
	}
	
	public void update${class}(${class} ${simpleName}) {
		repository.update(${simpleName});
	}
	
	public ${class} find${class}(Serializable id) {
		return repository.find(id);
	}
	
	public List<${class}> findAll${class}() {
		return repository.findAll();
	}
	
	public List<${class}> find${class}s(int firstResult, int maxResult) {
		return repository.find${class}s(firstResult, maxResult);
	}
	
	public List<${class}> find${class}sForPage(int page, int size) {
		return repository.find${class}sForPage(page, size);
	}
	
	public List<${class}> find${class}sForPage(int page) {
		return repository.find${class}sForPage(page);
	}
	
	public void set${class}Repository(${class}Repository repository) {
		this.repository = repository;
	}
	
	public ${class}Repository get${class}Repository(${class}Repository repository) {
		return repository;
	}
	
}