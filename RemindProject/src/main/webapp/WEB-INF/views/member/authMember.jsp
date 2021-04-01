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
        <title>인증 완료창</title>
        <script>

        </script>
</head>
<body>
<div>
        <p>Remind웹 사이트에 오신걸 환영합니다.<p>
        <p>아래 링크를 통해서 로그인후 해당 웹 페이지를 이용해주세요!!</p>
        <a href="${contextPath}/main/main.do">로그인하기</a>
</div>
</body>
</html>
