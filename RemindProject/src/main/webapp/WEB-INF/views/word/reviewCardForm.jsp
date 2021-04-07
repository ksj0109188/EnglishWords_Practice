<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>RevieCardStudy Page</title>
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
            var studyQuantityValue = studyQuantity.innerText;
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
                        studyQuantity.innerText = studyQuantityValue;
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
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-5">
                    <div class="card shadow-lg border-0 rounded-lg mt-5">
                        <div class="card-header">
                            <h3 class="text-center font-weight-light my-4">
                                새카드 학습하기
                            </h3>
                            <h2 class="text-center font-weight-light my-4">
                                <span id="studyQuantity" class="badge badge-dark">0</span>
                            </h2>
                        </div>
                        <div class="card-body">
                            <div class="form-group">
                                <input class="form-control text-center" type="button" id="word" name="word"
                                       value="${wordvo.word}">
                            </div>
                            <div class="form-group">
                                <input class="form-control text-center" type="button" id="mean" name="mean"
                                       value="${wordvo.mean}" style="display: none">
                            </div>
                        </div>
                        <div class="card-footer">
                            <div class="form-group">
                                <input class="btn btn-primary form-control" type="button" id="showAnswerButton"
                                       name="showAnswerButton"
                                       onclick="showAnswer()"
                                       value="답보기">
                            </div>
                            <div class="form-group">
                                <input class="btn btn-danger form-control" type="button" id="reviewButton"
                                       name="reviewButton" style="display: none"
                                       onclick="review()" value="다시하기">
                                <input class="btn btn-success form-control mt-1" type="button" id="appropriateButton"
                                       name="appropriateButton" style="display: none"
                                       onclick="appropriate()" value="알맞음">
                            </div>
                            <input type="hidden" id="wordCount" name="wordCount" value="${wordvo.wordCount}">
                            <input type="hidden" id="wordId" name="wordId" value="${wordvo.wordId}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>