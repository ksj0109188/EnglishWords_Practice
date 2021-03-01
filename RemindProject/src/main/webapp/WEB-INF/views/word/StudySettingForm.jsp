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
        function setStudy(formId,studyQuantity) {
            var form = document.getElementById(formId);
            var studyQuantity = document.getElementById(studyQuantity);
            if (studyQuantity.value < 1 ) {
                alert("1개 이상은 복습 하셔야죠 ㅠㅠ")
            }else{
                form.submit();
            }
        }


    </script>

</head>
<body>
오늘 학습할 갯수를 정해주세요.<br>
새로 추가한 카드는 학습할 갯수를 입력후, 새카드 학습하기 버튼을 클릭해주세요.<br>
이미 학습한 카드를 복습하고 싶으시면 학습할 갯수를 입력후, 복습하기 버튼을 클릭해주세요.<br>
<form id="setReviewCardForm" action="${contextPath}/word/reviewCard.do" method="get">
    <input type="text" name="studyQuantity" id="ReivewQuantity">학습할 양
    <input type="button" onclick=" setStudy('setReviewCardForm','ReivewQuantity')" value="복습하기">
</form>
<form id="setNewCardForm" action="${contextPath}/word/newCard.do" method="get">
    <input type="text" name="studyQuantity" id="NewQuantity">학습할 양
    <input type="button" onclick="setStudy('setNewCardForm','NewQuantity')" value="새카드 학습하기">
</form>


</body>
</html>