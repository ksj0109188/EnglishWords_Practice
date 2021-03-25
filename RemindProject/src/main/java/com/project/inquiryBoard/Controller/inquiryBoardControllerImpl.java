package com.project.inquiryBoard.Controller;

import com.project.common.base.BaseController;
import com.project.inquiryBoard.service.inquiryBoardService;
import com.project.inquiryBoard.vo.AnswerVO;
import com.project.inquiryBoard.vo.imageVO;
import com.project.inquiryBoard.vo.inquiryBoardVO;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@RequestMapping(value = "/inquiryBoard")
@RestController("inquiryBoardController")
public class inquiryBoardControllerImpl extends BaseController implements inquiryBoardController {
    @Autowired
    inquiryBoardService inquiryBoardService;

    @Autowired
    inquiryBoardVO inquiryBoardVO;

    @Autowired
    imageVO imageVO;

    @Autowired
    AnswerVO AnswerVO;

    @RequestMapping(value = "/boardForm", method = RequestMethod.GET)
    @Override
    public ModelAndView selectInquiryBoard(HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam(value = "section", defaultValue = "1") int section,
                                           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
        Map boardMap = new HashMap();
        int startPage = ((section - 1) * 100) + ((pageNum - 1) * 10 + 1);
        boardMap.put("pageNum", pageNum);
        boardMap.put("section", section);
        boardMap.put("startPage", startPage);
        List<inquiryBoardVO> inquiryBoardVO;
        try {
            inquiryBoardVO = inquiryBoardService.selectInquiryBoard(boardMap);
            int totalCount = inquiryBoardService.selectTotalCountBoard(boardMap);

            boardMap.put("inquiryBoardVO", inquiryBoardVO);
            boardMap.put("totalCount", totalCount);
            ModelAndView modelAndView = new ModelAndView("inquiryBoard/inquiryBoardForm");
            modelAndView.addObject("boardMap", boardMap);

            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("common/error");
        }
    }

