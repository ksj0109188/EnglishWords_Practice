<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>문의 게시판 상세보기</title>
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

        function modifyBoard(boardId) {
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

        function deleteBoard(boardId) {
            var form = document.createElement("form");
            var imageFileName = document.getElementById("imageFileName");
            var boardIdInput = document.createElement("input");
            boardIdInput.setAttribute("type", "hidden");
            boardIdInput.setAttribute("name", "boardId");
            boardIdInput.setAttribute("value", boardId);
            form.setAttribute("method", "post");
            form.setAttribute("action", "${contextPath}/inquiryBoard/deleteBoard");
            form.appendChild(boardIdInput);
            form.appendChild(imageFileName);
            document.body.appendChild(form);
            form.submit();
        }

        function setModifyAnswer(index) {
            var modifyTable = document.getElementsByName("modifyTable")[index];
            var AnswerContent = document.getElementsByName("AnswerContent")[index];
            AnswerContent.removeAttribute("disabled");
            var modifyAnswerButton = document.createElement("input");
            modifyAnswerButton.setAttribute("type", "button");
            modifyAnswerButton.setAttribute("onclick", "modifyAnswer(" + index + ")");
            modifyAnswerButton.setAttribute("value", "적용하기");
            modifyTable.appendChild(modifyAnswerButton);
        }

        function modifyAnswer(index) {
            var AnswerContentValue = document.getElementsByName("AnswerContent")[index].value;
            var AnswerIdValue = document.getElementsByName("AnswerId")[index].value;
            $.ajax({
                type: "put",
                contentType: "application/json",
                url: "${contextPath}/inquiryBoard/modifyAnswer",
                data: JSON.stringify({
                    content: AnswerContentValue,
                    AnswerId: AnswerIdValue
                }),
                success: function (data) {
                    alert(data);
                    location.reload();
                },
                error: function () {
                    alert("에러가 발생했습니다 잠시후 다시 시도해주세요.");
                }
            });
        }

        function deleteAnswer(index){
            var AnswerIdValue = document.getElementsByName("AnswerId")[index].value;
            $.ajax({
                type : "delete",
                url: "${contextPath}/inquiryBoard/deleteAnswer/"+AnswerIdValue,
                success : function (data){
                    alert(data);
                    location.reload();
                },
                error : function (request){

                }
            });
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
            <td><input type="text" name="inquiryBoardContent" value="${inquiryBoardVO.content}" disabled></td>
        </tr>
        <tr>
            <td>작성자</td>
            <td><input type="text" name="inquiryBoardUserId" value="${inquiryBoardVO.userId}" disabled></td>
        </tr>
        <tr>
            <td>글쓴날짜</td>
            <td>${inquiryBoardVO.writeDate}</td>
        </tr>

        <c:choose>
            <c:when test="${not empty imageVO}">
                <c:forEach var="item" items="${imageVO}" varStatus="itemStatus">
                    <tr>
                        <td>
                            <img src="${contextPath}/download.do?boardId=${item.boardId}&imageFileName=${item.imageFileName}"
                                 alt="error">
                        </td>
                    </tr>
                    <input type="hidden" id="imageFileName" name="imageFileName" value="${item.imageFileName}">
                </c:forEach>
            </c:when>
            <c:otherwise>
                <input type="hidden" id="imageFileName" name="imageFileName" value="empty">
            </c:otherwise>
        </c:choose>

        <c:if test="${not empty AnswerVO}">
            <h3>댓글</h3>
            <c:forEach var="item" items="${AnswerVO}" varStatus="itemStatus">
                <tr>
                    <td><input type="text" name="AnswerContent" value="${item.content}" disabled></td>
                    <td>${item.userId}</td>
                    <td>${item.savedDate}</td>
                    <td>${item.boardId}</td>
                    <input type="hidden" name="AnswerId" value="${item.answerId}">

                    <c:if test="${userId==item.userId}">
                        <td name="modifyTable">
                            <input type="button" value="댓글 수정하기" onclick="setModifyAnswer(${itemStatus.index})">
                            <input type="button" value="댓글 삭제하기" onclick="deleteAnswer(${itemStatus.index})">
                        </td>
                    </c:if>
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
        <input type="button" onclick="deleteBoard(${inquiryBoardVO.boardId})" value="글 삭제하기">
    </div>
</c:if>

</body>
</html>