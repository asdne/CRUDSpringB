<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
          integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<div class="container">
    <h2>Редактируем пользователя</h2>
    <form action="" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Login</label>
            <div class="col-sm-10">
                <input type="text" name="login" class="form-control" value="${user.login}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Пароль</label>
            <div class="col-sm-10">
                <input type="text" name="password" class="form-control" value="${user.password}">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Роли</label>
            <div class="col-sm-10">
                <select size="2" multiple name="role[]">
                    <c:choose>
                        <c:if test="${user.roles.contains(\"ROLE_USER\")}">
                            <option selected value="ROLE_USER">пользователь</option>
                        </c:if>
                        <c:otherwise>
                            <option value="ROLE_USER">пользователь</option>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${user.roles.contains(ROLE_ADMIN)}">
                            <option selected value="ROLE_ADMIN">админ!</option>
                        </c:when>
                        <c:otherwise>
                            <option value="ROLE_ADMIN">админ!</option>
                        </c:otherwise>
                    </c:choose>
                </select>
            </div>
        </div>
        <div class="form-group row">
            <button type="submit" class="btn btn-primary">Сохранить</button>
        </div>
    </form>
</div>
</body>
</html>
