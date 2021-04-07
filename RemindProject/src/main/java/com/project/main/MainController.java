package com.project.main;

import com.project.common.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("mainController")
@RequestMapping(value="/main")
public class MainController extends BaseController {

    @RequestMapping(value="/main.do", method ={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView main(HttpServletResponse response, HttpServletRequest request) throws Exception{
        String viewName = (String) request.getAttribute("viewName");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @RequestMapping(value="/introduction.do")
    public ModelAndView mainContent(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/main/introductionPage");
        return modelAndView;
    }
}