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
            var data = {boardId: boardId, content: contentValue};
            $.ajax({
                type: "post",
                url: "${contextPath}/inquiryBoard/writeAnswer",
                contentType: "application/json",
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

        function modifyBoard(boardId, obj) {
            var form = document.createElement("form");
            form.setAttribute("method", "get");
            form.setAttribute("action", "${contextPath}/inquiryBoard/modifyBoardForm.do");
            var boardIdInput = document.createElement("input");
            boardIdInput.setAttribute("type", "hidden");
            boardIdInput.setAttribute("name", "boardId");
            boardIdInput.setAttribute("value", boardId);
            form.appendChild(boardIdInput);
            document.body.appendChild(form);
            form.submit();
        }

        function deleteBoard() {
            var modifyForm = document.getElementById("modifyForm");
            modifyForm.action = "${contextPath}/inquiryBoard/delete";
            modifyForm.submit();
        }
    </script>
</head>
<body>
<form>
    <table>
        <tr>
            <td>제목</td>
            <td><input type="text" name="inquiryBoardTitle" value="${inquiryBoardVO.title}" disabled></td>
        </tr>
        <tr>
            <td>내용</td>
            <td><input type="text" name="inquiryBoardTitle" value="${inquiryBoardVO.content}" disabled></td>
        </tr>
        <tr>
            <td>작성자</td>
            <td><input type="text" name="inquiryBoardTitle" value="${inquiryBoardVO.userId}" disabled></td>
        </tr>
        <tr>
            <td>글쓴날짜</td>
            <td>${inquiryBoardVO.writeDate}</td>
        </tr>

        <c:forEach var="item" items="${imageVO}" varStatus="itemStatus">
            <tr>
                <td><img src="${contextPath}/download.do?boardId=${item.boardId}&imageFileName=${item.imageFileName}"
                         alt="error">
                </td>
            </tr>
        </c:forEach>


        <c:if test="${not empty AnswerVO}">
            <h3>댓글</h3>
            <c:forEach var="item" items="${AnswerVO}" varStatus="itemStatus">
                <tr>
                    <td>${item.content}</td>
                    <td>${item.userId}</td>
                    <td>${item.savedDate}</td>
                    <td>${item.boardId}</td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <div>
        <textarea id="content"></textarea>
        <input type="button" value="댓글 등록" onclick="writeAnswer(${inquiryBoardVO.boardId})">
    </div>
</form>
<c:if test="${userId==inquiryBoardVO.userId}">
    <div>
        <input type="button" onclick="modifyBoard(${inquiryBoardVO.boardId})" value="글 수정하기">
        <input type="button" onclick="modifyBoard()" value="글 삭제하기">
    </div>
</c:if>
</body>
</html>