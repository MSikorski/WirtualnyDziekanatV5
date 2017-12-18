<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Zarzadzaj uzytkownikami</title>
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

<center>
<h2>Zarzadzanie uzytkownikami</h2>
<h4>${msg}</h4>
</center>


<h3>Lista uzytkownikow</h3>

<table>
	<tr>
		<th>Nazwa uzytkownika</th>
		<th>Imie</th>
		<th>Nazwisko</th>
		<th>Administrator</th>
		<th>Nauczyciel</th>
		<th>Student</th>
		<th>Szczegoly</th>
	</tr>
	
	<c:forEach var="tempUser" items="${userList}" varStatus="status">
					
		<c:url var="detailLink" value="/admin/showUserDetail">
			<c:param name="userId" value="${tempUser.id}" />
		</c:url>
	
	<tr>
		<td><center>${tempUser.userName}</center></td>
		<td><center>${tempUser.userDetail.firstName}</center></td>
		<td><center>${tempUser.userDetail.lastName}</center></td>
		
		<c:choose>
		<c:when test = "${tempUser.userDetail.adminDetail.id > 0}">
			<td><center><input type="checkbox" checked onclick="return false;"/></center></td>
		</c:when>
		<c:otherwise>
			<td><center><input type="checkbox" onclick="return false;"/></center></td>
		</c:otherwise>
		</c:choose>
		
		<c:choose>
		<c:when test = "${tempUser.userDetail.teacherDetail.id > 0}">
			<td><center><input type="checkbox" checked onclick="return false;"/></center></td>
		</c:when>
		<c:otherwise>
			<td><center><input type="checkbox" onclick="return false;"/></center></td>
		</c:otherwise>
		</c:choose>
		
		<c:choose>
		<c:when test = "${tempUser.userDetail.studentDetail.id > 0}">
			<td><center><input type="checkbox" checked onclick="return false;"/></center></td>
		</c:when>
		<c:otherwise>
			<td><center><input type="checkbox" onclick="return false;"/></center></td>
		</c:otherwise>
		</c:choose>
		
		<td><center><a href="${detailLink}">Link</a></center></td>
	</tr>

	</c:forEach>

</table>

</body>
</html>