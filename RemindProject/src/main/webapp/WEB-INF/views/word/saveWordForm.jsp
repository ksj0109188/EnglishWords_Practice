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
                    mean.value = data.translatedText;
                }, error: function () {
                    alert("잠시후 다시 시도해주세요,");
                }
            });
        }

        function saveWord() {
            var word = document.getElementById("word");
            var mean = document.getElementById("mean");
            $.ajax({
                method: "post",
                url: "${contextPath}/word/word",
                data: {word: word.value, mean: mean.value},
                success: function (data) {
                    alert(data);
                    word.value ="";
                    mean.value ="";
                    word.focus();
                }, error: function () {
                    alert("잠시후 다시 시도해주세요,");
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
                        Save Words
                    </h3>
                </div>
                <div class="card-body">
                    <div class="form-group">
                        <label class="small mb-1" for="word">영어 단어</label>
                        <input class="form-control py-4" type="text" id="word" name="word" maxlength="200"
                               placeholder="저장하실 단어를 입력하세요">
                    </div>
                    <div class="form-group">
                        <label class="small mb-1" for="mean">단어 뜻</label>
                        <input class="form-control py-4" type="text" id="mean" name="mean" maxlength="500"
                               placeholder="저장하실 단어의 뜻을 입력하세요">
                    </div>
                </div>
                <div class="card-footer text-center">
                    <div class="small">
                        <button class="btn btn-primary" type="button" onclick="saveWord()">저장하기</button>
                        <button class="btn btn-success" type="button" onclick="sendToPaPaGO()">번역하기</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
