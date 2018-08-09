package dev.fringe.controller;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Mustache.Compiler;

import dev.fringe.entity.support.ApplicationMenu;
import dev.fringe.entity.support.Layout;
import dev.fringe.entity.support.Menu;
import dev.fringe.service.UserService;

@ControllerAdvice
public class LayoutAdvice implements InitializingBean{

	private final Mustache.Compiler compiler;

	private ApplicationMenu application;

	@Autowired
	public LayoutAdvice(Compiler compiler, ApplicationMenu application) {
		this.compiler = compiler;
		this.application = application;
	}

	@ModelAttribute("menus")
	public Iterable<Menu> menus(@ModelAttribute Layout layout) {
		for (Menu menu : application.getMenus()) {
			menu.setActive(false);
		}
		return application.getMenus();
	}

	@ModelAttribute("menu")
	public Mustache.Lambda menu(@ModelAttribute Layout layout) {
		return (frag, out) -> {
			Menu menu = application.getMenu(frag.execute());
			menu.setActive(true);
			layout.title = menu.getTitle();
		};
	}

	@ModelAttribute("layout")
	public Mustache.Lambda layout(Map<String, Object> model) {
		return new Layout(compiler);
	}

	@Autowired
	private UserService userService;
	
	public void afterPropertiesSet() throws Exception {
//		userService.save();
	}
}




