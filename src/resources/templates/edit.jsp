<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<h2>Новая запись</h2>
<c:choose>
    <c:when test="${empty person}">

        <form action="" method="post">
            <label>Фамилия</label><input type="text" name="surname">
            <label>Имя</label><input type="text" name="name"><label>Адрес</label><textarea name="address"></textarea>
            <button type="submit">Сохранить</button>
        </form>
    </c:when>
    <c:otherwise>
        <form action="" method="post">
        <table border="1">
            <tr>

                <td>Фамилия</td>
                <td>Имя</td>
                <td>адрес</td>
            </tr>

                <tr valign="top">

                    <td><input type="text" name="surname" value="${person.surname}"></td>
                    <td><input type="text" name="name" value="${person.name}"></td>
                        <%-- The c:out will escape html/xml characters. --%>
                    <td><textarea name="address">${person.address}</textarea></td>
                </tr>

        </table>
            <input type="hidden" name="id" value="${person.id}">
            <button type="submit">Усё!</button>
        </form>
    </c:otherwise>
</c:choose>
</body>
</html>
