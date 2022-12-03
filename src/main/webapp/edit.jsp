<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:wrapper>

	
				<h1>Registration</h1>


				<div class="row">
					<form class="col s12">
						<div class="row">
							<div class="input-field col s12 m6 l4">

								<input id="first_name" type="text" class="validate"> <label
									for="first_name">First Name</label>
							</div>
							<div class="input-field col s12 m6 l4">
								<input id="last_name" type="text" class="validate"> <label
									for="last_name">Last Name</label>
							</div>
							<div class="input-field col s12 m6 l4">
								<input id="Patronymic" type="text" class="validate"> <label
									for="Patronymic">Patronymic</label>
							</div>
						</div>
						<div class="row">
							<div class="input-field col s12  m6 l4">

								<input placeholder="the number of points in the subject 1"
									id="Sybject 1" type="text" class="validate"> <label
									for="the number of points in the subject 1">Subject 1 </label>
							</div>
							<div class="input-field col s12 m6 l4">
								<input placeholder="the number of points in the subject 2"
									id="Sybject 2" type="text" class="validate"> <label
									for="the number of points in the subject 2">Subject 2 </label>
							</div>
							<div class="input-field col s12 m6 l4">
								<input placeholder="the number of points in the subject 3"
									id="Sybject 3" type="text" class="validate"> <label
									for="the number of points in the subject 3">Subject 3 </label>
							</div>
							<div class="input-field col s12 m6 l4">
								<input placeholder="points in certificate" id="Certificate"
									type="text" class="validate"> <label for="Certificate">Certificate
								</label>
							</div>

						</div>
						<div class="row">
							<div class="input-field col s12 m12 l8">
								<i class="material-icons prefix">lock_outline</i> <input
									id="password" type="password" class="validate"> <label
									for="password">Password</label>
							</div>
						</div>
						<div class="row">
							<div class="input-field col s12 m12 l8">
								<i class="material-icons prefix">email</i> <input id="email"
									type="email" class="validate"> <label for="email">Email</label>
							</div>
						</div>
						<div class="row">
							<div class="col s12">
								This is an inline input field:
								<div class="input-field inline">
									<input id="email_inline" type="email" class="validate">
									<label for="email_inline">Email</label> <span
										class="helper-text" data-error="wrong" data-success="right">Helper
										text</span>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="row">
					<div class="col s12 input-field center-align">
						<a class="btn waves-effect waves-light red" href="index.jsp"><i
							class="material-icons left">list</i>List</a> <a
							class="btn waves-effect waves-light green" href="list.jsp"><i
							class="material-icons left">save</i>Save</a>
					</div>
				</div>
</t:wrapper>

