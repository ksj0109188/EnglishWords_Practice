<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="wordVoItems" value="${wordMap.wordVoItems}"/>
<c:set var="totalCount" value="${wordMap.totalCount}"/>
<c:set var="pageNum" value="${wordMap.pageNum}"/>
<c:set var="section" value="${wordMap.section}"/>
<c:set var="selectMode" value="${wordMap.selectMode}"/>
<c:set var="search" value="${wordMap.search}"/>
<html>
<head>
    <title>단어 게시판 페이지</title>
</head>
<body>
<div style="align-content: center">
<div>
    <form action="${contextPath}/word/title" method="get">
        <input type="text" name="search" placeholder="글 제목을 입력하세요.">
        <input type="submit" value="검색하기">
    </form>
</div>
<div>
    <table>
        <tr>
            <td><span>단어</span></td>
            <td><span>의미</span></td>
            <td><span>저장날짜</span></td>
            <td><span>복습날짜</span></td>
        </tr>

        <c:forEach var="item" items="${wordVoItems}" varStatus="itemStatus">
            <tr>
                <input type="hidden" name="wordIdHidden" value="${item.wordId}">
                <td><a href="${contextPath}/word/modifyWordForm.do/${item.wordId}" methods="get">${item.word}</a></td>
                <td>${item.mean}</td>
                <td>${item.savedDate}</td>
                <td>${item.appearanceDate}</td>
            </tr>
        </c:forEach>
    </table>
</div>


<div>

    <c:if test="${selectMode=='like'}">
        <c:choose>
            <c:when test="${totalCount>100}">
                <c:forEach var="page" begin="1" end="10" step="1">
                    <c:if test="${section>1 && page==1}">
                        <a href="${contextPath}/word/title?section=${section-1}&pageNum=1&search=${search}">pre</a>
                    </c:if>
                    <a href="${contextPath}/word/title?section=${section}&pageNum=${page}&search=${search}">${(section-1)*10+page}</a>
                    <c:if test="${page==10}">
                        <a href="${contextPath}/word/title?section=${section+1}&pageNum=1&search=${search}">next</a>
                    </c:if>
                </c:forEach>
            </c:when>

            <c:when test="${totalCount == 100}">
                <c:forEach var="page" begin="1" end="10" step="1">
                    <a href="${contextPath}/word/title?section=${section}&pageNum=${page}&search=${search}">${page}</a>
                </c:forEach>
            </c:when>

            <c:when test="${totalCount < 100}">
                <c:forEach var="page" begin="1" end="${totalCount/10+1}" step="1">
                    <c:choose>
                        <c:when test="${page==pageNum}">
                            <a style="color:#db7093"
                               href="${contextPath}/word/title?section=${section}&pageNum=${page}&search=${search}">${page}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${contextPath}/word/title?section=${section}&pageNum=${page}&search=${search}">${page}</a>
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
                        <a href="${contextPath}/word/wordBoardForm.do?section=${section-1}&pageNum=1">pre</a>
                    </c:if>
                    <a href="${contextPath}/word/wordBoardForm.do?section=${section}&pageNum=${page}">${(section-1)*10+page}</a>
                    <c:if test="${page==10}">
                        <a href="${contextPath}/word/wordBoardForm.do?section=${section+1}&pageNum=1">next</a>
                    </c:if>
                </c:forEach>
            </c:when>

            <c:when test="${totalCount == 100}">
                <c:forEach var="page" begin="1" end="10" step="1">
                    <a href="${contextPath}/word/wordBoardForm.do?section=${section}&pageNum=${page}">${page}</a>
                </c:forEach>
            </c:when>

            <c:when test="${totalCount < 100}">
                <c:forEach var="page" begin="1" end="${totalCount/10+1}" step="1">
                    <c:choose>
                        <c:when test="${page==pageNum}">
                            <a style="color:#db7093"
                               href="${contextPath}/word/wordBoardForm.do?section=${section}&pageNum=${page}">${page}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${contextPath}/word/wordBoardForm.do?section=${section}&pageNum=${page}">${page}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:when>
        </c:choose>
    </c:if>
</div>
</div>
</body>
</html>