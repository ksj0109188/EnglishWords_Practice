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
<script type="text/javascript" src="<c:url value="/webjars/jquery/2.2.1/jquery.min.js"/>"></script>
<html>
<head>
    <title>비밀번호 찾기 페이지</title>
    <script>
        function findUserPwd() {
            var userIdValue = document.getElementById("userId").value;
            var emailValue = document.getElementById("email").value;
            $.ajax({
                method: "get",
                url: "${contextPath}/member/findUserPwd",
                data: {userId: userIdValue, email: emailValue},
                success: function (data) {
                    if (data == "founded") {
                        alert("입력하신 이메일로 임시 비밀번호를 발송했습니다.");
                        location.href = "${contextPath}/main/main.do";
                    } else {
                        alert("입력하신 이름과 이메일로 등록된 아이디가 존재하지 않습니다.");
                    }
                }, error: function () {
                    alert("죄송합니다. 잠시 후 다시 시도해주세요.");
                }
            });
        }
    </script>
</head>
<body>

<div>
    <form>
        <input type="text" id="userId" name="userId" value="아이디">
        <input type="text" id="email" name="email" value="이메일">
        <input type="button" value="비밀번호 찾기" onclick="findUserPwd()">
    </form>
</div>

</body>
</html>
