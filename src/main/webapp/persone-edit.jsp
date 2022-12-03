<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Persone edit" scope="application" />
<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
		<div class="row">
				<div class="col s12">
					<div class="center-align">
						<h1>Create persone</h1>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
		<div class="row">
				<div class="col s12">
					<div class="center-align">
						<h1>Edit persone #${dto.id}</h1>
					</div>
				</div>
			</div>
			
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/persone">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
				<div class="col s5">
					<label for="roleId">RoleId</label> <select name="roleId"
						class="browser-default" required>
						<option value="">--select Role --</option>
						<c:forEach items="${allRoles}" var="role">
							<option value="${role.id}"
								<c:if test="${role.id eq dto.roleId}">selected="selected"</c:if>>${role.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s3">
				<input type="text" name="firstName" required value="${dto.firstName}">
				<label for="firstName">First_name</label>
			</div>
			<div class="input-field col s3">
				<input type="text" name="secondName" required value="${dto.secondName}">
				<label for="secondName">Second_name</label>
			</div>
			<div class="input-field col s3">
				<input type="text" name="patronymic" required  value="${dto.patronymic}">
				<label for="patronymic">Patronymic</label>
			</div>
			<div class="input-field col s3">
				<input type="email" name="mail" required value="${dto.mail}"> <label
					for="mail">Mail</label>
			</div>
		</div>
		</div>

		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/persone"><i
					class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>



</t:wrapper>