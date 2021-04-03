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

        function checkValidText() {
            var form = document.getElementById("userForm")
            var userName = document.getElementById("userName");
            var userNameValue = userName.value;
            if (userNameValue == "" || userNameValue.length <= 0) {
                alert("이름을 입력해주세요.");
                userName.focus();
            } else if (!checkValidEmail) {
                return;
            } else {
                form.submit();
            }
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-5">
            <div class="card shadow-lg border-0 rounded-lg mt-5">
                <div class="card-header">
                    <h3 class="text-center font-weight-light my-4">
                        Find ID
                    </h3>
                </div>
                <div class="card-body">
                    <div class="small mb-3 text-muted">가입에 사용된 이름, 이메일을 입력하시면 가입한 아이디를 출력합니다.</div>
                    <form id="userForm" action="${contextPath}/member/findUserId" method="get">
                        <div class="form-group">
                            <label class="small mb-1" for="userName">Name</label>
                            <input class="form-control py-4" type="text" id="userName" name="userName"
                                   placeholder="Enter Name">
                        </div>
                        <div class="form-group">
                            <label class="small mb-1" for="email">Email</label>
                            <input class="form-control py-4" type="text" name="email" id="email"
                                   placeholder="Enter Email">
                        </div>
                        <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                            <a class="small" href="${contextPath}/main/main.do">Return to Main</a>
                            <input class="btn btn-primary" type="button" value="Find" onclick="checkValidText()">
                        </div>
                    </form>
                </div>
                <div class="card-footer text-center">
                    <div class="small">
                        <a href="${contextPath}/member/registerMemberForm.do">Need an account? Sing up!</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
