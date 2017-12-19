<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logi serwera</title>
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

<center><h2>Logi serwera</h2></center>

<br><br>

<table width="100%">
	<tr>
		<th>Id logu</th>
		<th>Link do profilu uzytkownika</th>
		<th>Typ logu</th>
		<th>Szczegoly logu</th>
	</tr>
	
	<c:forEach var="tempLog" items="${logs}">
					
		<c:url var="detailLink" value="/admin/showUserDetail">
			<c:param name="userId" value="${tempLog.userDetail.user.id}" />
		</c:url>
		
		<c:set var="userDetail" value="${tempLog.userDetail.firstName} ${tempLog.userDetail.lastName}" />
				
		<tr>
			<td><center>${tempLog.id}</center></td>
			<td><center><a href="${detailLink}">${userDetail}</a></center></td>
			<td><center>${tempLog.type}</center></td>
			<td>${tempLog.detail}</td>
		</tr>
	</c:forEach>

</table>

</body>
</html>