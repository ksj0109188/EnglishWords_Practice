<%--
  Created by IntelliJ IDEA.
  User: kim
  Date: 2021/02/18
  Time: 7:06 오후창
  To change this template use File | Settings | File Templates.
--%>
<%-- ajax를 두번 호출한 이유, 처음 rest API를 이용해 학습할 수 만큼 데이터를 가지고 오려 했다. 하지만 데이터베이스에 저장된 단어의 출력시간을 체크해서
계속 요청 받아야 하는 로직이로 REST API를 사용하기엔 HTML폼 태그가 너무 많아지고, 로직이 복잡해져 유지보수가 어렵다 판단 다음과 같은 코드를 작성했다.--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>RevieCardStudy Page</title>
    <script type="text/javascript" src="<c:url value="/webjars/jquery/2.2.1/jquery.min.js"/>"></script>
    <script>

        function showAnswer() {
            document.getElementById("showAnswerButton").style.display = "none";
            document.getElementById("mean").style.display = "block";
            document.getElementById("appropriateButton").style.display = "block";
            document.getElementById("reviewButton").style.display = "block";
        }

        function review() {
            updateWord("review");
        }

        function appropriate() {
            updateWord("appropriate");
        }

        function updateWord(command) {
            var wordCount = document.getElementById("wordCount");
            var wordId = document.getElementById("wordId");
            var studyQuantity = document.getElementById("studyQuantity");
            var wordcount = wordCount.value;
            var wordIdValue = wordId.value;
            var studyQuantityValue = studyQuantity.value;
            if (command == "review") {
                $.ajax({
                    type: "put",
                    dataType: "json",
                    async: true,
                    contentType: "application/json",
                    url: "${contextPath}/word/reviewStudy_wrong",
                    data: JSON.stringify({
                        wordId: wordIdValue
                    }),
                    success: function (data) {
                        studyQuantityValue = parseInt(studyQuantityValue) + 1
                        studyQuantity.value = studyQuantityValue;
                        reviewCardSelect();
                    },
                    error: function (request, error) {
                        alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                    }
                });


            } else if (command == "appropriate") {
                $.ajax({
                    type: "put",
                    async: true,
                    dataType: "json",
                    contentType: "application/json",
                    url: "${contextPath}/word/reviewStudy_appropriate",
                    data: JSON.stringify(
                        {
                            wordCount: wordcount,
                            wordId: wordIdValue
                        }),
                    success: function (data) {
                        studyQuantityValue = parseInt(studyQuantityValue) + 1
                        studyQuantity.value = studyQuantityValue;
                        reviewCardSelect();
                    },
                    error: function (request, status, error) {
                        alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                    }
                });

            }
        }

        function reviewCardSelect() {
            $.ajax({
                type: "get",
                async: true,
                url: "${contextPath}/word/reviewStudy",
                success: function (data) {
                    if (data == 'Empty') {
                        alert('학습할 것이 없습니다.');
                        location.href = "${contextPath}/word/StudyForm.do";
                    } else {
                        receiveDataHandle(data);
                    }
                },
                error: function (request, error) {
                    alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                }
            });
        }

        function receiveDataHandle(data) {
            document.getElementById("word").value = data.word;
            document.getElementById("wordCount").value = data.wordCount;
            document.getElementById("wordId").value = data.wordId;
            var mean = document.getElementById("mean")
            mean.value = data.mean;
            mean.style.display = "none";
            document.getElementById("showAnswerButton").style.display = "block";
            document.getElementById("appropriateButton").style.display = "none";
            document.getElementById("reviewButton").style.display = "none";
        }
    </script>
</head>
<body>
<c:choose>
    <c:when test="${empty wordvo}">
        <strong>학습할 단어가 없습니다. 새로운 카드를 학습하거나 추가하세요.</strong>
    </c:when>
    <c:otherwise>
        <form action="${contextPath}/word/addWord.do">
            <div name="studyDiv" style="display: block">
                <input type="text" id="word" name="word" value="${wordvo.word}"><br>
                <input type="text" id="mean" name="mean" value="${wordvo.mean}" style="display: none">
                <input type="button" id="showAnswerButton" name="showAnswerButton" onclick="showAnswer()"
                       value="답보기">
                <input type="button" id="reviewButton" name="reviewButton" style="display: none"
                       onclick="review()" value="다시하기">
                <input type="button" id="appropriateButton" name="appropriateButton" style="display: none"
                       onclick="appropriate()" value="알맞음">
                <input type="hidden" id="wordCount" name="wordCount" value="${wordvo.wordCount}">
                <input type="hidden" id="wordId" name="wordId" value="${wordvo.wordId}">
            </div>
            <div>
                학습량<input type="text" id="studyQuantity" value="0">
            </div>
        </form>
    </c:otherwise>
</c:choose>
</body>
</html>