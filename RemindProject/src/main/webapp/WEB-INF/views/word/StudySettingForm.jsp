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
    <title>Study Page</title>
    <script>
        function setStudy() {
            var form = document.getElementById("setStudyForm");
            var studyQuantity = document.getElementById("studyQuantity");
            if (studyQuantity.value < 1) {
                alert("1개 이상은 복습 하셔야죠 ㅠㅠ")
            }else{
                form.submit();
            }
        }
    </script>
</head>
<body>
오늘 학습할 갯수를 정해주세요.
<form id="setStudyForm" action="${contextPath}/word/study.do" method="get">
    <input type="text" name="studyQuantity" id="studyQuantity">학습할 양
    <input type="button" onclick="setStudy()">
</form>
</body>
</html>
