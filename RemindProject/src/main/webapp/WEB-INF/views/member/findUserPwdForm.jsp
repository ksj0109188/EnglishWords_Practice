<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>비밀번호 찾기 페이지</title>
    <script>
        function findUserPwd() {
            var userIdValue = document.getElementById("userId").value;
            var emailValue = document.getElementById("email").value;
            var button = document.getElementById("findPasswordButton");
            button.setAttribute("disabled","true");
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
<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-5">
            <div class="card shadow-lg border-0 rounded-lg mt-5">
                <div class="card-header">
                    <h3 class="text-center font-weight-light my-4">
                        Find Password
                    </h3>
                </div>
                <div class="card-body">
                    <div class="small mb-3 text-muted">계정, 이메일을 입력하시면 해당 이메일로 임시 비밀번호를 발송합니다.</div>
                    <form>
                        <div class="form-group">
                            <label class="small mb-1" for="userId">ID</label>
                            <input class="form-control py-4" type="text" id="userId" name="userId"
                                   placeholder="Enter ID">
                        </div>
                        <div class="form-group">
                            <label class="small mb-1" for="email">Email</label>
                            <input class="form-control py-4" type="text" id="email" name="email" placeholder="Enter email">
                        </div>
                        <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                            <a class="small" href="${contextPath}/main/main.do">Return to Main</a>
                            <input class="btn btn-primary" id="findPasswordButton" type="button" value="Find" onclick="findUserPwd()">
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
