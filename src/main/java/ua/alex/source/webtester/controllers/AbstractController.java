package ua.alex.source.webtester.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ua.alex.source.webtester.service.CommonService;


public abstract class AbstractController {
	protected final Logger LOGGER = Logger.getLogger(getClass());
	
	@Autowired
	protected CommonService commonService;
}
