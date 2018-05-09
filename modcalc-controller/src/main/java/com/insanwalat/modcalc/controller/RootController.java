package com.insanwalat.modcalc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/")
public class RootController {

    @RequestMapping(method=RequestMethod.GET, path = {"/modcalc-controller", "/modcalc-controller/", "/"})
    public void goToRoot(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.sendRedirect("./page/index.html");
    }

    @RequestMapping(method=RequestMethod.GET, path = {"/page/"})
    public void goToRootPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.sendRedirect("./index.html");
    }

    @RequestMapping(method=RequestMethod.GET, path = {"/page"})
    public void goToRootPage2(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.sendRedirect("./page/index.html");
    }


}
