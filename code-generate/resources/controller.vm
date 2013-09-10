package ${target};

import ${fullName};
import repository.${class}Repository;

import java.util.Collection;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/${simpleName}")
public class ${class}${suffix} {
	
	@Autowired
	${class}Repository repository;
	
	@RequestMapping()
	public void list(
			@RequestParam(required = false, defaultValue = "10") int size,
			@RequestParam(required = false, defaultValue = "0") int page, Model model) {
        int first = page * size;
        Collection<${class}> ${simpleName}s = repository.find${class}s(first, size);
        model.addAttribute("${simpleName}s", ${simpleName}s);
	}
	
	@RequestMapping("/{id}")
	public void view(@PathVariable long id, Model model) {
		${class} ${simpleName} = repository.find(id);
		model.addAttribute("${simpleName}", ${simpleName});
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(${class} ${simpleName}) {
		repository.save(${simpleName});
		return "redirect:/${simpleName}/" + ${simpleName}.getId();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable long id) {
		repository.delete(id);
		return "redirect:/${simpleName}";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
    public String update(${class} ${simpleName}) {
        repository.update(${simpleName});
		return "redirect:/${simpleName}/" + ${simpleName}.getId();
    }
	
	// json
	
	@RequestMapping(headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<String> listJson(
			@RequestParam(required = false, defaultValue = "10") int size,
			@RequestParam(required = false, defaultValue = "0") int page) {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        int first = page * size;
        Collection<${class}> ${simpleName}s = repository.find${class}s(first, size);
        return new ResponseEntity<String>(${class}.toJsonArray(${simpleName}s), headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<String> viewJson(@PathVariable long id) {
		${class} ${simpleName} = repository.find(id);
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(${simpleName}.toJson(), headers, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> createFromJson(@RequestBody String json) {
		${class} ${simpleName} = ${class}.fromJsonTo${class}(json);
		repository.save(${simpleName});
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<String> deleteFromJson(@PathVariable long id) {
		repository.delete(id);
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        ${class} ${simpleName} = ${class}.fromJsonTo${class}(json);
        repository.update(${simpleName});
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
	
}
