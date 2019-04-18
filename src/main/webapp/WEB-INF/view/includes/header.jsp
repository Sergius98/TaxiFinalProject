<%--
  Created by IntelliJ IDEA.
  User: BIGse
  Date: 01-Mar-19
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" />
<html>
<head>
    <meta charset="utf-8">
    <title>MSS: Lab_2</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web_resources/css/bootstrap.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web_resources/css/styles.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web_resources/styles.css" />
    <script src="${pageContext.request.contextPath}/web_resources/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/web_resources/js/popper.js"></script>
    <script src="${pageContext.request.contextPath}/web_resources/js/bootstrap.bundle.js"></script>
</head>
<body>
<div class="container">
    <div class="my_top">
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <!-- Links -->
            <div class="col-4">
                <ul class="navbar-nav">

                    <a class="nav-link" href="${pageContext.request.contextPath}${PATH}/home">
                        <fmt:message key="home"/>
                    </a>
                    <!-- import user or admin or guest header -->
                    <c:choose>
                        <c:when test="${ROLE == 0}">
                            <%@include file="/WEB-INF/view/includes/guest/header.jsp" %>
                        </c:when>
                        <c:when test="${ROLE == 1}">
                            <%@include file="/WEB-INF/view/includes/user/header.jsp" %>
                        </c:when>
                        <c:when test="${ROLE == 2}">
                            <%@include file="/WEB-INF/view/includes/admin/header.jsp" %>
                        </c:when>
                    </c:choose>
                </ul>
            </div>
            <!-- Brand/logo -->
            <div class="col-8 text-right">
                <a class="navbar-brand my_logo" href="#">
                    <fmt:message key="taxi_name"/>
                </a>
            </div>
        </nav>
    </div>
<%--
    <div class="col-lg-2 dropdown open"
         data-toggle="tooltip" data-placement="right" title="${MESSAGE.getString("change_language_message")}">
        <button class="btn btn-outline-dark btn-sm dropdown-toggle" type="button" id="dropdownMenuButton"
                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            ${MESSAGE.getString("language")}
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <c:forEach items="${LANGUAGES_LIST}" var="language">
                <a class="dropdown-item" href="${URL}?language=${language}">${MESSAGE.getString(language)}</a>
            </c:forEach>
        </div>
    </div>
--%>