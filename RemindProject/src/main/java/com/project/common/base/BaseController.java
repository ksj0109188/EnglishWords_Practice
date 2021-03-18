package com.project.common.base;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public abstract class BaseController {
    private static final String BOARD_IMAGE = "/Users/kim/IdeaProjects/EnglishWords_Practice/images";

    @RequestMapping(value = "/*Form.do", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView viewForm(HttpServletResponse response, HttpServletRequest request) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        String viewName= (String) request.getAttribute("viewName");
        modelAndView.setViewName(viewName);
        return modelAndView;
    }


    protected List<String> upload(MultipartHttpServletRequest multipartRequest) throws Exception{
        List<String> fileList= new ArrayList<String>();
        Iterator<String> fileNames = multipartRequest.getFileNames();
        while(fileNames.hasNext()){
            String fileName = fileNames.next();
            MultipartFile mFile = multipartRequest.getFile(fileName);
            String originalFileName=mFile.getOriginalFilename();
            fileList.add(originalFileName);
            File file = new File(BOARD_IMAGE +"/"+ fileName);
            if(mFile.getSize()!=0){
                if(! file.exists()){
                    if(file.getParentFile().mkdirs()){
                        file.createNewFile();
                    }
                }
                mFile.transferTo(new File(BOARD_IMAGE +"/"+"temp"+ "/"+originalFileName));
            }
        }
        return fileList;
    }
}
