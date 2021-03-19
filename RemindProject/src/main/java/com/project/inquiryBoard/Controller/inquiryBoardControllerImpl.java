package com.project.inquiryBoard.Controller;

import com.project.common.base.BaseController;
import com.project.inquiryBoard.service.inquiryBoardService;
import com.project.inquiryBoard.vo.AnswerVO;
import com.project.inquiryBoard.vo.imageVO;
import com.project.inquiryBoard.vo.inquiryBoardVO;
import org.apache.commons.io.FileUtils;
import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@RequestMapping(value = "/inquiryBoard")
@Controller("inquiryBoardController")
public class inquiryBoardControllerImpl extends BaseController implements inquiryBoardController {
    @Autowired
    inquiryBoardService inquiryBoardService;

    @Autowired
    inquiryBoardVO inquiryBoardVO;

    @Autowired
    imageVO imageVO;

    @Autowired
    AnswerVO AnswerVO;

    @RequestMapping(value = "/boardForm.do")
    @Override
    public ModelAndView selectInquiryBoard(HttpServletRequest request, HttpServletResponse response) {
        Map boardMap = new HashMap();
        List<inquiryBoardVO> inquiryBoardVO;
        try {
            inquiryBoardVO = inquiryBoardService.selectInquiryBoard(boardMap);
            ModelAndView modelAndView = new ModelAndView("inquiryBoard/inquiryBoardForm");
            modelAndView.addObject("inquiryBoardVO", inquiryBoardVO);
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("common/error");
        }

    }

    @RequestMapping(value = "/board/{boardId}", method = RequestMethod.GET)
    public ModelAndView selectBoardDetail(HttpServletRequest request, HttpServletResponse response, @PathVariable int boardId) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        Map boardMap = new HashMap();
        boardMap.put("boardId", boardId);
        boardMap.put("userId", userId);
        inquiryBoardVO inquiryBoardVO;
        List<AnswerVO> AnswerVO;
        List<imageVO> imageVO;
        try {
            inquiryBoardVO = inquiryBoardService.selectBoardDetail(boardMap);
            AnswerVO = inquiryBoardService.selectBoardAnswer(boardMap);
            imageVO=inquiryBoardService.selectBoardImage(boardMap);
            ModelAndView modelAndView = new ModelAndView("inquiryBoard/detailBoardForm");
            modelAndView.addObject("inquiryBoardVO", inquiryBoardVO);
            modelAndView.addObject("AnswerVO", AnswerVO);
            modelAndView.addObject("imageVO", imageVO);
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("common/error");
        }
    }

    @RequestMapping(value = "/writeBoard", method = RequestMethod.POST)
    @Override
    public ResponseEntity writeBoard(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
        ResponseEntity responseEntity = null;
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("content-type", "text/html; charset=utf-8");
        String message;
        HttpSession session = multipartRequest.getSession();
        String userId = (String) session.getAttribute("userId");
        Map boardMap = new HashMap();
        boardMap.put("userId", userId);

        List<inquiryBoardVO> inquiryBoardVO = new ArrayList<inquiryBoardVO>();
        Enumeration enu = multipartRequest.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            String value = multipartRequest.getParameter(name);
            boardMap.put(name, value);
        }

        String imageFileName;
        List<String> fileList = upload(multipartRequest);
        List<imageVO> imageFileList = new ArrayList<imageVO>();

        if (fileList != null && fileList.size() != 0) {
            for (String fileName : fileList) {
                imageVO ImageVO = new imageVO();
                ImageVO.setImageFileName(fileName);
                imageFileList.add(ImageVO);
            }
            boardMap.put("imageFileList", imageFileList);
        }
        try {
            int boardId = inquiryBoardService.writeBoard(boardMap);
            inquiryBoardService.writeImageBoard(boardMap);
            if (imageFileList.size() != 0) {
                for (imageVO imageVO : imageFileList) {
                    imageFileName = imageVO.getImageFileName();
                    File srcFile = new File(BOARD_IMAGE + "/" + "temp" + "/" + imageFileName);
                    File destDir = new File(BOARD_IMAGE + "/" + boardId);
                    FileUtils.moveFileToDirectory(srcFile, destDir, true);
                }
            }
            message = "<script>";
            message += " alert('글이 작성되었습니다.');";
            message += " location.href='" + multipartRequest.getContextPath() + "/inquiryBoard/boardForm.do'; ";
            message += " </script>";
            responseEntity = new ResponseEntity(message, responseHeader, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            if (imageFileList != null && imageFileList.size() != 0) {
                for (imageVO imageVO : imageFileList) {
                    imageFileName = imageVO.getImageFileName();
                    File srcFile = new File(BOARD_IMAGE + "/" + "temp" + "/" + imageFileName);
                    srcFile.delete();
                }
                message = "<script>";
                message += " alert('잠시후 다시 시도해주세요.');";
                message += " location.href='" + multipartRequest.getContextPath() + "/inquiryBoard/error'; ";
                message += " </script>";
                responseEntity = new ResponseEntity(message, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return responseEntity;
    }
}
