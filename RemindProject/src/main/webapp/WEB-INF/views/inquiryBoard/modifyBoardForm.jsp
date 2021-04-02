<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>문의 게시판 상세보기</title>
    <script>
        var cnt = 0;

        function modifyImage() {
            if (cnt == 0) {
                $("#add_ImageDiv").append("<br>" + "<input  type='file' name='imageFileName' id='imageFileName' onchange='setImage(event)'/>");
                cnt++;
            } else {
                alert("이미지는 한 개만 추가가 가능합니다.");
            }
        }

        function setImage(event) {
            var reader = new FileReader();
            reader.readAsDataURL(event.target.files[0]);

            reader.onload = function (event) {
                var img = document.getElementById("inquiryBoardImage");
                img.setAttribute("src", event.target.result);
            };
        }

    </script>
</head>
<body>
<form action="${contextPath}/inquiryBoard/modifyBoard" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>제목</td>
            <td><input type="text" name="title" value="${inquiryBoardVO.title}"></td>
        </tr>
        <tr>
            <td>내용</td>
            <td><textarea name="content">${inquiryBoardVO.content}</textarea></td>
        </tr>
        <tr>
            <td>작성자</td>
            <td><input type="text" name="userId" value="${inquiryBoardVO.userId}" disabled></td>
        </tr>

        <tr>
            <td>글쓴날짜</td>
            <td><input type="text" name="writeDate" value="${inquiryBoardVO.writeDate}" disabled></td>
        </tr>

        <input type="hidden" name="boardId" value="${inquiryBoardVO.boardId}">
        <c:forEach var="item" items="${imageVO}" varStatus="itemStatus">
            <tr>
                <td><img id="inquiryBoardImage"
                         src="${contextPath}/download.do?boardId=${item.boardId}&imageFileName=${item.imageFileName}">
                </td>
                <input type="hidden" name="originalFileName" value="${item.imageFileName}">
                <input type="hidden" name="imageFileId" value="${item.imageFileId}">
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="적용하기">

    <div>
        <input type="button" onclick="modifyImage()" value="이미지 수정">
        <div id="add_ImageDiv"></div>
    </div>

</form>
</body>
</html>