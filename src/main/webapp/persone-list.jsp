<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="pageTitle" value="Persone list" scope="application"/>
<t:wrapper>
		<h1>Persone list</h1>
	<div class="row">
		<div class="col s12">
			<div class="center-align">
				<a class="btn-floating btn-large waves-effect waves-light" href="/persone?view=edit"><i class="material-icons">add</i></a>
			</div>
		</div>
	</div>
	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>role_id</th>
				<th>first_name</th>
				<th>second_name</th>
				<th>patronymic</th>
				<th>mail</th>
				<th>actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entity" items="${list}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${entity.id}" /></td>
					<td><c:out value="${entity.roleName}" /></td>
					<td><c:out value="${entity.firstName}" /></td>
					<td><c:out value="${entity.secondName}" /></td>
					<td><c:out value="${entity.patronymic}" /></td>
					<td><c:out value="${entity.mail}" /></td>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="редактировать" href="/persone?view=edit&id=${entity.id}"><i
							class="material-icons">edit</i></a><a class="btn-small btn-floating waves-effect waves-light red" title="удалить" onclick="sendHTTPDelete('/persone?id=${entity.id}')"><i class="material-icons">delete</i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
</t:wrapper>