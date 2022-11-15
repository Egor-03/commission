<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="pageTitle" value="Speciality edit" scope="application"/>
<t:wrapper>
<c:choose>
<c:when test="${empty dto.id}">
			<h1>Create speciality</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit speciality #${dto.id}</h1>
		</c:otherwise>
		</c:choose>
		<form class="col s12" method="post" action="/speciality">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
				<div class="input-field col s12">  
					<input type="text" name="firstSubjectId" value="${dto.firstSubjectId}" > <label for="firstSubjectId">FirstSubjectId</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s6">
					<input type="text" name="secondSubjectId" value="${dto.secondSubjectId}"> <label for="secondSubjectId">SecondSubjectId</label>
				</div>
				<div class="input-field col s6">
					<input type="text" name="thirdSubjectId" value="${dto.thirdSubjectId}"> <label for="thirdSubjectId">ThirdSubjectId</label>
				</div>
				<div class="input-field col s6">
					<input type="text" name="facultyId" value="${dto.facultyId}"> <label for="facultyId">facultyId</label>
				</div>
				<div class="input-field col s6">
					<input type="text" name="name" value="${dto.name}"> <label for="name">name</label>
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