<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<spring:url value="/resources/css/table.css" var="tables" />
<link href="${tables}" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Przedmioty</title>
</head>
<body>

<table width="100%">
	<tr>
		<td><center><a href="/WirtualnyDziekanat/">Strona glowna</a></center></td>
			
		<c:if test="${user.userDetail.adminDetail.id > 0 }">
			<c:url var="adminPanel" value="/admin/panel"></c:url>
			<td><center><a href="${adminPanel}">Panel Administratora</a></center></td>
		</c:if>
			
		<c:if test="${user.userDetail.studentDetail.id > 0 }">
			<c:url var="studentPanel" value="/student/detail"></c:url>
			<td><center><a href="${studentPanel}">Panel Studenta</a></center></td>
		</c:if>
			
		<c:if test="${user.userDetail.teacherDetail.id > 0 }">
			<c:url var="teacherPanel" value="/teacher/panel"></c:url>
			<td><center><a href="${teacherPanel}">Panel Nauczyciela (nie dziala)</a></center></td>
		</c:if>
			
		<c:if test="${user.userDetail.id > 0 }">
			<c:url var="userDetail" value="/user/detail"></c:url>
			<c:url var="logout" value="/user/logout"></c:url>
			<td><center><a href="${userDetail}">Panel uzytkownika</a></center></td>
			<td><center><a href="${logout}">Wyloguj</a></center></td>
		</c:if>
	</tr>
</table>

<center><h2>Przedmioty</h2>
<h3>${msg}</h3>
</center>

<h3>Lista przedmiotow</h3>

<input type="button" value="Stworz przedmiot" onclick="window.location.href='createSubject';">

<br><br>

<table>
	<tr>
		<th>Nazwa przedmiotu</th>
		<th>Id przedmiotu</th>
		<th>Szczegoly przedmiotu</th>
		<th>Zarzadzaj</th>
	</tr>
	
	<c:forEach var="tempSubject" items="${subjects}">
					
		<c:url var="updateLink" value="/admin/updateSubject">
		<c:param name="subjectId" value="${tempSubject.id}" />
		</c:url>
				
		<tr>
			<td>${tempSubject.name}</td>
			<td>${tempSubject.id}</td>
			<td>${tempSubject.detail}</td>
			<td><a href="${updateLink}">Link</a></td>
		</tr>
	</c:forEach>

</table>

</body>
</html>