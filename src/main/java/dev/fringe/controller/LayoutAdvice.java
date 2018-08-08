package dev.fringe.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Mustache.Compiler;
import com.samskivert.mustache.Template.Fragment;

import dev.fringe.controller.Application.Menu;
import dev.fringe.service.UserService;

@ControllerAdvice
public class LayoutAdvice implements InitializingBean{

	private final Mustache.Compiler compiler;

	private Application application;

	@Autowired
	public LayoutAdvice(Compiler compiler, Application application) {
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
		userService.save();
	}
}

@Component
@ConfigurationProperties("app")
class Application {

	private List<Menu> menus = new ArrayList<>();

	public List<Menu> getMenus() {
		return menus;
	}

	public static class Menu {
		private String name;
		private String path;
		private String title;
		private boolean active;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
		}
	}

	public Menu getMenu(String name) {
		for (Menu menu : menus) {
			if (menu.getName().equalsIgnoreCase(name)) {
				return menu;
			}
		}
		return menus.get(0);
	}
}

class Layout implements Mustache.Lambda {

	String title = "Demo Application";

	String body;

	private Compiler compiler;

	public Layout(Compiler compiler) {
		this.compiler = compiler;
	}

	public void execute(Fragment frag, Writer out) throws IOException {
		body = frag.execute();
		compiler.compile("{{>layout}}").execute(frag.context(), out);
	}

}
