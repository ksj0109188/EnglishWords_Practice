<%@ page language="java" contentType="text/html; ISO-8859-1"
         pageEncoding="utf-8"
         isELIgnored="false"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title><tiles:insertAttribute name="title"/></title>
    <%--    jquery  --%>
    <script type="text/javascript" src="<c:url value="/webjars/jquery/2.2.1/jquery.min.js"/>"></script>
    <%--    bootStrap core  --%>
    <link rel="stylesheet" href="${contextPath}/webjars/bootstrap/4.6.0/css/bootstrap.min.css">
    <script src="${contextPath}/webjars/bootstrap/4.6.0/js/bootstrap.min.js"></script>

    <%--    custome style--%>
    <link href="${contextPath}/resources/css/styles.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"
            crossorigin="anonymous"></script>
    <style type="text/css">
        .layoutFooter {
            width: 100%;
            height: 100px;
            position: fixed;
            bottom: 0;
            background: #5eaeff;
            text-align: center;
            color: white;
        }
    </style>
</head>
<body class="bg-primary">

<header>
    <tiles:insertAttribute name="header"/>
</header>

<article>
    <tiles:insertAttribute name="body"/>
</article>

<footer>
    <tiles:insertAttribute name="footer"/>
</footer>

</body>
</html>
