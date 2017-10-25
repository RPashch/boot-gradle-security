<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Books</title>
	
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
	<h1>books list add/delete/update</h1>
	
	<br/>
	<a href="<c:url value="/welcome"/>">Back to main page</a>
	<br/><br/><br/>
	
	<c:if test="${!empty books}">
	    <table class="tg">
	        <tr>
	            <th width="80">id</th>
	            <th width="200">Title</th>
	            <th width="120">ISBN</th>
	            <th width="150">Genre</th>
	            <th width="60">Edit</th>
	            <th width="60">Delete</th>
	        </tr>
	        <c:forEach items="${books}" var="book">
	            <tr>
	                <td>${book.id}</td>
	                <td><a href="<c:url value='/books/data/${book.id}' />" target="_self">${book.title}</a></td>
	                <!-- <td><a href="<c:url value='/authors/data/${product.producer.id}'/>" target="_self">${product.producer.name}</a></td> -->
	                <td>${book.isbn}</td>
	                <td>${book.genre}</td>
	                <td><a href="<c:url value='/books/edit/${book.id}' />">Edit</a></td>
	                <td><a href="<c:url value='/books/remove/${book.id}' />">Delete</a></td>
	            </tr>
	        </c:forEach>
	    </table>
	</c:if>
	
	<h1>Add book</h1>

	<c:url var="addBook" value="/books/save"/>
	
	<form:form action="${addBook}" commandName="book" method="post">
	    <table>
	        <c:if test="${!empty book.title}">
	            <tr>
	                <td>
	                    <form:label path="id">
	                        <spring:message text="ID"/>
	                    </form:label>
	                </td>
	                <td>
	                    <form:input path="id" readonly="true" size="8" disabled="false"/>
	                    <%--<form:hidden path="id"/>--%>
	                </td>
	            </tr>
	        </c:if>
	        <tr>
	            <td>
	                <form:label path="title">
	                    <spring:message text="Title"/>
	                </form:label>
	            </td>
	            <td>
	                <form:input path="title"/>
	            </td>
	        </tr>
	        <tr>
	            <td>
	                <form:label path="isbn">
	                    <spring:message text="ISBN"/>
	                </form:label>
	            </td>
	            <td>
	                <form:input path="isbn"/>
	            </td>
	        </tr>
	        <tr>
	            <td>
	                <form:label path="genre">
	                    <spring:message text="Genre"/>
	                </form:label>
	            </td>
	            <td>
	                 <form:select path="genre" cssStyle="width: 150px;">
	                     <form:options/>
	                </form:select>
	            </td>
	        </tr>
	        <%-- <tr>
	            <td>
	                <form:label path="author">
	                    <spring:message text="Author"/>
	                </form:label>
	            </td>
	            <td>
	                <form:select path="author.id" cssStyle="width: 150px;">
	                    <c:if test="${!empty author.name}">
	                        <option value="${product.producer.id}">${product.producer.name}</option>
	                    </c:if>
	                    <c:if test="${empty product.name}">
	                        <option value="1">Select company</option>
	                    </c:if>
	                    <c:forEach items="${listCompanies}" var="company">
	                        <option value="${company.id}">${company.name}</option>
	                    </c:forEach>
	                </form:select>
	            </td>
	        </tr> --%>
	        <tr>
	            <td colspan="2">
	                <c:if test="${!empty book.title}">
	                    <input type="submit"
	                           value="<spring:message text="Edit book"/>"/>
	                </c:if>
	                <c:if test="${empty book.title}">
	                    <input type="submit"
	                           value="<spring:message text="Add book"/>"/>
	                </c:if>
	            </td>
	        </tr>
	    </table>
	</form:form>

</body>
</html>