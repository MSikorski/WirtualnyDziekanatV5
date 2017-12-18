<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>O projekcie</title>
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

<h2>Witam</h2>

<p>Jest to projekt prezentacyjny dla (oby) przyszlych pracodawcow. Projekt opiera sie na frameworkach <b>Spring MVC</b> oraz <b>Hibernate</b>.
<br> Jak widac czesc wizualna jest nadzywczaj uboga, gdyz skupialem sie na <b>back-endzie</b>. Jednoczesnie wlasnie z tym wiaze swoja sciezke
<br> kariery. Jestem typowym umyslem scislym, fizykiem z zamilowania oraz studentem <b>Politechniki Warszawskiej na wydziale Mechanicznym 
<br> Energetyki i Lotnictwa kier. Mechanika i Budowa Maszyn</b> (studia dzienne 2-gi rok). Swoja przygode z programowaniem zaczalem w gimnazjumm 
<br> od prostych skryptow DOS (programy wsadowe z roszerzeniem .bat). Wkrotce pozniej nauczylem sie C++, a w liceum zaczalem uczyc sie Javy.
<br> Mam bardzo analityczny umysl o czym moze swiadczyc wynik z matury z <b>fizyki roszerzonej 94%</b> oraz <b>matematyki roszerzonej 78%</b>.
<br> Jestem takze <b>finalista konkursu "Fizyczne Sciezki" z 2016r.</b> organizowanego przez <b>Narodowe Centrum Badan Jadrowych</b> za zbudowanie
<br> <b>dziala magneto-kinetycznego</b> (bardzo chetnie o tym opowiem zaciekawionym). Obecnie przynaleze do <b>Kola Robotykow Politechniki Warszawskiej</b>.
</p><br><br>
<p>Projekt zostal zaprojektowany na wzor Wirtualnego Dziekanatu/USOS'a i zostal w calosci napisany przeze mnie. Nie pisalem go na niczyje zlecenie,
<br> nie oczekuje takze, ze ktokolwiek mi za niego zaplaci. Jest to jedynie projekt, ktorym chce pokazac, ze posiadam juz podstawowe umiejetnosci, 
<br> ktore w moim miemaniu wystarcza do rozpeczecia komercyjnej kariery. Jesli w panstwa miemaniu te umiejetnosci nie sa wystarczajace, lecz widza panstwo
<br> potencjal to jestem skory do zaczecia od praktyk/stazu. </p>

<h4>Funkcjonalnosc projektu:</h4>
<ul><b>Zaimplantowane</b>
<li>Tworzenie, zapisywanie, przechowywanie uzytkownikow oraz wszelakich danych w bazie danych</li>
<li>Podzial uzytkownikow na studentow, nauczycieli oraz administratorow</li>
<li>Mozliwosc tworzenia grup studenckich, dodawania do nich studentow oraz przedmiotow</li>
<li>Panel administratora z mozliwoscia zarzadzania zaimplementowanymi elementami</li>
<b>Niezaimplementowane</b>
<li>Plan zajec dla kazdej grupy studenckiej</li>
<li>Oceny studentow</li>
<li>Spring Security</li>
<li>Spring AOP - logi czynnosci uzytkownikow</li>
</ul>

<h3>Struktura bazy danych</h3>
<img src="resources/img/DBScheme.png" />




</body>
</html>