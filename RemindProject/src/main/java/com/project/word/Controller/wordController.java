package com.project.word.Controller;

import com.project.word.vo.wordVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface wordController {

    ResponseEntity addWord(HttpServletRequest request, HttpServletResponse response, @ModelAttribute wordVO wordvo);
    ResponseEntity<List<wordVO>> study(HttpServletRequest request, HttpServletResponse response, @RequestParam("info") Map<String,String> wordvo);

}
