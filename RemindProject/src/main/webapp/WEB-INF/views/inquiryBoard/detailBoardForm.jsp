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
                var applyButton = document.getElementsByName("applyAnswerButton")[index];
                alert(applyButton);
                if (!applyButton) {
                    var modifyAnswerButton = document.createElement("input");
                    modifyAnswerButton.setAttribute("type", "button");
                    modifyAnswerButton.setAttribute("name", "applyAnswerButton");
                    modifyAnswerButton.setAttribute("class", "btn btn-primary mt-1");
                    modifyAnswerButton.setAttribute("onclick", "modifyAnswer(" + index + ")");
                    modifyAnswerButton.setAttribute("value", "적용하기");
                    modifyTable.appendChild(modifyAnswerButton);
                } else {
                    return;
                }
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

        function deleteAnswer(index) {
            var AnswerIdValue = document.getElementsByName("AnswerId")[index].value;
            $.ajax({
                type: "delete",
                url: "${contextPath}/inquiryBoard/deleteAnswer/" + AnswerIdValue,
                success: function (data) {
                    alert(data);
                    location.reload();
                },
                error: function (request) {

                }
            });
        }
    </script>

</head>
<body>
<div class="container-fluid">
    <div class="justify-content-center">
        <div class="card shadow-lg border-0 rounded-lg mt-5">
            <div class="card-header">
                <h3 class="text-center font-weight-light my-4">${inquiryBoardVO.title}</h3>
            </div>
            <div class="card-body justify-content-center">
                <div class="form-group">
                    ${inquiryBoardVO.content}
                </div>
                <div class="form-group">
                    <c:choose>
                        <c:when test="${not empty imageVO}">
                            <c:forEach var="item" items="${imageVO}" varStatus="itemStatus">
                                <img class="img-thumbnail mx-auto d-block"
                                     src="${contextPath}/download.do?boardId=${item.boardId}&imageFileName=${item.imageFileName}"
                                     alt="error">
                                <input type="hidden" id="imageFileName" name="imageFileName"
                                       value="${item.imageFileName}">
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" id="imageFileName" name="imageFileName" value="empty">
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="form-group ">
                    <div class="row justify-content-center">
                        <span class="badge badge-primary">${inquiryBoardVO.userId}</span>
                        <span class="badge badge-info">${inquiryBoardVO.writeDate}</span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row justify-content-center">
                        <c:if test="${userId==inquiryBoardVO.userId}">
                            <input class="btn btn-primary" type="button"
                                   onclick="modifyBoard(${inquiryBoardVO.boardId})" value="글 수정하기">
                            <input class="btn btn-danger" type="button"
                                   onclick="deleteBoard(${inquiryBoardVO.boardId})" value="글 삭제하기">
                        </c:if>
                    </div>
                </div>

                <c:if test="${not empty AnswerVO}">
                <c:forEach var="item" items="${AnswerVO}" varStatus="itemStatus">
                    <hr>
                    <div class="form-group">
                        <label class="small mb-1" for="AnswerContent${itemStatus.index}">${item.userId}</label>
                        <span class="badge badge-primary mt-1">${item.savedDate}</span>
                        <div class="row justify-content-between">
                        <textarea class="form-control" id="AnswerContent${itemStatus.index}" name="AnswerContent"
                                  disabled rows="4" cols="30"
                                  maxlength="1000">${item.content}</textarea>
                            <input type="hidden" id="AnswerId" name="AnswerId" value="${item.answerId}">
                            <c:if test="${userId==item.userId}">
                            <div name="modifyTable" class="align-items-center">
                                <input class="btn btn-primary mt-1" type="button" value="댓글 수정하기"
                                       onclick="setModifyAnswer(${itemStatus.index})">
                            </div>
                            <input class="btn btn-warning mt-1" type="button" value="댓글 삭제하기"
                                   onclick="deleteAnswer(${itemStatus.index})">

                        </div>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
            </c:if>
            <div class="card-footer text-center">
                <div class="form-group">
                    <textarea class="form-control" id="content" name="content" placeholder="댓글 입력"
                              rows="4" cols="30"
                              maxlength="1000"></textarea>
                    <input class="btn btn-primary mt-1" type="button" value="댓글 등록"
                           onclick="writeAnswer(${inquiryBoardVO.boardId})">
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>