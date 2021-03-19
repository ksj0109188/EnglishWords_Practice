<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>문의 게시판 작성</title>
    <script type="text/javascript" src="<c:url value="/webjars/jquery/2.2.1/jquery.min.js"/>"></script>
    <script>
        function setImage(event) {
            var reader = new FileReader();
            reader.readAsDataURL(event.target.files[0]);

            reader.onload = function (event) {
                var img = document.createElement("img");
                img.setAttribute("src", event.target.result);
                document.getElementById("image_container").appendChild(img);
            };
        }

    </script>
</head>

<body>

<form method="post" enctype="multipart/form-data"  action="${contextPath}/inquiryBoard/writeBoard">
    <div>
        제목 <input type="text" id="title" name="title" placeholder="글의 제목">
        내용 <input type="text" id="content" name="content" placeholder="글의 내용">
        이미지 <input type="file" name="imageFileName" onchange="setImage(event)">
        <input type="submit" value="글쓰기" >
    </div>
</form>
<div id="image_container"></div>

</body>
</html>
