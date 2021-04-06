<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>회원가입창</title>
    <script>
        var checkOverlapIdBoolean = false;

        function checkValidId() {
            var userId = document.getElementById("userId");
            var str = userId.value;
            var regul1 = /^[a-zA-Z0-9]{4,12}$/;
            if (str == "") {
                alert("아이디를 입력해주세요");
                userId.focus();
                return false;
            } else if (str.length < 4 || str.length > 12) {
                alert("아이디는 길이는 4~12이내입니다.");
                userId.focus();
                return false;
            } else if (regul1.test(str)) {
                return true;
            } else {
                alert("아이디는 영어, 숫자 조합만 이용가능합니다.");
                userId.focus();
                return false;
            }
        }

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

        function checkValidEmail() {
            var email = document.getElementById("userEmail");
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

        function checkOverlap() {
            var userBox = document.getElementById("userId");
            var overlapButton = document.getElementById("overlapButton");
            if (userBox.value == "") {
                alert("아이디를 올바르게 입력해주세요.");
                return;
            } else if (!checkValidId()) {
                return;
            }
            $.ajax({
                method: "get",
                url: "${contextPath}/member/overlap?userId=" + userBox.value,
                success: function (data) {
                    if (data == "notOverlapping") {
                        alert("사용할 수 있는 아이디입니다.");
                        checkOverlapIdBoolean = true;
                        userBox.setAttribute("readOnly", "true");
                        overlapButton.setAttribute("disabled", "true");
                    } else {
                        alert("중복된 아이디입니다.");
                    }
                },
                error: function () {
                    alert("잠시후 다시 시도해주세요.");
                }
            });
        }

        function submitForm() {
            var form = document.getElementById("userForm");
            var submitButton = document.getElementById("submitButton");
            var userNameValue = document.getElementById("userName").value;
            var phone1Value = document.getElementById("phone1").value;
            var phone2Value = document.getElementById("phone2").value;
            var phone3Value = document.getElementById("phone3").value;
            if (!checkValidId()) {
                return;
            } else if (!checkValidPassword()) {
                return;
            } else if (!checkPasswordOverlap()) {
                return;
            } else if (!checkValidEmail()) {
                return;
            } else if (checkOverlapIdBoolean == false) {
                alert("아이디 중복확인을 해주세요.");
                return;
            } else if (phone1Value == "" || phone2Value == "" || phone3Value == "") {
                alert("휴대폰 번호를 입력해주세요.");
                return;
            } else if (userNameValue == "") {
                alert("이름을 입력해주세요.")
            } else {
                submitButton.setAttribute("disabled","true");
                form.submit();
            }
        }


    </script>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-7">
            <div class="card shadow-lg border-0 rounded-lg mt-5">
                <div class="card-header">
                    <h3 class="text-center font-weight-light my-4">Create Account</h3>
                </div>
                <div class="card-body">
                    <form id="userForm" action="${contextPath}/member/addMember" method="post">
                        <div class="form-group">
                            <label class="small mb-1" for="userName">Name</label>
                            <input class="form-control py-4" type="text" id="userName" name="userName"
                                   placeholder="Enter Name">
                        </div>
                        <div class="form-group">
                            <label class="small mb-1" for="userId">ID</label>
                            <div class="row">
                                <div class="col-md-9">
                                    <input class="form-control py-4" type="text" id="userId" name="userId" minlength="4"
                                           maxlength="12" placeholder="Enter ID">
                                </div>
                                <div class="col-md-3 align-self-center">
                                    <input class="btn btn-info" type="button" id="overlapButton" value="중복확인하기"
                                           onclick="checkOverlap()">
                                </div>
                            </div>
                            <div class="small mb-3 text-muted">아이디는 4~12자 이내</div>
                        </div>

                        <div class="form-row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="small mb-1" for="userPwd1">Password</label>
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
                            <label class="small mb-1" for="userEmail">Email</label>
                            <input class="form-control py-4" type="email" id="userEmail" name="email"
                                   placeholder="Enter Email">
                        </div>
                        <div>
                            <label class="small mb-1" for="phone1">Phone Number</label>
                        </div>
                        <div class="form-row ">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <select class="form-control py-2" id="phone1" name="phone1">
                                        <option>없음</option>
                                        <option selected value="010">010</option>
                                        <option value="011">011</option>
                                        <option value="016">016</option>
                                        <option value="017">017</option>
                                        <option value="018">018</option>
                                        <option value="019">019</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-4 ">
                                <div class="form-group">
                                    <input class="form-control py-2" type="text" id="phone2" name="phone2">
                                </div>
                            </div>
                            <div class="col-md-4 ">
                                <div class="form-group">
                                    <input class="form-control py-2" type="text" id="phone3"
                                           name="phone3">
                                </div>
                            </div>
                        </div>
                        <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                            <a class="small" href="${contextPath}/main/main.do">Return to Main</a>
                            <input class="btn btn-primary" type="button" id="submitButton" value="가입하기"
                                   onclick="submitForm()">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
