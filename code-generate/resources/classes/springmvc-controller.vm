package ${target};

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/${simpleName}s")
public class ${class} {
	
	@RequestMapping
	public String list(Model model) {
		return "${simpleName}/list";
	}
	
	@RequestMapping("/{id}")
	public String show(@PathVariable long id, Model model) {
		return "${simpleName}/show";
	}
	
	@RequestMapping(params = "form")
	public String createForm(Model model) {
		return "${simpleName}/form";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(Model model) {
		return "${simpleName}/view";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(@PathVariable long id, Model model) {
		return "${simpleName}/view";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable long id) {
		return "redirect:${simpleName}s";
	}


}