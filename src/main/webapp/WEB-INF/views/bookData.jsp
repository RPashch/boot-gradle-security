<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Book info</title>
	
	<style type="text/css">
	        .tg {
	            border-collapse: collapse;
	            border-spacing: 0;
	            border-color: #ccc;
	        }
	        .tg td {
	            font-family: Arial, sans-serif;
	            font-size: 14px;
	            padding: 10px 5px;
	            border-style: solid;
	            border-width: 1px;
	            overflow: hidden;
	            word-break: normal;
	            border-color: #ccc;
	            color: #333;
	            background-color: #fff;
	        }
	        .tg th {
	            font-family: Arial, sans-serif;
	            font-size: 14px;
	            font-weight: normal;
	            padding: 10px 5px;
	            border-style: solid;
	            border-width: 1px;
	            overflow: hidden;
	            word-break: normal;
	            border-color: #ccc;
	            color: #333;
	            background-color: #f0f0f0;
	        }
	        .tg .tg-4eph {
	            background-color: #f9f9f9
	        }
	    </style>
</head>
<body>
	<h1>Information about concrete book</h1>
	<br/>
	
	<table class="tg">
	    <tr>
	        <th width="20">id</th>
	        <th width="120">Title</th>
	        <th width="120">ISBN</th>
	        <th width="120">Genre</th>
	        <th width="120">Authors</th>
	    </tr>
	    <tr>
	        <td>${book.id}</td>
	        <td>${book.title}</td>
	        <td>${book.isbn}</td>
	        <td>${book.genre}</td>
	        <td><a href="<c:url value='/books/data/detail/${book.id}' />">Authors</a></td>
	    </tr>
	</table>	
	<br />
	<br />
	<br />
	<c:if test="${empty allAuthorsThisBook}">
		<h3>Author(or authors) this book is unknown or isn't specified</h3>
	</c:if>
	<c:if test="${!empty allAuthorsThisBook}">
	    <table class="tg">
	        <caption>Author(-s) who have written this book</caption>
	        <tr>
	            <th width="20">id</th>
	            <th width="250">Name and surname</th>
	        </tr>
	        <c:forEach items="${allAuthorsThisBook}" var="author">
	            <tr>
	                <td>${author.id}</td>
	                <td><a href="<c:url value='/authors/data/${author.id}' />" target="_self">${author.firstName} ${author.lastName}</a>	            
	           	</tr>
	        </c:forEach>
	    </table>
	</c:if>
	<br>
	<c:if test="${!empty allAuthors}">
	<h3>If you know author of this book - you may to add it in the down form</h3>
	
	<br>
	<c:url var="addAuthorToBook" value="/books/data/detail/${book.id}"/>
	
	<form:form action="${addAuthorToBook}" commandName="author" method="post">
	    <table>
	        <tr>
	            <td>
	                <form:label path="id">
	                    <spring:message text="List of all authors"/>
	                </form:label>
	            </td>
	            <td>
	                 <form:select path="id" cssStyle="width: 200px;">
	                     <c:forEach items="${allAuthors}" var="author">
	                        <option value="${author.id}">${author.firstName} ${author.lastName}</option>
	                    </c:forEach>
	                </form:select>
	            </td>
	        </tr>
	        <tr>
	            <td colspan="2">
	                    <input type="submit" value="<spring:message text="Add this author to book"/>"/>
	            </td>
	        </tr>
	    </table>
	</form:form>
	</c:if>
</body>
</html>