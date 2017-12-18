<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Zarzadzanie grupa studencka</title>
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

<center><h2>Zarzadzanie grupa studencka ${studentGroup.name}</h2></center>

<form:form action="saveUpdatedStudentGroup" method="POST">

<input type="hidden" name="${helpForm.studentGroupId}"/>

<h4>Lista studentow w grupie</h4>
<table>
	<tr>
		<th>Imie</th>
		<th>Nazwisko</th>
		<th>Numer indeksu</th>
		<th>Specjalizacja</th>
		<th>Usun z grupy</th>
	</tr>
	
	<c:forEach var="tempStudentDetail" items="${helpForm.studentList}" varStatus="status">
					
		<c:set var = "checkBoxPath" value="helpForm.removeCB.field${status.index}" />
						
		<tr>
			<td>${tempStudentDetail.userDetail.firstName}</td>
			<td>${tempStudentDetail.userDetail.lastName}</td>
			<td>${tempStudentDetail.indexNumber}</td>
			<td>${tempStudentDetail.specialization}</td>
			<td><input type="checkbox" name="${checkBoxPath}"/></td>
		</tr>
	</c:forEach>
</table>

<h4>Lista studentow bez grupy mozliwych do dodania</h4>
<table>
	<tr>
		<th>Imie</th>
		<th>Nazwisko</th>
		<th>Numer indeksu</th>
		<th>Specjalizacja</th>
		<th>Dodaj do grupy</th>
	</tr>
	
	<c:forEach var="tempStudentDetailWithoutGroup" items="${helpForm.studentListWithoutGroup}" varStatus="status">
		
		<c:set var = "checkBoxPath" value="helpForm.addCB.field${status.index}" />
				
		<tr>
			<td>${tempStudentDetailWithoutGroup.userDetail.firstName}</td>
			<td>${tempStudentDetailWithoutGroup.userDetail.lastName}</td>
			<td>${tempStudentDetailWithoutGroup.indexNumber}</td>
			<td>${tempStudentDetailWithoutGroup.specialization}</td>
			<td><input type="checkbox" name="${checkBoxPath}"/></td>
		</tr>
		
	</c:forEach>
	
	<tr>
		<td></td><td></td><td></td><td></td>
		<td><input type="submit" value="Zaaktualizuj grupe" /></td>
	</tr>
	
</table>

</form:form>


</body>
</html>