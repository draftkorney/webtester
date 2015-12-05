package ua.alex.source.webtester.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/advanced_tutor")
public class AdvancedTutorController extends AbstractTutorController {

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(){
		return "advanced_tutor/home";
	}
}
