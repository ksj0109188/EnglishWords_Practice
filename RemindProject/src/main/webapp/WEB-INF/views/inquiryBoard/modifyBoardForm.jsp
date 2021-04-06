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
                $("#add_ImageDiv").append("<input class='custom-file-input' type='file' name='imageFileName' id='imageFileName' onchange='setImage(this)'/>");
                $("#add_ImageDiv").append(" <label class='custom-file-label' for='imageFileName'>Choose file</label>");
                cnt++;
            } else {
                alert("이미지는 한 개만 추가가 가능합니다.");
            }
        }

        function setImage(event) {
            if (event.files && event.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    var img = document.getElementsByName("inquiryBoardImage");
                    var imgSize = img.length;
                    if (imgSize >= 1) {
                        img[0].setAttribute("src", e.target.result);
                    } else {
                        var img = document.createElement("img");
                        img.setAttribute("id", "inquiryBoardImage");
                        img.setAttribute("name", "inquiryBoardImage");
                        img.setAttribute("src", e.target.result);
                        img.setAttribute("class", "mt-2 img-thumbnail mx-auto d-block");
                        document.getElementById("image_container").appendChild(img);
                    }
                };
                reader.readAsDataURL(event.files[0]);
            }
        }
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-lg-7">
            <div class="card shadow-lg border-0 rounded-lg mt-5">
                <div class="card-header">
                    <h3 class="text-center font-weight-light my-4">글 수정</h3>
                </div>
                <form action="${contextPath}/inquiryBoard/modifyBoard" method="post" enctype="multipart/form-data">
                    <div class="card-body">
                        <div class="form-group">
                            <label class="small mb-1" for="title">Title</label>
                            <textarea class="form-control" id="title" name="title" rows="2"
                                      maxlength="100">${inquiryBoardVO.title}</textarea>
                        </div>
                        <div class="form-group">
                            <label class="small mb-1" for="content">Content</label>
                            <textarea class="form-control" id="content" rows="10" maxlength="1000"
                                      name="content">${inquiryBoardVO.content}</textarea>
                        </div>
                        <div class="form-group">
                            <div class="row justify-content-center">
                                <span class="badge badge-primary">${inquiryBoardVO.userId}</span>
                                <span class="badge badge-info">${inquiryBoardVO.writeDate}</span>
                            </div>
                        </div>

                        <input type="hidden" name="userId" value="${inquiryBoardVO.userId}">
                        <input type="hidden" name="writeDate" value="${inquiryBoardVO.writeDate}">
                        <input type="hidden" name="boardId" value="${inquiryBoardVO.boardId}">

                        <div class="form-group">
                            <c:forEach var="item" items="${imageVO}" varStatus="itemStatus">
                                <img class="img-thumbnail mx-auto d-block" id="inquiryBoardImage"
                                     name="inquiryBoardImage"
                                     src="${contextPath}/download.do?boardId=${item.boardId}&imageFileName=${item.imageFileName}">
                                <input type="hidden" name="originalFileName" value="${item.imageFileName}">
                                <input type="hidden" name="imageFileId" value="${item.imageFileId}">
                            </c:forEach>
                            <div class="row justify-content-center">
                                <input class="btn btn-success mt-1" ype="button" onclick="modifyImage()" value="이미지 수정">
                            </div>
                            <div id="image_container"></div>
                        </div>
                        <div class="custom-file" id="add_ImageDiv"></div>
                    </div>
                    <div class="card-footer">
                        <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                            <a class="small" href="${contextPath}/inquiryBoard/boardForm">Return to board page</a>
                            <input class="btn btn-primary" type="submit" value="적용하기">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>