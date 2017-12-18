<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<spring:url value="http://code.jquery.com/jquery-2.2.4.min.js" var="jQueryScript" />
<script type="text/javascript" src="${jQueryScript}"></script>

<spring:url value="/resources/js/userNamePanel.js" var="userNameScript" />
<script type="text/javascript" src="${userNameScript}"></script>

<title>Wirtualny Dziekanat Strona Glowna</title>
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
		<h2>WirtualnyDziekanat projekt prezentacyjny</h2>

		<br> <br>

		<h1>${msg}</h1>
	</div>

	<div align="left">
		<ul>
		<li><a href="/WirtualnyDziekanat/user/create">Utworz uzytkownika</a></li> 
		<li><a href="/WirtualnyDziekanat/user/login">Logowanie</a></li>
		<li><a href="/WirtualnyDziekanat/user/detail">Panel uzytkownika</a></li>
		<li><a href="/WirtualnyDziekanat/student/detail">Panel studenta</a></li>
		<li><a href="/WirtualnyDziekanat/teacher/panel">Panel nauczyciela (nie dziala)</a>
		<li><a href="/WirtualnyDziekanat/admin/panel">Panel Administratora</a></li>
		<li><a href="/WirtualnyDziekanat/author">O Autorze</a></li>
		<li><a href="/WirtualnyDziekanat/project">O projekcie</a></li> 
		</ul>	
	</div>

</body>
</html>
