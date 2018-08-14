package dev.fringe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dev.fringe.entity.Item;
import dev.fringe.service.ItemService;

/**
 * @author dev-frine
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService item;

	@RequestMapping(value = "/item", method = {RequestMethod.GET , RequestMethod.POST})
	public String get(Model m, Item i) {
		System.out.println(i);
		m.addAllAttributes(item.get(i));
		return "item";
	}
}
