<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<div class="container">
    <h2>Привет <sec:authentication property="principal.username"/></h2>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/logout" role="button">Бежим отсюда!</a>
    <c:choose>
        <c:when test="${empty persons}">
            <p>There are no comments in system yet.</p>
        </c:when>
        <c:otherwise>
            <table class="table table-bordered table-hover table-striped">
            <thead class="thead-dark">
            <tr>
                <th>INDEX</th>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>адрес</th>
                <sec:authorize access="hasRole('ADMIN')">
                    <th>Редактировать</th>
                    <th>Удалить нафиг!</th>
                </sec:authorize>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${persons}" var="comment" varStatus="status">

                <tr>
                    <td>${status.index+1}</td>
                    <td>${comment.surname}</td>
                    <td>${comment.name}</td>
                        <%-- The c:out will escape html/xml characters. --%>
                    <td>
                        <pre><c:out value="${comment.address}"/></pre>
                    </td>
                    <sec:authorize access="hasRole('ADMIN')">
                        <td><a href="./edit?id=${comment.id}">ага</a></td>
                        <td><a href="./del?id=${comment.id}">ага</a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
            </table>
            <sec:authorize access="hasRole('ADMIN')">
                <a class="btn btn-primary" href="./new" role="button">создать, блин</a>
            </sec:authorize>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
