<%@ page language="java" contentType="text/html; ISO-8859-1"
         pageEncoding="utf-8"
         isELIgnored="false"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Content Page</title>
</head>
<body>
    <ul>
        <li><a href="${contextPath}/word/StudySettingForm.do"/>오늘의 학습</li>
        <li><a href="${contextPath}/word/saveWordForm.do"/>단어저장</li>
        <li>단어학습</li>
        <li>통계</li>
    </ul>
</body>
</html>
