<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         isELIgnored="false"
%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<nav class="navbar navbar-expand-md bg-dark navbar-dark justify-content-between">
    <a class="nav-link text-white" href="${contextPath}/main/main.do">REMIND</a>
    <ul class="navbar-nav">
        <li class="nav-item"><a class="nav-link" href="${contextPath}/dailyWord/dailyWordForm.do">오늘의 단어</a></li>
        <li class="nav-item"><a class="nav-link" href="${contextPath}/word/saveWordForm.do">단어저장</a></li>
        <li class="nav-item"><a class="nav-link" href="${contextPath}/word/StudyForm.do">학습하기</a></li>
        <li class="nav-item"><a class="nav-link" href="${contextPath}/inquiryBoard/boardForm">문의게시판</a></li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                MY INFO
            </a>
            <div class="dropdown-menu">
                <span class="dropdown-item">${userId}</span>
                <a class="dropdown-item" href="${contextPath}/member/modifyMemberForm.do">회원정보 수정</a>
                <hr>
                <a class="dropdown-item" href="${contextPath}/word/wordBoardForm.do">단어수정</a>
                <a class="dropdown-item" href="${contextPath}/statistic/statisticForm.do">통계</a>
                <hr>
                <a class="dropdown-item" href="${contextPath}/member/logout">로그아웃</a>
            </div>
        </li>
    </ul>
</nav>
