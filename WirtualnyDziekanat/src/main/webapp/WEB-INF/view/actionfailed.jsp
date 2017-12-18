<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<spring:url value="/resources/css/infoboxes.css" var="infoBoxes" />
<link href="${infoBoxes}" rel="stylesheet" />

<spring:url value="http://code.jquery.com/jquery-2.2.4.min.js" var="jQueryScript" />
<script type="text/javascript" src="${jQueryScript}"></script>

<spring:url value="/resources/js/userNamePanel.js" var="userNameScript" />
<script type="text/javascript" src="${userNameScript}"></script>

<meta http-equiv="refresh" content="3;url=/WirtualnyDziekanat/" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Akcja nieudana</title>
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
	
	
<div class="isa_error"><i class="fa fa-times-circle">
<center><h2>Zapytanie nie moglo zostac wykonane</h2></center></i></div>

<br>
<div class="isa_info"><i class="fa fa-times-circle">
<center>Powod: ${msg}</center></i></div>
<center>
<h5>Zostaniesz przeniesiony do strony glownej po 3 sekundach</h5>
<a href="/WirtualnyDziekanat/">Powrot do strony glownej</a>
</center>

</body>
</html>