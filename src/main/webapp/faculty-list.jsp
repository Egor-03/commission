<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="pageTitle" value="Faculty list" scope="application"/>
<t:wrapper>
		
	<div class="row">
		<div class="col s12">
			<div class="center-align">
			<h1>Choose faculty</h1>
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
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entity" items="${list}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${entity.id}" /></td>
					<td><c:out value="${entity.name}" /></td>
					<th></th>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="Redact" href="/faculty?view=edit&id=${entity.id}"><i class="material-icons">edit</i></a>
						<a class="btn-small btn-floating waves-effect waves-light green" title="Choose" href="/speciality?facultyId=${entity.id}" ><i class="material-icons">check_circle</i></a>
						<a class="btn-small btn-floating waves-effect waves-light black" title="All requests" href="/request?facultyId=${entity.id}" ><i class="material-icons">list</i></a>
						<a class="btn-small btn-floating waves-effect waves-light red" title="Delete" onclick="sendHTTPDelete('/faculty?id=${entity.id}')"><i class="material-icons">delete</i>
							</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
</t:wrapper>