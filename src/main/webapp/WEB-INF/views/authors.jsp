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
	<title>Authors</title>
	
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
<h1>authors list add/delete/update</h1>

	<br/>
	<a href="<c:url value="/welcome"/>">Back to main page</a>
	<br/><br/><br/>

	<c:if test="${!empty authors}">
	    <table class="tg">
	        <tr>
	            <th width="20">id</th>
	            <th width="200">First name</th>
	            <th width="200">Second name</th>
	            <th width="60">Sex</th>
	            <th width="100">Birth date</th>
	            <th width="60">Edit</th>
	            <th width="60">Delete</th>
	        </tr>
	        <c:forEach items="${authors}" var="author">
	            <tr>
	                <td>${author.id}</td>
	                <td><a href="<c:url value='/authors/data/${author.id}' />" target="_self">${author.firstName}</a></td>
	                <td><a href="<c:url value='/authors/data/${author.id}' />" target="_self">${author.lastName}</a></td>
	                <td>${author.sex}</td>
	                <td>${author.birthDate}</td>
	                <td><a href="<c:url value='/authors/edit/${author.id}' />">Edit</a></td>
	                <td><a href="<c:url value='/authors/remove/${author.id}' />">Delete</a></td>
	            </tr>
	        </c:forEach>
	    </table>
	</c:if>
	
	<h1>Add new author</h1>
	
	<c:url var="addAuthor" value="/authors/save"/>
	
	<form:form action="${addAuthor}" commandName="author" method="post">
	    <table>
	        <c:if test="${!empty author.firstName}">
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
	                <form:label path="firstName">
	                    <spring:message text="First name"/>
	                </form:label>
	            </td>
	            <td>
	                <form:input path="firstName"/>
	            </td>
	        </tr>
	        <tr>
	            <td>
	                <form:label path="lastName">
	                    <spring:message text="Second name"/>
	                </form:label>
	            </td>
	            <td>
	                <form:input path="lastName"/>
	            </td>
	        </tr>
	        <tr>
	            <td>
	                <form:label path="birthDate">
	                    <spring:message text="Birth date (dd.MM.yyyy)"/>
	                </form:label>
	            </td>
	            <td>
	                <form:input path="birthDate"/>
	            </td>
	        
	        </tr>
	        <tr>
	            <td>
	                <form:label path="sex">
	                    <spring:message text="Sex"/>
	                </form:label>
	            </td>
	            <td>
	                 <form:select path="sex" cssStyle="width: 150px;">
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
	                <c:if test="${!empty author.firstName}">
	                    <input type="submit"
	                           value="<spring:message text="Edit author"/>"/>
	                </c:if>
	                <c:if test="${empty author.firstName}">
	                    <input type="submit"
	                           value="<spring:message text="Add author"/>"/>
	                </c:if>
	            </td>
	        </tr>
	    </table>
	</form:form>
	
</body>
</html>