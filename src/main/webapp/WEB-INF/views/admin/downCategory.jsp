<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<option value="">::하위 카테고리::</option>
<c:forEach var="down" items="${cgList}">
	<option value="${down.downCg_code}">${down.downCg_name}</option>
</c:forEach>