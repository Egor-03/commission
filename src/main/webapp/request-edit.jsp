<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="pageTitle" value="Request edit" scope="application"/>
<t:wrapper>
<c:choose>
<c:when test="${empty dto.id}">
			<h1>Create request</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit request #${dto.id}</h1>
		</c:otherwise>
		</c:choose>
		<form class="col s12" method="post" action="/request">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
				<div class="input-field col s12">  
					<input type="text" name="personId" value="${dto.personId}" > <label for="personId">PersonId</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s6">
					<input type="text" name="specialityId" value="${dto.specialityId}"> <label for="specialityId">specialityId</label>
				</div>
				<div class="input-field col s6">
					<input type="text" name="stateId" value="${dto.stateId}"> <label for="stateId">stateId</label>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/car"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
		
		
		
</t:wrapper>