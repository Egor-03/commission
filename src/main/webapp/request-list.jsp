<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="pageTitle" value="Request list" scope="application"/>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="pageUrl" value="/request" scope="page" />
<t:wrapper>

		
	<div class="row">
		<div class="col s12">
			<div class="center-align">
			<h1>Request list</h1>
				<a class="btn-floating btn-large waves-effect waves-light" href="/request?view=edit"><i class="material-icons">add</i></a>
			</div>
		</div>
	</div>
	<table>
		<thead>
			<tr>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="id">id</mytaglib:sort-link></th>
				<th>person</th>
				<th>facultyName</th>
				<th>speciality</th>
				<th>state</th>
				<th>actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entity" items="${list}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${entity.id}" /></td>
					<td><c:out value="${entity.personeName}" /></td>
					<td><c:out value="${entity.facultyName}" /></td>
					<td><c:out value="${entity.specialityName}" /></td>
					<td><c:out value="${entity.stateName}" /></td>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="редактировать" href="/request?view=edit&id=${entity.id}"><i
							class="material-icons">edit</i></a><a class="btn-small btn-floating waves-effect waves-light red" title="удалить" onclick="sendHTTPDelete('/request?id=${entity.id}')"><i class="material-icons">delete</i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
<t:paging/>	
</t:wrapper>