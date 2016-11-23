package com.mmashyr.staffmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by Mark
 */

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    public final static String DEFAULT_ERROR_VIEW = "error";

    private static final Logger logger = LoggerFactory
            .getLogger(GlobalControllerExceptionHandler.class);


    @ExceptionHandler(value = {RuntimeException.class})
    public ModelAndView errorHandler(HttpServletRequest req, Exception e) {

        logger.error(e.toString() + "at" + req.getRequestURI() + e.getMessage());

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}

