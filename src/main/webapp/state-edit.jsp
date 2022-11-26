<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="pageTitle" value="State edit" scope="application"/>
<t:wrapper>
<c:choose>
<c:when test="${empty dto.id}">
	<div class="row">
				<div class="col s12">
					<div class="center-align">
							<h1>Create state</h1>
					</div>
				</div>
			</div>
		
		</c:when>
		<c:otherwise>
			<div class="row">
				<div class="col s12">
					<div class="center-align">
							<h1>Edit state #${dto.id}</h1>
					</div>
				</div>
			</div>
		
		</c:otherwise>
		</c:choose>
		<form class="col s12" method="post" action="/state">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
				<div class="input-field col s6">
					<input type="text" name="name" required value="${dto.name}"> <label for="name">name</label>
				</div>
			</div>
	
		
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/state"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
		
		
		
</t:wrapper>