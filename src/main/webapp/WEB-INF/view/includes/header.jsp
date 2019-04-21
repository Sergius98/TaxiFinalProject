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
    <title>
        <fmt:message key="taxi_name"/>
    </title>
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
            <div class="col-10">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}${PATH}/home">
                            <fmt:message key="home"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}${PATH}/discounts?page=1">
                            <fmt:message key="discounts"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}${PATH}/loyalties?page=1">
                            <fmt:message key="loyalties"/>
                        </a>
                    </li>
                    <!-- import user or admin or guest header -->
                    <c:choose>
                        <c:when test="${ROLE == 0}">
                            <%@include file="/WEB-INF/view/includes/guest/header.jsp" %>
                        </c:when>
                        <c:when test="${ROLE == 1}">
                            <%@include file="/WEB-INF/view/includes/user/header.jsp" %>
                        </c:when>
                        <c:when test="${ROLE == 2}">
                            <%@include file="/WEB-INF/view/includes/user/header.jsp" %>
                            <%@include file="/WEB-INF/view/includes/admin/header.jsp" %>
                        </c:when>
                    </c:choose>
                </ul>


            </div>
            <!-- Brand/logo -->
            <div class="col-2 text-right">
                <a class="navbar-brand my_logo" href="#">
                    <fmt:message key="taxi_name"/>
                </a>
            </div>
        </nav>
    </div>
<%----%>
    <div class="col-lg-2 dropdown open"
         data-toggle="tooltip" data-placement="right" title="<fmt:message key="change_language_message"/>">
        <button class="btn btn-outline-dark btn-sm dropdown-toggle" type="button" id="dropdownMenuButton"
                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <fmt:message key="language"/>
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <c:forEach items="${LANGUAGES_LIST}" var="language">
                <a class="dropdown-item" href="${pageContext.request.contextPath}${ACTION_URI}?lang=${language}"><fmt:message key="${language}"/></a>
            </c:forEach>
        </div>
    </div>

    <div class="col-lg-2 dropdown open"
         data-toggle="tooltip" data-placement="right" title="<fmt:message key="change_currency_message"/>" style="margin-top: 2px">
        <button class="btn btn-outline-dark btn-sm dropdown-toggle" type="button" id="dropdownMenuButton2"
                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <fmt:message key="currency"/>
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <c:forEach items="${CURRENCY_LIST}" var="currency">
                <a class="dropdown-item" href="${pageContext.request.contextPath}${ACTION_URI}?curr=${currency}"><fmt:message key="${currency}"/></a>
            </c:forEach>
        </div>
    </div>
<%----%>

<c:if test="${not empty ALERT}">
    <div class="alert alert-danger" role="alert"  style="margin-top: 5px">
        <c:out value="${ALERT}"/>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>

</c:if>