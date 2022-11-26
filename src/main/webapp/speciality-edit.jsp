<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Speciality edit" scope="application" />
<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
			<div class="row">
				<div class="col s12">
					<div class="center-align">
						<h1>Create speciality</h1>
					</div>
				</div>
			</div>

		</c:when>
		<c:otherwise>
			<div class="row">
				<div class="col s12">
					<div class="center-align">
						<h1>Edit speciality #${dto.id}</h1>
					</div>
				</div>
			</div>

		</c:otherwise>
	</c:choose>
	<form class="col s6" method="post" action="/speciality">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="col s4">
				<label for="firstSubjectId">FirstSubjectId</label> <select
					name="firstSubjectId" class="browser-default" required>
					<option value="">--select First subject --</option>
					<c:forEach items="${allSubjects}" var="subject">
						<option value="${subject.id}"
							<c:if test="${subject.id eq dto.firstSubjectId}">selected="selected"</c:if>>${subject.name}</option>
					</c:forEach>
				</select>
			</div>

			<div class=" col s4">
				<label for="secondSubjectId">secondSubjectId</label> <select
					name="secondSubjectId" class="browser-default" required>
					<option value="">--select Second SubjectId --</option>
					<c:forEach items="${allSubjects}" var="subject">
						<option value="${subject.id}"
							<c:if test="${subject.id eq dto.secondSubjectId}">selected="selected"</c:if>>${subject.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class=" col s4">
				<label for="thirdSubjectId">thirdSubjectId</label> <select
					name="thirdSubjectId" class="browser-default" required>
					<option value="">--select Third SubjectId --</option>
					<c:forEach items="${allSubjects}" var="subject">
						<option value="${subject.id}"
							<c:if test="${subject.id eq dto.thirdSubjectId}">selected="selected"</c:if>>${subject.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col s6">
				<label for="facultyId">facultyId</label> <select name="facultyId"
					class="browser-default" required>
					<option value="">--select Faculty --</option>
					<c:forEach items="${allFaculties}" var="faculty">
						<option value="${faculty.id}"
							<c:if test="${faculty.id eq dto.facultyId}">selected="selected"</c:if>>${faculty.name}</option>
					</c:forEach>
				</select>

			</div>
			<div class="input-field col s6">
				<input type="text" name="name" required value="${dto.name}">
				<label for="name">name</label>
			</div>

		</div>

		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/speciality"><i
					class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>



</t:wrapper>