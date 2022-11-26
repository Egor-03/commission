<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="pageTitle" value="Request edit" scope="application"/>
<t:wrapper>
<c:choose>
<c:when test="${empty dto.id}">
			<div class="row">
				<div class="col s12">
					<div class="center-align">
						<h1>Create request</h1>
					</div>
				</div>
			</div>

		</c:when>
		<c:otherwise>
		<div class="row">
				<div class="col s12">
					<div class="center-align">
							<h1>Edit request #${dto.id}</h1>
					</div>
				</div>
			</div>	
		</c:otherwise>
		</c:choose>
		<form class="col s12" method="post" action="/request">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
				<div class="col s12 m6 l4">
					<label for="personId">RoleId</label> <select name="personId"
						class="browser-default" required>
						<option value="">--select Persone --</option>
						<c:forEach items="${allPersons}" var="persone">
							<option value="${persone.id}"
								<c:if test="${persone.id eq dto.personId}">selected="selected"</c:if>>${persone.firstName} ${persone.secondName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col s12 m6 l4">
					<label for="specialityId">SpecialityId</label> <select name="specialityId"
						class="browser-default" required>
						<option value="">--select Speciality --</option>
						<c:forEach items="${allSpecialites}" var="speciality">
							<option value="${speciality.id}"
								<c:if test="${speciality.id eq dto.specialityId}">selected="selected"</c:if>>${speciality.name}</option>
						</c:forEach>
					</select>
				
				</div>
				<div class="col s12 m6 l4">
					<label for="stateId">StateId</label> <select name="stateId"
						class="browser-default" required>
						<option value="">--select State --</option>
						<c:forEach items="${allStates}" var="state">
							<option value="${state.id}"
								<c:if test="${state.id eq dto.stateId}">selected="selected"</c:if>>${state.name}</option>
						</c:forEach>
					</select>
				
				</div>
			</div>	
		</div>
		
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/request"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
		
		
		
</t:wrapper>