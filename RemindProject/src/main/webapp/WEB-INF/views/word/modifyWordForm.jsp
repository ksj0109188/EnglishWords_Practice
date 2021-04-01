<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>단어 수정페이지</title>
    <script type="text/javascript" src="<c:url value="/webjars/jquery/2.2.1/jquery.min.js"/>"></script>
    <script>
        function modifyWord() {
            var wordIdValue = document.getElementById("wordId").value;
            var wordValue = document.getElementById("word").value;
            var meanValue = document.getElementById("mean").value;
            var data = {
                wordId: wordIdValue,
                word: wordValue,
                mean: meanValue
            }
            $.ajax({
                method: "put",
                url: "${contextPath}/word/modifyWord",
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (data) {
                    if(data =="sucess"){
                        alert("수정되었습니다.");
                        location.href = "${contextPath}/word/wordBoardForm.do";
                    }
                },
                error: function () {
                    alert("잠시후 다시 시도해주세요.");
                    location.href = "${contextPath}/word/error";
                }
            });
        }

        function deleteWord() {
            var wordIdValue = document.getElementById("wordId").value;
            var data = {
                wordId: wordIdValue,
            }
            $.ajax({
                method: "delete",
                url: "${contextPath}/word/deleteWord",
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (data) {
                    if(data =="success"){
                        alert("삭제되었습니다.");
                        location.href = "${contextPath}/word/wordBoardForm.do";
                    }
                },
                error: function () {
                    alert("잠시후 다시 시도해주세요.");
                    location.href = "${contextPath}/word/error";
                }
            });
        }
    </script>
</head>
<body>
<div>
    <input type="hidden" id="wordId" name="wordId" value="${wordvo.wordId}">
    <span>단어<br><input type="text" id="word" name="word" value="${wordvo.word}"></span>
    <span>의미<br><input type="text" id="mean" name="mean" value="${wordvo.mean}"></span>
    <span>저장날짜<br><input type="text" value="${wordvo.savedDate}" disabled></span>
    <span>복습날짜<br><input type="text" value="${wordvo.appearanceDate}" disabled></span>
</div>
<div>
    <input type="button" value="수정하기" onclick="modifyWord()">
    <input type="button" value="삭제하기" onclick="deleteWord()">
</div>
</body>
</html>