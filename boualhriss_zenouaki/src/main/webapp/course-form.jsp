<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> Gestion des Formations </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Cours</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${course != null}">
					<form action="update" method="post">
				</c:if>
				
				<caption>
					<h2>
						<c:if test="${course != null}">
            			Edit User
            		</c:if>
						<c:if test="${course == null}">
            			Add New User
            		</c:if>
					</h2>
				</caption>

				<c:if test="${course != null}">
					<input type="hidden" name="id" value="<c:out value='${course.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>course code</label> <input type="text"
						value="<c:out value='${course.code}' />" class="form-control"
						name="code" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>course title</label> <input type="text"
						value="<c:out value='${course.title}' />" class="form-control"
						name="title">
				</fieldset>

				<fieldset class="form-group">
					<label>User Country</label> <input type="text"
						value="<c:out value='${user.country}' />" class="form-control"
						name="country">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>