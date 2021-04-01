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
    <title>아이디찾기_출력 페이지</title>
    <script>
    </script>
</head>
<body>
<div>
    <c:choose>
        <c:when test="${message eq 'empty'}">
            해당 이름과 이메일로 등록된 계정이 없습니다.
        </c:when>
        <c:otherwise>
            <span>발견된 아이디 </span><br>
            <c:forEach var="item" items="${memberVOItems}">
                <input type="text" value="${item.userId}">
                <input type="text" value="${item.email}">
                <input type="text" value="${item.joinDate}">
                <c:choose>
                    <c:when test="${item.authKey eq 'Y'}">
                        <input type="text" value="이메일 인증 완료.">
                    </c:when>
                    <c:otherwise>
                        <input type="text" value="이메일 인증이 필요합니다.">
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
<div>
     <a href="${contextPath}/main/main.do">로그인 하기</a>
    <a href="${contextPath}/member/findUserPwdForm.do">비밀번호 찾기</a>
</div>
</body>
</html>
