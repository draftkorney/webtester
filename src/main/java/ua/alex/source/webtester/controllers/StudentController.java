package ua.alex.source.webtester.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.alex.source.webtester.service.StudentService;


@Controller
public class StudentController extends AbstractController {

	@Autowired
	protected StudentService studentService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(){
		return "student/home";
	}
}
