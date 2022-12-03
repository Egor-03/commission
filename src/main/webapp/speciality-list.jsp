<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="pageTitle" value="Speciality list" scope="application"/>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<c:set var="pageUrl" value="/speciality" scope="page" />
<t:wrapper>
		
	<div class="row">
		<div class="col s12">
			<div class="center-align">
			<h1>Speciality list</h1>
				<a class="btn-floating btn-large waves-effect waves-light" href="/speciality?view=edit"><i class="material-icons">add</i></a>
			</div>
		</div>
	</div>
	<table>
		<thead>
			<tr>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="id">ID</mytaglib:sort-link></th>
				<th><mytaglib:sort-link pageUrl="${pageUrl}" column="name">Name</mytaglib:sort-link></th>
				<th>First_subject</th>
				<th>Second_subject</th>
				<th>Third_subject</th>
				<th>Faculty</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entity" items="${list}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${entity.id}" /></td>
					<td><a href ="/request?specialityId=${entity.id}"> <c:out value="${entity.name}" /> </a></td>
					<td><c:out value="${entity.firstSubjectName}" /></td>
					<td><c:out value="${entity.secondSubjectName}" /></td>
					<td><c:out value="${entity.thirdSubjectName}" /></td>
					<td><c:out value="${entity.facultyName}" /></td>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="редактировать" href="/speciality?view=edit&id=${entity.id}"><i
							class="material-icons">edit</i></a><a class="btn-small btn-floating waves-effect waves-light red" title="удалить" onclick="sendHTTPDelete('/speciality?id=${entity.id}')"><i class="material-icons">delete</i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<t:paging/>	
	
	
</t:wrapper>