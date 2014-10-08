<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Events Manager</title>
</head>
<body>
<h1>Events</h1>

<c:url var="addUrl" value="/devops/eventController/events/add" />
<table style="border: 1px solid; width: 1000px; text-align:center">
	<thead style="background:#fcf">
		<tr>
			<th>Event Name</th>
			<th>Address</th>
			<th>Start</th>
			<th>End</th>
			<th>Organizer Name</th>
			<th>Contact</th>
			<th colspan="3"></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${events}" var="event">
			<c:url var="editUrl" value="/devops/eventController/events/edit?id=${event.id}" />
			<c:url var="deleteUrl" value="/devops/eventController/events/delete?id=${event.id}" />
		<tr>
			<td><c:out value="${event.name}" /></td>
			<td>
				<c:out value="${event.address.street1}" /><br>
				<c:if test="${not empty event.address.street2}">
					<c:out value="${event.address.street2}" /><br>
				</c:if>
				<c:out value="${event.address.city}" />, 
				<c:out value="${event.address.state}" /> 
				<c:out value="${event.address.zip}" />
				<c:if test="${not empty event.address.country}">
					<br><c:out value="${event.address.country}" />
				</c:if>
			</td>
			<td><fmt:formatDate pattern="HH:mm MM-dd-yyyy" value="${event.startTime}" /></td>
			<td><fmt:formatDate pattern="HH:mm MM-dd-yyyy" value="${event.endTime}" /></td>
			<td><c:out value="${event.organizer.firstName}" /> <c:out value="${event.organizer.lastName}" /></td>
			<td><c:out value="${event.organizer.email}" /></td>
			<td><a href="${editUrl}">Edit</a></td>
			<td><a href="${deleteUrl}">Delete</a></td>
			<td><a href="${addUrl}">Add</a></td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<c:if test="${empty events}">
	There are currently no events in the list. <a href="${addUrl}">Add</a> an event.
</c:if>

</body>
</html>