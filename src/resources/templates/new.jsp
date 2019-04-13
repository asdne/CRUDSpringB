<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
<h2>Новая запись</h2>
<c:choose>
    <c:when test="${empty persons}">

        <form action="" method="post">
            <label>Фамилия</label><input type="text" name="surname">
            <label>Имя</label><input type="text" name="name"><label>Адрес</label><textarea name="address"></textarea>
        <button type="submit">Сохранить</button>
        </form>
    </c:when>
    <c:otherwise>
        <table border="1">
            <tr>
                <td>INDEX</td>
                <td>Фамилия</td>
                <td>Имя</td>
                <td>адрес</td>
            </tr>
            <c:forEach items="${persons}" var="comment" varStatus="status">
                <tr valign="top">
                    <td>${status.index}</td>
                    <td>${comment.surname}</td>
                    <td>${comment.name}</td>
                        <%-- The c:out will escape html/xml characters. --%>
                    <td><pre><c:out value="${comment.address}"/></pre></td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>
