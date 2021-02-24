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
        <title>회원가입창</title>
</head>
<body>
        <form action="${contextPath}/member/addMember.do" method="post">
                <input type="text" name="userId">아이디
                <input type="text" name="userPwd">비밀번호
                <input type="text" name="email">email
                <input type="text" name="address">address
                <input type="submit">가입하기
        </form>
</body>
</html>
