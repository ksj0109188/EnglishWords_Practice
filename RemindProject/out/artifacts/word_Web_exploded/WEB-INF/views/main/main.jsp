<%@ page language="java" contentType="text/html; ISO-8859-1"
         pageEncoding="utf-8"
         isELIgnored="false"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Remind Home</title>
</head>
<body>

<form action="${contextPath}/member/registerMemberForm.do">
    <button type="submit">회원가입</button>
</form>

<form>
    <input type="text" name="userId">
    <input type="text" name="userPwd">
</form>
</body>
</html>
