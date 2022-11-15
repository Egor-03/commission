<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="pageTitle" value="Faculty edit" scope="application"/>
<t:wrapper>
<c:choose>
<c:when test="${empty dto.id}">
			<h1>Create faculty</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit faculty #${dto.id}</h1>
		</c:otherwise>
		</c:choose>
		<form class="col s12" method="post" action="/faculty">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
				<div class="input-field col s6">
					<input type="text" name="name" value="${dto.name}"> <label for="name">name</label>
				</div>
			</div>
	
		
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/faculty"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
		
		
		
</t:wrapper>