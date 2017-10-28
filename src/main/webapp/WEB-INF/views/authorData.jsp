<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Author info</title>
	
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
</head>
<body>
	<br/>
	<a href="<c:url value="/authors/all"/>">Show all authors</a>
	<br/>
	<h1>Information about one of the authors</h1>
	<br/>
	
	<table class="tg">
	    <tr>
	        <th width="20">id</th>
            <th width="200">First name</th>
            <th width="200">Second name</th>
            <th width="60">Sex</th>
            <th width="100">Birth date</th>
	        <th width="120">Books</th>
	        <th width="120">Rewards</th>
	    </tr>
	    <tr>
	        <td>${author.id}</td>
	        <td>${author.firstName}</td>
	        <td>${author.lastName}</td>
	        <td>${author.sex}</td>
	        <td>${author.birthDate}</td>
	        <td><a href="<c:url value='/authors/data/books/${author.id}' />">List books</a></td>
	        <td><a href="<c:url value='/authors/data/rewards/${author.id}' />">List rewards</a></td>
	    </tr>
	    
	</table>	
	<br />
	<br />
	<br />
	
	<c:if test="${empty allBooksThisAuthor && !empty allBooks}">
	<h3>There aren't books of this author</h3>
	</c:if>
	<c:if test="${!empty allBooksThisAuthor}">
	    <table class="tg">
	        <caption>Books which author has written</caption>
	        <tr>
	            <th width="20">id</th>
	            <th width="250">Title</th>
	        </tr>
	        <c:forEach items="${allBooksThisAuthor}" var="book">
	            <tr>
	                <td>${book.id}</td>
	                <td><a href="<c:url value='/books/data/${book.id}' />" target="_self">${book.title}</a>	            
	           	</tr>
	        </c:forEach>
	    </table>
	</c:if>
	<br>
	<c:if test="${!empty allBooks}">
	<h3>If you know any of books of ${author.firstName} ${author.lastName} - you may to add theirs in the down form</h3>
	
	<br>
	<c:url var="addBookToAuthor" value="/authors/data/books/${author.id}"/>
	
	<form:form action="${addBookToAuthor}" commandName="book" method="post">
	    <table>
	        <tr>
	            <td>
	                <form:label path="id">
	                    <spring:message text="List of all books"/>
	                </form:label>
	            </td>
	            <td>
	                 <form:select path="id" cssStyle="width: 200px;">
	                     <c:forEach items="${allBooks}" var="book">
	                        <option value="${book.id}">${book.title}</option>
	                    </c:forEach>
	                </form:select>
	            </td>
	        </tr>
	        <tr>
	            <td colspan="2">
	                    <input type="submit" value="<spring:message text="Add book"/>"/>
	            </td>
	        </tr>
	    </table>
	</form:form>
	</c:if>
	
	
	
	<c:if test="${!empty flagShowRewardFormAndTable}">
		<c:if test="${empty allRewardsThisAuthor}">
		<h3>There aren't rewards of this author</h3>
		</c:if>
		<c:if test="${!empty allRewardsThisAuthor}">
		    <table class="tg">
		        <caption>Rewards which author was awarded</caption>
		        <tr>
		            <th width="20">id</th>
		            <th width="250">Title</th>
		            <th width="100">Year</th>
		        </tr>
		        <c:forEach items="${allRewardsThisAuthor}" var="rewardExist">
		            <tr>
		                <td>${rewardExist.id}</td>
		                <td>${rewardExist.title}</td>
		                <td>${rewardExist.year}</td>
		           	</tr>
		        </c:forEach>
		    </table>
		</c:if>
		<br>
		
		<br>
		<c:url var="addRewardToAuthor" value="/authors/data/rewards/${author.id}"/>
		
		<form:form action="${addRewardToAuthor}" commandName="reward" method="post">
		    <table>
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
		                <form:label path="year">
		                    <spring:message text="Year"/>
		                </form:label>
		            </td>
		            <td>
		                <form:input path="year"/>
		            </td>
		        </tr>
		        <tr>
		            <td colspan="2">
		                    <input type="submit" value="<spring:message text="Add reward"/>"/>
		            </td>
		        </tr>
		    </table>
		</form:form>
	</c:if>
</body>
</html>