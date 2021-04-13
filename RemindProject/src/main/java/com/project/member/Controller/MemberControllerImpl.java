package com.project.member.Controller;

import com.project.common.base.BaseController;
import com.project.common.mail.mailService;
import com.project.common.sessionListener.sessionConfig;
import com.project.member.service.MemberService;
import com.project.member.vo.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("MemberController")
@RequestMapping(value = "/member")
public class MemberControllerImpl extends BaseController implements MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);

    @Autowired
    MemberVO memberVO;

    @Autowired
    MemberService memberService;

    @Autowired
    mailService mailService;


    @RequestMapping(value = "/addMember", method = RequestMethod.POST)
    @Override
    public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response, @ModelAttribute MemberVO memberVO) throws Exception {
        ModelAndView modelAndView;
        try {
            String AuthKey = mailService.getKey(false, 20);
            memberVO.setAuthKey(AuthKey);
            memberService.addMember(memberVO);
            mailService.setMail(AuthKey, memberVO.getUserId(), memberVO.getEmail(), request.getContextPath());
            modelAndView = new ModelAndView("/main/main");
            modelAndView.addObject("message", "registerSuccess");
        } catch (Exception e) {
            logger.debug("DEBUG : " + e);
            modelAndView = new ModelAndView("/common/error");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/overlap")
    @Override
    public ResponseEntity searchOverlapId(HttpServletRequest request, HttpServletResponse response, @RequestParam("userId") String userId) {
        ResponseEntity responseEntity;
        try {
            int userIdCount = memberService.searchOverlapId(userId);
            if (userIdCount == 0) {
                responseEntity = new ResponseEntity<String>("notOverlapping", HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<String>("Overlapping", HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.debug("DEBUG : " + e);
            responseEntity = new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/authMember.do")
    @Override
    public ModelAndView authMember(HttpServletRequest request, HttpServletResponse response, @RequestParam("userId") String userId, @RequestParam("authKey") String authKey) {
        Map<String, Object> memberMap = new HashMap<>();
        ModelAndView modelAndView;
        try {
            memberMap.put("userId", userId);
            memberMap.put("authKey", authKey);
            String savedAuthKey = memberService.selectAuthKey(memberMap);
            if (authKey.equals(savedAuthKey)) {
                modelAndView = new ModelAndView("/member/authMember");
                memberService.updateAuthKey(memberMap);
            } else if (savedAuthKey.equals("Y")) {
                modelAndView = new ModelAndView("/main/main");
                modelAndView.addObject("message", "certified");
            } else {
                modelAndView = new ModelAndView("/main/main");
                modelAndView.addObject("message", "authFalse");
            }
        } catch (Exception e) {
            logger.debug("DEBUG : " + e);
            modelAndView = new ModelAndView("/common/error");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Override
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> memberMap) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        MemberVO membervo = memberService.loginMember(memberMap);
        if (membervo != null) {
            if (!membervo.getAuthKey().equals("Y")) {
                modelAndView = new ModelAndView("/main/main");
                modelAndView.addObject("message", "notAuthorization");
                return modelAndView;
            } else {
                String userId = membervo.getUserId();
                sessionConfig.SessionCheck(userId);
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("userId", userId);
                httpSession.setAttribute("isLogin", true);
                modelAndView.setViewName("/main/introductionPage");
            }
        } else {
            modelAndView.addObject("message", "loginFail");
            modelAndView.setViewName("/main/main");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/logout")
    @Override
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("/main/main");
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        if (userId != null) {
            session.removeAttribute("userId");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/findUserId", method = RequestMethod.GET)
    @Override
    public ModelAndView findUserId(HttpServletRequest request, HttpServletResponse response, @ModelAttribute MemberVO memberVO) {
        ModelAndView modelAndView;
        List<MemberVO> memberVOItems;
        try {
            memberVOItems = memberService.findUserId(memberVO);
            if (memberVOItems == null || memberVOItems.size() <= 0) {
                modelAndView = new ModelAndView("/member/foundUserIdForm");
                modelAndView.addObject("message", "empty");
            } else {
                modelAndView = new ModelAndView("/member/foundUserIdForm");
                modelAndView.addObject("memberVOItems", memberVOItems);
            }
        } catch (Exception e) {
            logger.debug("DEBUG : " + e);
            modelAndView = new ModelAndView("/common/error");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/findUserPwd", method = RequestMethod.GET)
    @Override
    public ResponseEntity findUserPwd(HttpServletRequest request, HttpServletResponse response, @ModelAttribute MemberVO memberVO) {
        ResponseEntity<String> responseEntity;
        String contextPath = request.getContextPath();
        try {
            MemberVO _memberVO = memberService.findUserPwd(memberVO);
            if (_memberVO == null) {
                responseEntity = new ResponseEntity<String>("NotFounded", HttpStatus.OK);
            } else {
                String newPwd = mailService.getKey(false, 14);
                mailService.findPwdMail(newPwd, _memberVO.getUserId(), _memberVO.getUserName(), _memberVO.getEmail(), contextPath);
                _memberVO.setUserPwd(newPwd);
                memberService.updateUserPwd(_memberVO);
                responseEntity = new ResponseEntity<String>("founded", HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.debug("DEBUG : " + e);
            responseEntity = new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/modifyMemberForm.do", method = RequestMethod.GET)
    public ModelAndView modifyMemberForm(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView;
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        Map<String, String> memberMap = new HashMap<>();
        memberMap.put("userId", userId);
        try {
            MemberVO memberVO = memberService.selectMemberInfo(memberMap);
            modelAndView = new ModelAndView("/member/modifyMemberForm");
            modelAndView.addObject("memberVO", memberVO);
        } catch (Exception e) {
            logger.debug("DEBUG : " + e);
            modelAndView = new ModelAndView("/common/error");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/modifyMember", method = RequestMethod.POST)
    public ModelAndView modifyMember(HttpServletRequest request, HttpServletResponse response, @ModelAttribute MemberVO memberVO) {
        ModelAndView modelAndView;
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        memberVO.setUserId(userId);
        try {
            memberService.modifyMember(memberVO);
            modelAndView = new ModelAndView("redirect:/member/logout");
        } catch (Exception e) {
            logger.debug("DEBUG : " + e);
            modelAndView = new ModelAndView("/common/error");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
    public ModelAndView deleteMember(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView;
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        Map<String, String> memberMap = new HashMap<>();
        memberMap.put("userId", userId);
        try {
            memberService.deleteMember(memberMap);
            session.removeAttribute("userId");
            modelAndView = new ModelAndView("/main/main");
        } catch (Exception e) {
            logger.debug("DEBUG : " + e);
            modelAndView = new ModelAndView("/common/error");
        }
        return modelAndView;
    }
}