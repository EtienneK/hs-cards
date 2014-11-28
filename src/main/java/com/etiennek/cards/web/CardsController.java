package com.etiennek.cards.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class CardsController {
	
	@RequestMapping
	public ModelAndView index() {
		return new ModelAndView("cards/index");
	}
	
}
