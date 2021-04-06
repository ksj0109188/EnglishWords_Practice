<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Study Page</title>

    <script>
        window.onpageshow = function (event) {
            if (event.persisted || (window.performance && window.performance.navigation.type == 2)) {
                location.reload();
            }
        }

        function setReviewCardStudy() {
            var form = document.createElement("form");
            form.setAttribute("action", "${contextPath}/word/reviewCardForm.do");
            form.setAttribute("method", "get");
            document.body.appendChild(form);
            var countReviewCard = document.getElementById("countReviewCard");
            var countWrongCard = document.getElementById("countWrongCard");

            if (parseInt(countReviewCard.value) == 0 && parseInt(countWrongCard.value) == 0) {
                alert("복습할 카드가 없네요.")
            } else {
                form.submit();
            }
        }

        function setNewCardStudy() {
            var form = document.createElement("form");
            form.setAttribute("action", "${contextPath}/word/newCardForm.do");
            form.setAttribute("method", "get");
            document.body.appendChild(form);
            var countNewCard = document.getElementById("countNewCard");
            var countWrongNewCard = document.getElementById("countWrongNewCard");
            if (parseInt(countNewCard.value) == 0 && parseInt(countWrongNewCard.value) == 0) {
                alert("복습할 카드가 없네요.")
            } else {
                form.submit();
            }
        }
    </script>
</head>
<body>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-6 col-md-4">
            <div class="card bg-success text-white mb-4">
                <div class="card-body">
                    복습 카드
                </div>
                <div class="card-footer d-flex align-items-center justify-content-between"> 개수: ${setting.countReviewCard}
                    <input type="hidden" id="countReviewCard" value="${setting.countReviewCard}">
                    <a id="link_countReviewCard" class="small text-white stretched-link" onclick="setReviewCardStudy()">
                        학습하기
                    </a>
                </div>
            </div>
        </div>

        <div class="col-6 col-md-4">
            <div class="card bg-warning text-white mb-4">
                <div class="card-body">
                    복습에 실패한 카드
                </div>
                <div class="card-footer d-flex align-items-center justify-content-between"> 개수: ${setting.countWrongReviewCard}
                    <input type="hidden" id="countWrongCard" value="${setting.countWrongReviewCard}">
                    <a id="link_countReviewCard" class="small text-white stretched-link" onclick="setReviewCardStudy()">
                        학습하기
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-6 col-md-4">
            <div class="card bg-primary text-white mb-4">
                <div class="card-body">
                    새로운 카드
                </div>
                <div class="card-footer d-flex align-items-center justify-content-between">
                    개수: ${setting.countNewCard}
                    <input type="hidden" id="countNewCard" value="${setting.countNewCard}">
                    <a id="link_countReviewCard" class="small text-white stretched-link" onclick="setNewCardStudy()">
                        학습하기
                    </a>
                </div>
            </div>
        </div>

        <div class="col-6 col-md-4">
            <div class="card bg-danger text-white mb-4">
                <div class="card-body">
                    새로운 단어 암기에 실패한 카드
                </div>
                <div class="card-footer d-flex align-items-center justify-content-between">
                    개수: ${setting.countWrongNewCard}
                    <input type="hidden" id="countWrongNewCard" value="${setting.countWrongNewCard}">
                    <a id="link_countReviewCard" class="small text-white stretched-link" onclick="setNewCardStudy()">
                        학습하기
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="jumbotron bg-light">
            <p>복습 카드 개수는 복습이 필요한 단어들의 개수를 말합니다.<br>복습에 실패한 카드수는 복습 카드를 학습하며 외우지 못한 카드수를 말합니다.</p>
            <p>새로운 카드는 새로 추가한 단어의 수를 말합니다.<br>새로운 단어 암기에 실패한 카드수는 새로운 단어 카드를 외우지 못한 카드 수입니다.</p>
            <p>새로운 카드는 알맞음 버튼을 눌러야 복습할 단어로 이동합니다.</p>
        </div>
    </div>
</div>


</body>
</html>