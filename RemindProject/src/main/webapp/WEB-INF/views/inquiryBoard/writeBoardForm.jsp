<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>문의 게시판 작성</title>
    <script>
        var cnt = 0;

        function addImage() {
            if (cnt == 0) {
                $("#add_ImageForm").append("<input class='custom-file-input' type='file' name='imageFileName' id='imageFileName' onchange='setImage(this)'/>");
                $("#add_ImageForm").append(" <label class='custom-file-label' for='imageFileName'>Choose file</label>");
                cnt++;
            } else {
                alert("이미지는 한 개만 추가가 가능합니다.");
            }
        }

        function setImage(event) {
            if (event.files && event.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    var img = document.getElementsByName("boardImgName");
                    var imgSize = img.length;
                    if (imgSize >= 1) {
                        img[0].setAttribute("src", e.target.result);
                    } else {
                        var img = document.createElement("img");
                        img.setAttribute("id", "boardImg");
                        img.setAttribute("name", "boardImgName");
                        img.setAttribute("src", e.target.result);
                        img.setAttribute("class", "mt-2 img-thumbnail mx-auto d-block");
                        document.getElementById("image_container").appendChild(img);
                    }
                }
                reader.readAsDataURL(event.files[0]);
            }
        }

        function writeBoard() {
            var form = document.getElementById("boardForm");
            var title = document.getElementById("title");
            var content = document.getElementById("content");
            if (title.value == "" || content.value == "") {
                alert("재목, 내용을 반드시 입력해주세요.");
            } else {
                form.submit();
            }
        }
    </script>

</head>

<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-7">
            <div class="card shadow-lg border-0 rounded-lg mt-5">
                <div class="card-header">
                    <h3 class="text-center font-weight-light my-4">Write an Inquiry Board</h3>
                </div>
                <div class="card-body">
                    <form id="boardForm" method="post" enctype="multipart/form-data"
                          action="${contextPath}/inquiryBoard/writeBoard">
                        <div class="form-group">
                            <label class="small mb-1" for="title">Title</label>
                            <input class="form-control py-4" type="text" id="title" name="title" maxlength="100"
                                   placeholder="글 제목을 입력하세요">
                            <label class="small mb-1" for="content">Content</label>
                            <textarea class="form-control" id="content" name="content" placeholder="글을 입력하세요."
                                      rows="10" maxlength="1000"></textarea>
                        </div>
                        <div class="form-group">
                            <div class="row justify-content-center">
                            <input class="btn btn-success mt-1" type="button" onclick="addImage()" value="이미지 추가">
                            </div>
                            <div id="image_container"></div>
                        </div>
                        <div class="custom-file" id="add_ImageForm"></div>
                    </form>
                </div>
                <div class="card-footer">
                    <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                        <a class="small" href="${contextPath}/inquiryBoard/boardForm">Return to board page</a>
                        <input class="btn btn-primary" type="button" id="submitButton" value="글쓰기"
                               onclick="writeBoard()">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
