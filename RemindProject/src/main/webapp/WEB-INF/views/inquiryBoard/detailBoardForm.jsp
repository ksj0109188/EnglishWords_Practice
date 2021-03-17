<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>문의 게시판 상세보기</title>
    <script type="text/javascript" src="<c:url value="/webjars/jquery/2.2.1/jquery.min.js"/>"></script>

    <c:if test="${not empty inquiryItems}">
        <script>
            window.onload = function () {
                alert("게시글이 없습니다.");
            }
        </script>
    </c:if>

    <script>
        function detailBoard(index) {
            document.getElementsByName()
        }

    </script>
</head>
<body>
<table>
    <tr><td>제목</td><td>${inquiryBoardVO.title}</td></tr>
    <tr><td>내용</td><td>${inquiryBoardVO.content}</td></tr>
    <tr><td>작성자</td><td>${inquiryBoardVO.userId}</td></tr>
    <tr><td>글쓴날짜</td><td>${inquiryBoardVO.writeDate}</td></tr>
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
</body>
</html>
