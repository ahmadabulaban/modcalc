package com.insanwalat.modcalc.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public void handleConflict(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().println(e.getMessage());
    }
}
