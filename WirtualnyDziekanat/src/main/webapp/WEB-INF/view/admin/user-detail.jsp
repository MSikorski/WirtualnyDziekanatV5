<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Zarzadzanie uzytkownikiem</title>
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

<div align="center">
	
<h2>Edytowanie profil uzytkownika z panelu administratora</h2>
	
<h3>${msg}</h3>

<form action="saveUserDetail" method="POST">

<form:hidden path="privagles.id" />

<table>
	<tr>
		<td><label>Nazwa uzytkownika:</label></td>
		<td>${checkedUser.userName}</td>
	</tr>

	<tr>
		<td><label>Email:</label></td>
		<td><form:input path="checkedUser.userDetail.email" /></td>
	</tr>

	<tr>
		<td><label>Imie:</label></td>
		<td><form:input path="checkedUser.userDetail.firstName" /></td>
	</tr>

	<tr>
		<td><label>Nazwisko:</label></td>
		<td><form:input path="checkedUser.userDetail.lastName" /></td>
	</tr>

	<tr>
		<td><label>Numer telefonu:</label></td>
		<td><form:input path="checkedUser.userDetail.telephoneNumber" /></td>
	</tr>
				
				
	<tr>
		<td><label>Uprawnienia studenta </label>
		<form:checkbox path="privagles.studentPrivagles"/></td>
					
		<td><label>Uprawnienia nauczyciela </label>
		<form:checkbox path="privagles.teacherPrivagles"/></td>
					
		<td><label>Uprawnienia administratora </label>
		<form:checkbox path="privagles.adminPrivagles"/></td>	
	</tr>

	<tr>
		<td></td>
		<td><input type="submit" value="Zapisz" class="save" /></td>
	</tr>

</table>
</form>
</div>



</body>
</html>