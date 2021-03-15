package com.project.inquiryBoard.dao;

import com.project.inquiryBoard.vo.AnswerVO;
import com.project.inquiryBoard.vo.inquiryBoardVO;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface inquiryBoardDAO {
    List<inquiryBoardVO> selectInquiryBoard(Map inquiryBoardMap) throws DataAccessException;

    inquiryBoardVO selectBoardDetail(Map boardMap) throws DataAccessException;

    List<AnswerVO> selectBoardAnswer(Map boardMap) throws DataAccessException;
}
