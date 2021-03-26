<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Content Page</title>
        <c:if test="${not empty finish}">
            <script>
                alert("외울 단어가 없습니다. mainContent.jsp페이지입니다.");
            </script>
        </c:if>


</head>

<body>
        <a href="${contextPath}/member/logout">로그아웃</a>
    <ul>
        <li><a href="${contextPath}/dailyWord/dailyWordForm.do"/>오늘의 단어</li>
        <li><a href="${contextPath}/word/saveWordForm.do"/>단어저장</li>
        <li><a href="${contextPath}/word/wordBoardForm.do"/>단어수정</li>
        <li><a href="${contextPath}/word/StudyForm.do"/>학습하기</li>
        <li><a href="${contextPath}/inquiryBoard/boardForm"/>문의게시판</li>
        <li><a href="${contextPath}/statistic/statisticForm.do"/>통계</li>
    </ul>
</body>
</html>
