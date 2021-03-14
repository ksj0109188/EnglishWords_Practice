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



    window.onpageshow = function (event) {
        if (event.persisted || (window.performance && window.performance.navigation.type == 2)) {
            location.reload();
        }
    }

    function setReviewCardStudy() {
        var form = document.getElementById("setReviewCardForm");
        var countReviewCard = document.getElementById("countReviewCard");
        var countWrongCard = document.getElementById("countWrongCard");

        if (parseInt(countReviewCard.value) == 0 && parseInt(countWrongCard.value) == 0 ) {
            alert("복습할 카드가 없네요.")
        } else {
            form.submit();
        }
    }

    function setNewCardStudy() {
        var form = document.getElementById("setNewCardForm");
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
오늘 학습할 갯수를 정해주세요.<br>
새로 추가한 카드는 학습할 갯수를 입력후, 새카드 학습하기 버튼을 클릭해주세요.<br>
이미 학습한 카드를 복습하고 싶으시면 학습할 갯수를 입력후, 복습하기 버튼을 클릭해주세요.<br>

학습양의 경우 틀린카드 + 복습/새카드 학습할 단어수를 포함한 갯수를 입력해주세요

<form id="setReviewCardForm" action="${contextPath}/word/reviewCardForm.do" method="get">
    <input type="button" onclick=" setReviewCardStudy()" value="복습하기">
</form>

<form id="setNewCardForm" action="${contextPath}/word/newCardForm.do" method="get">
    <input type="button" onclick="setNewCardStudy()" value="새카드 학습하기">
</form>
<div>

    복습해야할 단어 카드수<input type="text" id="countReviewCard" name="countReviewCard" value="${setting.countReviewCard}">
    복습에 실패한 카드수<input type="text" id="countWrongCard" name="countWrongCard" value="${setting.countWrongReviewCard}">
</div>
<div>
    새로운 단어 카드수<input type="text" id="countNewCard" name="countNewCard" value="${setting.countNewCard}">
    새로운 단어 암기에 실패한 카드수<input type="text" id="countWrongNewCard" name="countWrongNewCard"
                             value="${setting.countWrongNewCard}">
</div>

복습할 단어수는 당신이 학습해야할 복습 단어들의 갯수를 말합니다.<br>
복습에 실패한 카드수는 당신이 복습카드를 학습하며 외우지 못한 카드수를 말합니다.<br>
새로운 카드는 당신이 새로 추가한 단어의 수를 말합니다.<br>
새로운 단어 암기에 실패한 카드수는 당신이 새로쿤 단어 카드를 외우지 못한 카드수입니다. 알맞음을 눌러야 복습할 단어로 단어가 이동합니다.
</body>
</html>