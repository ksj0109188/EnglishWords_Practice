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
            이 페이지에 수정할 거
<%--        word 컨트롤러로 요청해서 남은 단어수 출력.--%>
        <li><a href="${contextPath}/word/StudySettingForm.do"/>오늘의 학습</li>
        <li><a href="${contextPath}/word/saveWordForm.do"/>단어저장</li>
        <li>단어학습</li>
        <li>통계</li>
    </ul>
</body>
</html>
