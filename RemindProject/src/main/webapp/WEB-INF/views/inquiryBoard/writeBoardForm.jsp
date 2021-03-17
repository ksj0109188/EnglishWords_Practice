<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>문의 게시판 작성</title>
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
        <form action="${contextPath}/inquiryBoard/writeBoard" method="post">
            제목 <input type="text" name="title" placeholder="글의 제목">
            내용 <input type="text" name="content" placeholder="글의 내용">
            이미지 <input type="file" name="imageFileName">
        </form>

</body>
</html>
