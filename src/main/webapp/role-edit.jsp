<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Role edit" scope="application" />
<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
			<div class="row">
				<div class="col s12">
					<div class="center-align">
						<h1>Create role</h1>
					</div>
				</div>
			</div>

		</c:when>
		<c:otherwise>
			<div class="row">
				<div class="col s12">
					<div class="center-align">
						<h1>Edit role #${dto.id}</h1>
					</div>
				</div>
			</div>

		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/role">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="input-field col s6">
				<input type="text" name="text" required value="${dto.name}">
				<label for="name">name</label>
			</div>
		</div>


		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/role"><i
					class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>



</t:wrapper>