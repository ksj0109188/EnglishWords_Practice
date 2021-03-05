<%@ page language="java" contentType="text/html; ISO-8859-1"
         pageEncoding="utf-8"
         isELIgnored="false"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <script type="text/javascript" src="<c:url value="/webjars/jquery/2.2.1/jquery.min.js"/>"></script>
    <script>
        function testclik(){
            var _user_id = document.getElementById("userId");
            var _word = document.getElementById("word");
            var _mean = document.getElementById("mean");
            var word = _word.value;
            var mean = _mean.value;
            var user_id = _user_id.value;
        $.ajax({
            type : "put",
            dataType : "json",
            data :JSON.stringify({user_id : user_id, word : word, mean : mean}),
            url : "${contextPath}/word/test.do",
            contentType : "application/json",
            success : function (data){

            }
            }
        );
        }
    </script>
    <title>Remind Home</title>

    <c:if test="${not empty message}">
        <script>
            window.onload = function () {
                alert("아이디 혹은 비밀번호가 틀렸습니다 다시 로그인해주세요.");
            }
        </script>
    </c:if>
</head>
<body>

<form action="${contextPath}/member/registerMemberForm.do" method="post">
    <button type="submit">회원가입</button>
</form>

<form action="${contextPath}/member/login.do" method="post">
    <input type="text" id="userId" name="userId">
    <input type="button" onclick="testclik()" value="테스트 여기 클릭">
    <input type="text" id="word" name="word">
    <input type="text" id="mean" name="mean">
    <input type="password" name="userPwd">
    <input type="submit" value="로그인">
</form>
</body>
</html>
