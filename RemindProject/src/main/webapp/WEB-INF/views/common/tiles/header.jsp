<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         isELIgnored="false"
%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">

</script>
<body>
<ul>
    <li><a href="${contextPath}/dailyWord/dailyWordForm.do"/>오늘의 단어</li>
    <li><a href="${contextPath}/word/saveWordForm.do"/>단어저장</li>
    <li><a href="${contextPath}/word/wordBoardForm.do"/>단어수정</li>
    <li><a href="${contextPath}/word/StudyForm.do"/>학습하기</li>
    <li><a href="${contextPath}/inquiryBoard/boardForm"/>문의게시판</li>
    <li><a href="${contextPath}/statistic/statisticForm.do"/>통계</li>
</ul>

<a href="${contextPath}/member/logout">로그아웃</a>

<hr>
</body>