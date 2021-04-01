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
    <title>아이디 찾기 페이지</title>
    <script>
        function checkValidEmail() {
            var email = document.getElementById("email");
            var str = email.value;
            var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
            if (str == null || str.length <= 0) {
                alert("이메일을 입력해주세요.");
                email.focus();
                return false;
            } else if (regExp.test(str)) {
                return true;
            } else {
                alert("이메일 주소를 다시 확인해주세요.");
                email.focus();
                return false;
            }
        }

        function checkValidText(){
            var form = document.getElementById("userForm")
            var userName = document.getElementById("userName");
            var userNameValue = userName.value;
            if(userNameValue=="" || userNameValue.length<=0){
                alert("이름을 입력해주세요.");
                userName.focus();
            }else if(!checkValidEmail){
                return;
            }else{
                form.submit();
            }
        }
    </script>
</head>
<body>
<div>
    <form id="userForm" action="${contextPath}/member/findUserId" method="get">
        이름<input type="text" name="userName" id="userName">
        이메일<input type="text" name="email" id="email">
        <input type="button" value="아이디 찾기" onclick="checkValidText()">
    </form>
</div>
</body>
</html>
