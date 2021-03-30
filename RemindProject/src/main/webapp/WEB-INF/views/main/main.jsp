<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Remind Home</title>
    <script>
        <c:choose>
        <c:when test="${message eq 'loginFail'}">
        alert("아이디 혹은 비밀번호가 틀렸습니다 다시 로그인해주세요.");
        </c:when>
        <c:when test="${message eq 'notAuthorization'}">
        alert("이메일 인증먼저 해주세요.");
        </c:when>
        <c:when test="${message eq 'registerSuccess'}">
        alert("이메일 인증후 해당 계정으로 이용하실수 있습니다.");
        </c:when>
        <c:when test="${message eq 'authFalse'}">
        alert("올바른 이메일로 인증해주세요.");
        </c:when>
        </c:choose>
    </script>
    <c:if test="${not empty userId}">
        <script>
            location.href = "${contextPath}/main/mainContent";
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
<a href="${contextPath}/member/findUserIdForm.do">아이디 찾기</a>
<a href="${contextPath}/member/findUserPwdForm.do">비밀번호 찾기</a>
</body>
</html>
