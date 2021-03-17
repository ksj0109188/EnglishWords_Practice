package com.project.common.base;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class BaseController {

    @RequestMapping(value = "/*Form.do", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView viewForm(HttpServletResponse response, HttpServletRequest request) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        String viewName= (String) request.getAttribute("viewName");
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
