package ua.alex.source.webtester.components.impl;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class DefaultExceptionHandler {
    private static final Logger LOGGER = Logger.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, HttpServletRequest req, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("errors");
        LOGGER.error("Internal Server Error - ", exception);
        modelAndView.addObject("errors", "Opss! Some error occurs.");
        return modelAndView;
    }
}
