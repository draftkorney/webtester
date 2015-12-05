package ua.alex.source.webtester.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import ua.alex.source.webtester.service.AdvancedTutorService;
import ua.alex.source.webtester.service.TutorService;

public abstract class AbstractTutorController extends AbstractController {

	@Autowired
	protected TutorService tutorService;
	
	@Autowired
	protected AdvancedTutorService advancedTutorService;
}
