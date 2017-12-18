<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Zarzadzanie przedmiotem</title>
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

<center><h2>Zarzadzanie przedmiotem ${subject.name}</h2></center>

<h4>Lista grup w ktorych wystepuje przedmiot</h4>
<table>
	<tr>
		<th>Nazwa grupy</th>
		<th>Id</th>
		<th>Usun przedmiot z grupy</th>
	</tr>
	
	<c:forEach var="tempStudentGroup" items="${studentGroupListWithSubject}">
					
		<c:url var="removeLink" value="/admin/updateSubjectRemoveGroup">
			<c:param name="removedGroupId" value="${tempStudentGroup.id}" />
			<c:param name="subjectId" value="${subject.id}" />
		</c:url>
						
		<tr>
			<td>${tempStudentGroup.name}</td>
			<td>${tempStudentGroup.id}</td>
			<td><a href="${removeLink}">Usun</a></td>
		</tr>
				
	</c:forEach>
</table>

<br><br>

<h4>Lista grup w ktorych nie wystepuje przedmiot</h4>
<table>
	<tr>
		<th>Nazwa grupy</th>
		<th>Id</th>
		<th>Dodaj przedmiot do grupy</th>
	</tr>
	
	<c:forEach var="tempStudentGroup" items="${studentGroupListWithoutSubject}">
					
		<c:url var="addLink" value="/admin/updateSubjectAddGroup">
			<c:param name="addedGroupId" value="${tempStudentGroup.id}" />
			<c:param name="subjectId" value="${subject.id}" />
		</c:url>
						
		<tr>
			<td>${tempStudentGroup.name}</td>
			<td>${tempStudentGroup.id}</td>
			<td><a href="${addLink}">Dodaj</a></td>
		</tr>
				
	</c:forEach>
</table>



</body>
</html>