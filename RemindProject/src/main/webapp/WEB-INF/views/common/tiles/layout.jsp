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

</head>
<body>
<div>
    <div>
        <header>
            <tiles:insertAttribute name="header"/>
        </header>

        <article>
            <tiles:insertAttribute name="body"/>
        </article>

        <footer>
            <tiles:insertAttribute name="footer"/>
        </footer>
    </div>
</div>
</body>
</html>
