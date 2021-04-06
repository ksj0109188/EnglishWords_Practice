
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>아이디찾기 출력 페이지</title>
    <script>
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="card mb-4">
        <c:choose>
            <c:when test="${message eq 'empty'}">
                <div class="card-body">
                    <h1 class="mt-4">해당 이름과 이메일로 등록된 계정이 없습니다.</h1>
                </div>
            </c:when>
            <c:otherwise>
                <div class="card-header">가입된 아이디</div>
                <div class="card-body">
                    <div class="table-responsive">
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="table" width="100%" cellpadding="0"
                                       role="grid" style="width: 100%;">
                                    <tread class="thead-dark">
                                        <tr role="row">
                                            <th>계정</th>
                                            <th>이메일</th>
                                            <th>가입날짜</th>
                                            <th>인증여부</th>
                                        </tr>
                                    </tread>
                                    <tbody>
                                    <c:forEach var="item" items="${memberVOItems}">
                                        <c:choose>
                                            <c:when test="${item.authKey eq 'Y'}">
                                                <tr>
                                                    <td>${item.userId}</td>
                                                    <td>${item.email}</td>
                                                    <td>${item.joinDate}</td>
                                                    <td>완료</td>
                                                </tr>
                                            </c:when>
                                            <c:otherwise>
                                                <tr>
                                                    <td>${item.userId}</td>
                                                    <td>${item.email}</td>
                                                    <td>${item.joinDate}</td>
                                                    <td>필요</td>
                                                </tr>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        <div class="card-footer text-center">
            <div class="small">
                <a class="" href="${contextPath}/main/main.do">Return to main</a>
                <a href="${contextPath}/member/findUserPwdForm.do">Find Password</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
