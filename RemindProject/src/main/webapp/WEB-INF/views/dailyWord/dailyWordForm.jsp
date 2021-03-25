<%@ page language="java" contentType="text/html; ISO-8859-1"
         pageEncoding="utf-8"
         isELIgnored="false"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>dailyWord Page</title>
    <script type="text/javascript" src="<c:url value="/webjars/jquery/2.2.1/jquery.min.js"/>"></script>
    <script>
        function moveTomyWord(index) {
            var word = document.getElementsByName("word")[index].value
            var mean = document.getElementsByName("mean")[index].value
            var dailyId = document.getElementsByName("dailyId")[index].value

            $.ajax({
                type: "post",
                async: true,
                url: "${contextPath}/word/DailyWord",
                data: {
                    dailyId: dailyId,
                    word: word,
                    mean: mean
                },
                success: function () {
                    alert("추가되었습니다.");
                },
                error: function (request, error) {
                    alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                }
            });
        }
    </script>
</head>
<body>
<form>
    <c:forEach items="${dailyWordVO}" var="item" varStatus="itemStatus">
        <div>
            <input type="text" name="word" value="${item.word}">
            <input type="text" name="mean" value="${item.mean}">
            <input type="hidden" name="dailyId" value="${item.dailyId}">
            <input type="button" value="새 카드로 이동" onclick="moveTomyWord(${itemStatus.index})">
        </div>
    </c:forEach>
</form>
매일 8시에 갱신됩니다.<br>
출처<br>
네이버 사전<br>
https://learn.dict.naver.com/m/endic/today/words.nhn
</body>
</html>
