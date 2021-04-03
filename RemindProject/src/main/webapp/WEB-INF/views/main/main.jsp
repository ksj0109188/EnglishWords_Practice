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
            location.href = "${contextPath}/main/introduction.do";
        </script>
    </c:if>

</head>
<body class="bg-primary">
<div class="col-lg-5">

</div>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-sm">
            <div class="jumbotron mt-5">
                <h1>반갑습니다!!!</h1>
                <p>Remind는 효율적인 영어단어 학습이 가능한 웹 사이트이며 비용없이 사이트를 이용하실 수 있습니다.</p>
                <p>로그인 후 웹 페이지를 이용하실 수 있으며, 민감한 개인정보를 묻지 않으니 걱정없이 사용하실 수 있습니다.</p>
                <p>사용자가 외울 단어를 직접 저장하거나 매일 업데이트되는 단어를 통해 영어단어를 쉽게 암기해보세요.</p>
            </div>
        </div>
        <div class="col-sm">
            <div class="card shadow-lg border-0 rounded-lg mt-5">
                <div class="card-header">
                    <h3 class="text-center font-weight-light my-4">Login</h3>
                </div>
                <div class="card-body">
                    <form action="${contextPath}/member/login" method="post">
                        <div class="form-group">
                            <label class="small mb-1" for="userId">ID</label>
                            <input type="id" class="form-control py-4" id="userId" name="userId"
                                   placeholder="Enter id">
                        </div>
                        <div class="form-group">
                            <label class="small mb-1" for="userPwd">PASSWORD</label>
                            <input type="password" class="form-control py-4" id="userPwd" name="userPwd"
                                   placeholder="Enter password">
                        </div>
                        <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                            <a class="small" href="${contextPath}/member/findUserIdForm.do">Forgot ID?</a>
                            <a class="small" href="${contextPath}/member/findUserPwdForm.do">Forgot Password?</a>
                            <input class="btn btn-primary" type="submit" value="Login">
                        </div>
                    </form>
                </div>
                <div class="card-footer text-center">
                    <div class="small"><a href="${contextPath}/member/registerMemberForm.do">Sing up</a></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
