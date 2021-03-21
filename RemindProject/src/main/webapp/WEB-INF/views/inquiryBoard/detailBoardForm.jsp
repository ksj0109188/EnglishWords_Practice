<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>문의 게시판 상세보기</title>
    <script type="text/javascript" src="<c:url value="/webjars/jquery/2.2.1/jquery.min.js"/>"></script>
    <script>
        function writeAnswer(boardId) {
            var contentValue = document.getElementById("content").value;
            var data = { boardId : boardId, content : contentValue };
            $.ajax({
                type: "post",
                url: "${contextPath}/inquiryBoard/writeAnswer",
                contentType : "application/json",
                data: JSON.stringify(data),
                success: function (data) {
                    alert(data);
                    location.reload();
                },
                error: function () {
                    alert("잠시후 다시 시도해주세요.");
                    location.href = "${contextPath}/inquiryBoard/error";
                }
            });
        }
    </script>
    <c:if test="${not empty inquiryItems}">
        <script>
            window.onload = function () {
                alert("게시글이 없습니다.");
            }
        </script>
    </c:if>


</head>
<body>
<table>
    <tr>
        <td>제목</td>
        <td>${inquiryBoardVO.title}</td>
    </tr>
    <tr>
        <td>내용</td>
        <td>${inquiryBoardVO.content}</td>
    </tr>
    <tr>
        <td>작성자</td>
        <td>${inquiryBoardVO.userId}</td>
    </tr>
    <tr>
        <td>글쓴날짜</td>
        <td>${inquiryBoardVO.writeDate}</td>
    </tr>
    <c:forEach var="item" items="${imageVO}" varStatus="itemStatus">
        <tr>
            <td><img
                    src="${contextPath}/download.do?boardId=${item.boardId}&imageFileName=${item.imageFileName}">${item.imageFileName}
            </td>
        </tr>
    </c:forEach>

    댓글
    <c:forEach var="item" items="${AnswerVO}" varStatus="itemStatus">
        <tr>
            <td>${item.content}</td>
            <td>${item.userId}</td>
            <td>${item.savedDate}</td>
            <td>${item.boardId}</td>
        </tr>
    </c:forEach>


</table>

<form>
    <textarea id="content"></textarea>
    <input type="button" value="등록" onclick="writeAnswer(${inquiryBoardVO.boardId})">
</form>

</body>
</html>
