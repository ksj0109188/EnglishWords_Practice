<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>문의 게시판</title>
    <script type="text/javascript" src="<c:url value="/webjars/jquery/2.2.1/jquery.min.js"/>"></script>


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
    <c:forEach var="item" items="${inquiryBoardVO}" varStatus="itemStatus">
        <tr>
            <td><a href="${contextPath}/inquiryBoard/board/${item.boardId}" methods="get">${item.title}</a></td>
            <td>${item.userId}</td>
            <td>${item.writeDate}</td>
            <td>${item.boardId}</td>
        </tr>
    </c:forEach>
</table>
<a href="${contextPath}/inquiryBoard/writeBoardForm.do">글쓰기</a>
</body>
</html>
