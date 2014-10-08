<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add New Event</title>
</head>
<body>

<h1>Create New Event</h1>

<c:url var="saveUrl" value="/devops/eventController/events/add" />
<form:form modelAttribute="eventAttribute" method="POST" action="${saveUrl}">
	<table>
		<tr>
			<td><form:label path="name">Name</form:label></td>
			<td><form:input path="name"/></td>
		</tr>

		<tr>
			<td><form:label path="startTime">Start Time</form:label></td>
			<td><form:input path="startTime" type="text" class="date" name="startTime" value='<fmt:formatDate value="${eventAttribute.startTime}" pattern="HH:mm MM-dd-yyyy" />' /></td>
		</tr>
		
		<tr>
			<td><form:label path="endTime">End Time</form:label></td>
			<td><form:input path="endTime" type="text" class="date" name="endTime" value='<fmt:formatDate value="${eventAttribute.endTime}" pattern="HH:mm MM-dd-yyyy" />' /></td>
		</tr>
	</table>
	
	<input type="submit" value="Save" />
</form:form>

</body>
</html>