<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="pageTitle" value="Faculty list" scope="application"/>
<t:wrapper>
		<h1>Choose faculty</h1>
	<div class="row">
		<div class="col s12">
			<div class="center-align">
				<a class="btn-floating btn-large waves-effect waves-light" href="/faculty?view=edit"><i class="material-icons">add</i></a>
			</div>
		</div>
	</div>
	<table>
		<thead>
			<tr>
			<th>Faculty id</th>
				<th>Faculty name</th>
				<th></th>
				<th>Register</th>
				<th>Show candidates</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entity" items="${list}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${entity.id}" /></td>
					<td><c:out value="${entity.name}" /></td>
					<th></th>
					<td><a href="/speciality?facultyId=${entity.id}" class="waves-effect waves-light btn">registre</a></td>
					<td><a href="/request" class="waves-effect waves-light btn">show 
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="редактировать" href="/faculty?view=edit&id=${entity.id}"><i
							class="material-icons">edit</i></a><a class="btn-small btn-floating waves-effect waves-light red" title="удалить" onclick="sendHTTPDelete('/faculty?id=${entity.id}')"><i class="material-icons">delete</i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
</t:wrapper>