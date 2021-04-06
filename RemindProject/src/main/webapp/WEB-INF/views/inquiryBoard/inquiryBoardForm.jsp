<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="pageNum" value="${boardMap.pageNum}"/>
<c:set var="section" value="${boardMap.section}"/>
<c:set var="inquiryBoardVO" value="${boardMap.inquiryBoardVO}"/>
<c:set var="totalCount" value="${boardMap.totalCount}"/>
<c:set var="selectMode" value="${boardMap.selectMode}"/>
<c:set var="title" value="${boardMap.title}"/>

<html>
<head>
    <title>문의 게시판</title>
    <c:if test="${empty inquiryBoardVO}">
        <script>
            window.onload = function () {
                alert("게시글이 없습니다.");
            }
        </script>
    </c:if>
</head>

<body>
<div class="container">
    <div class="card shadow-lg border-0 rounded-lg mt-5">
        <div class="card-header">
            <form action="${contextPath}/inquiryBoard/title" method="get">
                <div class="input-group mb-3">
                    <input class="form-control" type="text" name="title" placeholder="글 제목을 입력하세요.">
                    <div class="input-group-append">
                        <input class="btn btn-primary" type="submit" value="검색하기">
                    </div>
                </div>
            </form>
        </div>
        <div class="card-body">
            <table class="table text-center">
                <thead class="thead-dark">
                <tr role="row">
                    <th>글번호</th>
                    <th>글제목</th>
                    <th>작성자</th>
                    <th>글쓴 날짜</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${inquiryBoardVO}" varStatus="itemStatus">
                    <tr role="row">
                        <td>${item.boardId}</td>
                        <td><a oninput="" href="${contextPath}/inquiryBoard/board/${item.boardId}"
                               methods="get">${item.title}</a>
                        </td>
                        <td>${item.userId}</td>
                        <td>${item.writeDate}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="row justify-content-center">
                <button class="btn btn-primary"><a class="small text-white"
                                                   href="${contextPath}/inquiryBoard/writeBoardForm.do">글쓰기</a></button>
            </div>
        </div>
        <div class="card-footer text-center">
            <c:if test="${selectMode=='like'}">
                <c:choose>
                    <c:when test="${totalCount>100}">
                        <c:forEach var="page" begin="1" end="10" step="1">
                            <c:if test="${section>1 && page==1}">
                                <a href="${contextPath}/inquiryBoard/title?section=${section-1}&pageNum=1&title=${title}">pre</a>
                            </c:if>
                            <a href="${contextPath}/inquiryBoard/title?section=${section}&pageNum=${page}&title=${title}">${(section-1)*10+page}</a>
                            <c:if test="${page==10}">
                                <a href="${contextPath}/inquiryBoard/title?section=${section+1}&pageNum=1&title=${title}">next</a>
                            </c:if>
                        </c:forEach>
                    </c:when>

                    <c:when test="${totalCount == 100}">
                        <c:forEach var="page" begin="1" end="10" step="1">
                            <a href="${contextPath}/inquiryBoard/title?section=${section}&pageNum=${page}&title=${title}">${page}</a>
                        </c:forEach>
                    </c:when>

                    <c:when test="${totalCount < 100}">
                        <c:forEach var="page" begin="1" end="${totalCount/10+1}" step="1">
                            <c:choose>
                                <c:when test="${page==pageNum}">
                                    <a style="color:#db7093"
                                       href="${contextPath}/inquiryBoard/title?section=${section}&pageNum=${page}&title=${title}">${page}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${contextPath}/inquiryBoard/title?section=${section}&pageNum=${page}&title=${title}">${page}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </c:if>

            <c:if test="${selectMode!='like'}">
                <c:choose>
                    <c:when test="${totalCount>100}">
                        <c:forEach var="page" begin="1" end="10" step="1">
                            <c:if test="${section>1 && page==1}">
                                <a href="${contextPath}/inquiryBoard/boardForm?section=${section-1}&pageNum=1">pre</a>
                            </c:if>
                            <a href="${contextPath}/inquiryBoard/boardForm?section=${section}&pageNum=${page}">${(section-1)*10+page}</a>
                            <c:if test="${page==10}">
                                <a href="${contextPath}/inquiryBoard/boardForm?section=${section+1}&pageNum=1">next</a>
                            </c:if>
                        </c:forEach>
                    </c:when>

                    <c:when test="${totalCount == 100}">
                        <c:forEach var="page" begin="1" end="10" step="1">
                            <a href="${contextPath}/inquiryBoard/boardForm?section=${section}&pageNum=${page}">${page}</a>
                        </c:forEach>
                    </c:when>

                    <c:when test="${totalCount < 100}">
                        <c:forEach var="page" begin="1" end="${totalCount/10+1}" step="1">
                            <c:choose>
                                <c:when test="${page==pageNum}">
                                    <a style="color:#db7093"
                                       href="${contextPath}/inquiryBoard/boardForm?section=${section}&pageNum=${page}">${page}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${contextPath}/inquiryBoard/boardForm?section=${section}&pageNum=${page}">${page}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
