<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>회원정보 수정</title>
    <script>
        function checkValidPassword() {
            var userpwd = document.getElementById("userPwd1");
            var pw = userpwd.value;
            var num = pw.search(/[0-9]/g);
            var eng = pw.search(/[a-z]/ig);
            var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
            if (pw == "" || pw.length <= 0) {
                alert("비밀번호를 입력해주세요.");
                userpwd.focus();
                return false;
            }
            if (pw.length < 10 || pw.length > 20) {
                alert("비밀번호를 10자리 ~ 20자리 이내로 입력해주세요.");
                userpwd.focus();
                return false;
            }

            if (pw.search(/₩s/) != -1) {
                alert("비밀번호는 공백없이 입력해주세요.");
                userpwd.focus();
                return false;
            }
            if ((num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0)) {
                alert("영문,숫자, 특수문자 중 2가지 이상을 혼합하여 입력해주세요.");
                userpwd.focus();
                return false;
            }
            return true;
        }

        function checkPasswordOverlap() {
            var original = document.getElementById("userPwd1").value;
            var duplicate = document.getElementById("userPwd2").value;
            if (original != duplicate) {
                alert("비밀번호가 일치하지 않습니다.");
                $('#userPwd2').val('');
                $('#userPwd2').focus();
                return false;
            } else {
                return true;
            }
        }

        function submitModify() {
            var form = document.getElementById("userForm");
            var submitButton = document.getElementById("submitModifyButton");
            var userNameValue = document.getElementById("userName").value;
            var phone1Value = document.getElementById("phone1").value;
            var phone2Value = document.getElementById("phone2").value;
            var phone3Value = document.getElementById("phone3").value;
            if (!checkValidPassword()) {
                return;
            } else if (!checkPasswordOverlap()) {
                return;
            } else if (phone1Value == "" || phone2Value == "" || phone3Value == "") {
                alert("휴대폰 번호를 입력해주세요.");
                return;
            } else if (userNameValue == "") {
                alert("이름을 입력해주세요.");
                return;
            } else {
                submitButton.setAttribute("disabled", "true");
                form.submit();
            }
        }
        function submitdelete(){
            var form = document.getElementById("userForm");
            form.setAttribute("action","${contextPath}/member/deleteMember");
            form.submit();
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-7">
            <div class="card shadow-lg border-0 rounded-lg mt-5">
                <div class="card-header">
                    <h3 class="text-center font-weight-light my-4">Modify Account</h3>
                </div>
                <div class="card-body">
                    <form id="userForm" action="${contextPath}/member/modifyMember" method="post">
                        <div class="form-group">
                            <label class="small mb-1" for="userName">Name</label>
                            <input class="form-control py-4" type="text" id="userName" name="userName"
                                   placeholder="Enter Name" value="${memberVO.userName}">
                        </div>
                        <div class="form-group">
                            <label class="small mb-1" for="userId">ID</label>
                            <input class="form-control py-4" type="text" id="userId" name="userId"
                                   value="${memberVO.userId}" disabled>
                        </div>

                        <div class="form-row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="small mb-1" for="userPwd1">Modify Password</label>
                                    <input class="form-control py-4" type="password" id="userPwd1" name="userPwd"
                                           minlength="10" maxlength="20"
                                           placeholder="Enter Password">
                                    <div class="small mb-1 text-muted">10~20자리 숫자,문자, 특수문자 중 2개 이상을 혼합</div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="small mb-1" for="userPwd2">Confirm Password</label>
                                    <input class="form-control py-4" type="password" id="userPwd2" name="userPwd2"
                                           minlength="10"
                                           maxlength="20" placeholder="Confirm Password">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="small mb-1" for="Authorization">Email Authentication Status</label>
                            <input class="form-control py-4" type="email" id="Authorization" name="Authorization"
                                   value="${memberVO.authKey}" disabled>
                        </div>
                        <div>
                            <label class="small mb-1" for="phone1">Phone Number</label>
                        </div>
                        <div class="form-row ">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <input class="form-control py-2" type="text" id="phone1" name="phone1"
                                           value="${memberVO.phone1}" >
                                </div>
                            </div>
                            <div class="col-md-4 ">
                                <div class="form-group">
                                    <input class="form-control py-2" type="text" id="phone2" name="phone2"
                                           value="${memberVO.phone2}" >
                                </div>
                            </div>
                            <div class="col-md-4 ">
                                <div class="form-group">
                                    <input class="form-control py-2" type="text" id="phone3"
                                           name="phone3" value="${memberVO.phone3}">
                                </div>
                            </div>
                        </div>
                        <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                            <a class="small" href="${contextPath}/main/main.do">Return to Main</a>
                            <input class="btn btn-danger" type="button" id="submitButtonDeleteButton" value="회원탈퇴"
                                   onclick="submitdelete()">
                            <input class="btn btn-primary" type="button" id="submitModifyButton" value="수정하기"
                                   onclick="submitModify()">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
