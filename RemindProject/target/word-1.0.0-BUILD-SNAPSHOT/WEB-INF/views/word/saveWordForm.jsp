<%--
  Created by IntelliJ IDEA.
  User: kim
  Date: 2021/02/18
  Time: 7:06 오후창
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>saveWord Page</title>
</head>
<body>
<form action="${contextPath}/word/addWord.do" method="post">
    <input type="text" name="word">저장할 단어
    <input type="text" name="mean">저장할 의미
    <input type="submit" value="저장하기">
</form>
</body>
</html>
