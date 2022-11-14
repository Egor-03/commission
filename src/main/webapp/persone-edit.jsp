<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="pageTitle" value="Persone edit" scope="application"/>
<t:wrapper>
<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Create persone</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit persone #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/persone">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
				<div class="input-field col s12">  
					<input type="text" name="roleId" value="${dto.roleId}" ${empty dto.roleId ? '' : 'disabled'} > <label for="roleId">Role</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s3">
					<input type="text" name="firstName" value="${dto.firstName}"> <label for="firstName">First_name</label>
				</div>
				<div class="input-field col s3">
					<input type="text" name="secondName" value="${dto.secondName}"> <label for="secondName">Second_name</label>
				</div>
				<div class="input-field col s3">
					<input type="text" name="patronymic" value="${dto.patronymic}"> <label for="patronymic">Patronymic</label>
				</div>
				<div class="input-field col s3">
					<input type="text" name="mail" value="${dto.mail}"> <label for="mail">Mail</label>
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