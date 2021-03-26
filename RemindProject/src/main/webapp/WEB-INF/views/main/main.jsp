<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Remind Home</title>

    <c:if test="${not empty message}">
        <script>
            window.onload = function () {
                alert("아이디 혹은 비밀번호가 틀렸습니다 다시 로그인해주세요.");
            }
        </script>
    </c:if>

    <c:if test="${not empty userId}">
        <script>
            location.href="${contextPath}/main/mainContent";
        </script>
    </c:if>

</head>
<body>

<form action="${contextPath}/member/registerMemberForm.do" method="post">
    <button type="submit">회원가입</button>
</form>

<form action="${contextPath}/member/login" method="post">
    <input type="text" id="userId" name="userId">
    <input type="password" name="userPwd">
    <input type="submit" value="로그인">
</form>
</body>
</html>
