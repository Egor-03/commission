<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="pageTitle" value="faculty edit" scope="application"/>
<t:wrapper>


			<h1>Choose Faculty</h1>
			<table>
				<thead>
					<tr>
						<th>Faculty name</th>
						<th>specialty</th>
						<th></th>
						<th>Register</th>
						<th>Show candidates</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Faculty of Law</td>
						<td>
							<div class="input-field col s3">
								<select>
									<option value="" disabled selected>Choose speciality</option>
									<option value="1">sp1</option>
									<option value="2">sp2</option>
									<option value="3">sp3</option>
								</select>
							</div>
						</td>
						<td></td>
						<td><a href="edit.jsp" class="waves-effect waves-light btn">registre</a></td>
						<td><a href="list.jsp" class="waves-effect waves-light btn">show
						</a></td>


					</tr>
					<tr>
						<td>Faculty of Philology</td>
						<td>
							<div class="input-field col s3">
								<select>
									<option value="" disabled selected>Choose speciality</option>
									<option value="1">sp1</option>
									<option value="2">sp2</option>
									<option value="3">sp3</option>
								</select>
							</div>
						</td>
						<td></td>
						<td><a href="edit.jsp" class="waves-effect waves-light btn">registre</a></td>
						<td><a href="list.jsp" class="waves-effect waves-light btn">show
						</a></td>
					</tr>
					<tr>
						<td>Faculty of Economics and Management</td>
						<td>
							<div class="input-field col s3">
								<select>
									<option value="" disabled selected>Choose speciality</option>
									<option value="1">sp1</option>
									<option value="2">sp2</option>
									<option value="3">sp3</option>
								</select>
							</div>
						</td>
						<td></td>
						<td><a href="edit.jsp" class="waves-effect waves-light btn">registre</a></td>
						<td><a href="list.jsp" class="waves-effect waves-light btn">show
						</a></td>
					</tr>
					<tr>
						<td>Faculty of Mathematics and Informatics</td>
						<td>
							<div class="input-field col s3">
								<select>
									<option value="" disabled selected>Choose speciality</option>
									<option value="1">sp1</option>
									<option value="2">sp2</option>
									<option value="3">sp3</option>
								</select>
							</div>
						</td>
						<td></td>
						<td><a href="edit.jsp" class="waves-effect waves-light btn">registre</a></td>
						<td><a href="list.jsp" class="waves-effect waves-light btn">show
						</a></td>
					</tr>
					<tr>
						<td>Faculty of Law</td>
						<td>
							<div class="input-field col s3">
								<select>
									<option value="" disabled selected>Choose speciality</option>
									<option value="1">sp1</option>
									<option value="2">sp2</option>
									<option value="3">sp3</option>
								</select>
							</div>
						</td>
						<td></td>
						<td><a href="edit.jsp" class="waves-effect waves-light btn">registre</a></td>
						<td><a href="list.jsp" class="waves-effect waves-light btn">show
						</a></td>
					</tr>
				</tbody>
			</table>
</t:wrapper>