    @RequestMapping(value = "/title", method = RequestMethod.GET)
    public ModelAndView selectTitle(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "title") String title,
                                    @RequestParam(value = "section", defaultValue = "1") int section,
                                    @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {

        Map boardMap = new HashMap();
        int startPage = ((section - 1) * 100) + ((pageNum - 1) * 10 + 1);

        boardMap.put("title",title);
        boardMap.put("pageNum", pageNum);
        boardMap.put("section", section);
        boardMap.put("startPage", startPage);
        boardMap.put("selectMode", "like");
        List<inquiryBoardVO> inquiryBoardVO;
        try {
            inquiryBoardVO = inquiryBoardService.selectInquiryBoard(boardMap);
            int totalCount = inquiryBoardService.selectTotalCountBoard(boardMap);

            boardMap.put("inquiryBoardVO", inquiryBoardVO);
            boardMap.put("totalCount", totalCount);
            ModelAndView modelAndView = new ModelAndView("inquiryBoard/inquiryBoardForm");
            modelAndView.addObject("boardMap", boardMap);
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
            imageVO = inquiryBoardService.selectBoardImage(boardMap);
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
        try {
            int boardId = inquiryBoardService.writeBoard(boardMap);
            inquiryBoardService.updateBoard(boardMap);
            if (fileList != null && fileList.size() != 0) {
                for (String fileName : fileList) {
                    imageVO ImageVO = new imageVO();
                    ImageVO.setImageFileName(fileName);
                    imageFileList.add(ImageVO);
                }
                boardMap.put("imageFileList", imageFileList);
                inquiryBoardService.writeImageBoard(boardMap);
            }
            if (imageFileList != null && imageFileList.size() != 0) {
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

    @RequestMapping(value = "/writeAnswer", method = RequestMethod.POST)
    public ResponseEntity writeAnswer(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, String> boardMap) {
        ResponseEntity responseEntity = null;
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("content-type", "text/html; charset=utf-8");
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        boardMap.put("userId", userId);
        try {
            inquiryBoardService.writeAnswer(boardMap);
            responseEntity = new ResponseEntity<String>("댓글이 작성되었습니다.", responseHeader, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            String message = "<script>";
            message += " alert('잠시후 다시 시도해주세요.');";
            message += " location.href='" + request.getContextPath() + "/inquiryBoard/error'; ";
            message += " </script>";
            responseEntity = new ResponseEntity(message, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/modifyBoardForm.do", method = RequestMethod.GET)
    public ModelAndView modifyForm(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "boardId", required = true) int boardId) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        Map boardMap = new HashMap();
        boardMap.put("boardId", boardId);
        boardMap.put("userId", userId);
        List<imageVO> imageVO;
        try {
            inquiryBoardVO inquiryBoardVO = inquiryBoardService.selectBoardDetail(boardMap);
            imageVO = inquiryBoardService.selectBoardImage(boardMap);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("inquiryBoardVO", inquiryBoardVO);
            modelAndView.addObject("imageVO", imageVO);
            modelAndView.setViewName("/inquiryBoard/modifyBoardForm");
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("inquiryBoardVO", inquiryBoardVO);
            modelAndView.setViewName("/common/error");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/modifyBoard", method = RequestMethod.POST)
    public ModelAndView modifyBoard(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
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

        try {
            inquiryBoardService.updateBoard(boardMap);
            int boardId = Integer.parseInt((String) boardMap.get("boardId"));
            if (fileList != null && fileList.size() != 0) {
                for (String fileName : fileList) {
                    imageVO ImageVO = new imageVO();
                    ImageVO.setBoardId(boardId);
                    ImageVO.setImageFileName(fileName);
                    imageFileList.add(ImageVO);
                }
                boardMap.put("imageFileList", imageFileList);
                inquiryBoardService.updateImageBoard(boardMap);
            }
            if (imageFileList != null && imageFileList.size() != 0) {
                for (imageVO imageVO : imageFileList) {
                    imageFileName = imageVO.getImageFileName();
                    File srcFile = new File(BOARD_IMAGE + "/" + "temp" + "/" + imageFileName);
                    File destDir = new File(BOARD_IMAGE + "/" + boardId);
                    FileUtils.moveFileToDirectory(srcFile, destDir, true);

                    String originalFileName = (String) boardMap.get("originalFileName");
                    File oldFile = new File(BOARD_IMAGE + "/" + boardId + "/" + originalFileName);
                    oldFile.delete();
                }
            }
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:/inquiryBoard/board/" + boardId);
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            if (imageFileList != null && imageFileList.size() != 0) {
                for (imageVO imageVO : imageFileList) {
                    imageFileName = imageVO.getImageFileName();
                    File srcFile = new File(BOARD_IMAGE + "/" + "temp" + "/" + imageFileName);
                    srcFile.delete();
                }
            }
            return new ModelAndView("/common/error");
        }
    }

    @RequestMapping(value = "/deleteBoard", method = RequestMethod.POST)
    public ModelAndView deleteBoard(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "boardId") int boardId,
                                    @RequestParam(value = "imageFileName") String imageFileName) {
        Map boardMap = new HashMap();
        boardMap.put("boardId", boardId);
        boardMap.put("imageFileName", imageFileName);
        try {
            if (!imageFileName.equals("empty")) {
                File directory = new File(BOARD_IMAGE + "/" + boardId);
                File srcFile = new File(BOARD_IMAGE + "/" + boardId + "/" + imageFileName);
                srcFile.delete();
                directory.delete();
            }
            inquiryBoardService.deleteBoard(boardMap);
            return new ModelAndView("redirect:/inquiryBoard/boardForm.do");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("common/error");
        }
    }

    @RequestMapping(value = "/modifyAnswer", method = RequestMethod.PUT)
    public ResponseEntity modifyAnswer(HttpServletRequest request, HttpServletResponse response, @RequestBody Map AnswerMap) {
        ResponseEntity responseEntity = null;
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("content-type", "text/html; charset=utf-8");
        try {
            inquiryBoardService.modifyAnswer(AnswerMap);
            responseEntity = new ResponseEntity<String>("댓글이 수정되었습니다.", responseHeader, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>("에러발생", responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/deleteAnswer/{AnswerId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAnswer(HttpServletRequest request, HttpServletResponse response, @PathVariable int AnswerId) {
        ResponseEntity responseEntity = null;
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("content-type", "text/html; charset=utf-8");
        Map AnswerMap = new HashMap();
        AnswerMap.put("AnswerId", AnswerId);
        try {
            inquiryBoardService.deleteAnswer(AnswerMap);
            responseEntity = new ResponseEntity<String>("댓글이 삭제되었습니다..", responseHeader, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>("에러발생", responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
