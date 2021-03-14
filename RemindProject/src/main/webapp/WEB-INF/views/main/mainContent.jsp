<%@ page language="java" contentType="text/html; ISO-8859-1"
         pageEncoding="utf-8"
         isELIgnored="false"
%>

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
    <ul>
        <li><a href="${contextPath}/dailyWord/dailyWordForm.do"/>오늘의 단어</li>
        <li><a href="${contextPath}/word/saveWordForm.do"/>단어저장</li>
        <li><a href="${contextPath}/word/StudyForm.do"/>학습하기</li>
        <li>통계</li>
    </ul>
</body>
</html>
