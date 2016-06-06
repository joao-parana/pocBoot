package br.cepel.soma.poc1.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.cepel.soma.poc1.daos.CategoryDao;
import br.cepel.soma.poc1.models.Category;

@Controller
@RequestMapping("/category")
@Transactional
public class CategoryController {

	@Autowired
	private CategoryDao categoryDao;

	@RequestMapping("/form")
	public ModelAndView form(Category category) {
		ModelAndView modelAndView = new ModelAndView("category/form-add");
		return modelAndView; // loadFormDependencies(modelAndView);

	}

	// private ModelAndView loadFormDependencies(ModelAndView modelAndView) {
	// modelAndView.addObject("stringList", stringDao.all());
	// modelAndView.addObject("stringList", stringDao.all());
	// return modelAndView;
	// }

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Valid Category category, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return form(category);
		}
		categoryDao.save(category);
		return new ModelAndView("redirect:/category");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ModelAndView load(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("category/form-update");
		modelAndView.addObject("category", categoryDao.findById(id));
		// loadFormDependencies(modelAndView);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(defaultValue = "0", required = false) int page) {
		ModelAndView modelAndView = new ModelAndView("category/list");
		modelAndView.addObject("paginatedList", categoryDao.paginated(page, 10));
		return modelAndView;
	}

	// just because get is easier here. Be my guest if you want to change.
	@RequestMapping(method = RequestMethod.GET, value = "/remove/{id}")
	public String remove(@PathVariable("id") Integer id) {
		Category category = categoryDao.findById(id);
		categoryDao.remove(category);
		return "redirect:/category";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}")
	public ModelAndView update(@PathVariable("id") Integer id, @Valid Category category, BindingResult bindingResult) {
		category.setId(id);
		if (bindingResult.hasErrors()) {
			ModelAndView m = new ModelAndView("category/form-update");
			return m; // loadFormDependencies(m);
		}
		categoryDao.update(category);
		return new ModelAndView("redirect:/category");
	}
}
