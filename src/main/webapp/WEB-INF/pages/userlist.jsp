<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<div class="container">
    <h2>Привет <sec:authentication property="principal.username"/></h2>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/logout" role="button"><i class="fas fa-running"></i> Бежим отсюда!</a>
    <c:choose>
        <c:when test="${empty users}">
            <p>There are no comments in system yet.</p>
        </c:when>
        <c:otherwise>
            <table class="table table-bordered table-hover table-striped">
            <thead class="thead-dark">
            <tr>
                <th>INDEX</th>
                <th>Логин</th>
                <th>Пароль</th>
                <th>Имя</th>
                <sec:authorize access="hasRole('ADMIN')">
                    <th class="text-center">Редактировать</th>
                    <th class="text-center">Удалить нафиг!</th>
                </sec:authorize>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user" varStatus="status">

                <tr>
                    <td>${status.index+1}</td>
                    <td>${user.login}</td>
                    <td>${user.password}</td>
                        <%-- The c:out will escape html/xml characters. --%>
                    <td>
                        <c:forEach items="${user.roles}" var="role" varStatus="stat">
                            <c:out value="${role.role}"/>
                        </c:forEach>
                    </td>
                    <sec:authorize access="hasRole('ADMIN')">
                        <td class="text-center"><a href="./edituser?id=${user.id}"><h1><i class="fas fa-user-cog"></i></h1></a></td>
                        <td class="text-center"><a href="./deluser?id=${user.id}"><h1><i class="fas fa-trash-alt"></i></h1></a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
            </table>
            <sec:authorize access="hasRole('ADMIN')">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/newuser" role="button"><i class="fas fa-plus-circle"></i> создать, блин</a>
            </sec:authorize>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
