<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>단어 수정페이지</title>
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
                    if (data == "sucess") {
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
                    if (data == "success") {
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
<div class="container">
    <div class="card shadow-lg border-0 rounded-lg mt-5">
        <div class="card-header">
            <h3 class="text-center font-weight-light my-4">단어 수정</h3>
        </div>
        <div class="card-body">
            <div class="form-group">
                <label class="small mb-1" for="word">단어</label>
                <input class="form-control py-4" type="text" id="word" name="word" value="${wordvo.word}">
            </div>
            <div class="form-group">
                <label class="small mb-1" for="mean">의미</label>
                <input class="form-control py-4" ype="text" id="mean" name="mean" value="${wordvo.mean}">
            </div>
            <div class="form-group text-center">
                <span class="badge badge-primary">저장 날짜:${wordvo.savedDate}</span>
                <span class="badge badge-success">복습 날짜${wordvo.appearanceDate}</span>
            </div>
            <input type="hidden" id="wordId" name="wordId" value="${wordvo.wordId}">
        </div>
        <div class="card-footer text-center">
            <a href="${contextPath}/word/wordBoardForm.do">
                <button class="btn btn-primary">취소하기</button>
            </a>
            <input class="btn btn-primary" type="button" value="수정하기" onclick="modifyWord()">
            <input class="btn btn-danger" type="button" value="삭제하기" onclick="deleteWord()">
        </div>
    </div>
</div>
</body>
</html>