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
    <title>saveWord Page</title>
    <script>
        function sendToPaPaGO() {
            var word = document.getElementById("word").value;
            $.ajax({
                method: "get",
                url: "${contextPath}/word/papagoAPI/" + word,
                success: function (data) {
                    alert("번역이 완료되었습니다.");
                    var mean = document.getElementById("mean");
                    mean.value =data.translatedText;
                }, error: function () {
                    alert("잠시후 다시 시도해주세요,");
                }
            });
        }

    </script>
</head>
<body>
<div>
    <form action="${contextPath}/word/word" method="post">
        <input type="text" id="word" name="word">저장할 단어
        <input type="text" id="mean" name="mean">저장할 의미
        <input type="submit" value="저장하기">
        <input type="button" onclick="sendToPaPaGO()" value="번역하기">
</div>
</form>
</body>
</html>
