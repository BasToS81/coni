package com.gor.sellphotos.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

public abstract class AbstractRestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRestHandler.class);
	
    @ExceptionHandler(Exception.class)
    public @ResponseBody String handleUncaughtException(Exception ex, WebRequest request, HttpServletResponse response) {
        LOGGER.error("Uncaught exception", ex);
        response.setHeader("Content-Type", "application/json");
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return "Error occurred" + ex.toString();
    }
	
    @ExceptionHandler(IllegalArgumentException.class)
    public @ResponseBody String handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request, HttpServletResponse response) {
        LOGGER.warn("Illegal argument", ex);
        response.setHeader("Content-Type", "application/json");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return "Error occurred"+ ex.toString();
    }
	
}
	