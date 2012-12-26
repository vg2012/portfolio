<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login Page</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/jVal.css"
	rel="stylesheet" type="text/css" />	
</head>
<body onload='document.f.j_username.focus();'>
	<table align="center">
		<tr>
			<td>
				<fieldset>
					<legend>Portfolio Management</legend>
				</fieldset>
			</td>
		</tr>
	</table>

	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>

	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>

		<table align="center">
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="name">Login Id</label>
					</div>
				</td>
				<td>
					<div class="control-group">
						<input type="text" class="input-small" name="j_username"
							id="j_username"
							jVal="{valid:function (val) { if (val.length < 1) return 'User Name is required'; else return ''; }}" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="name">Password</label>
					</div>
				</td>
				<td>
					<div class="control-group">
						<input type="password" class="input-small" name="j_password"
							id="j_password"
							jVal="{valid:function (val) { if (val.length < 1) return 'Password is required'; else return ''; }}" />
					</div>
				</td>
			</tr>
			<tr><td>&nbsp;</td>
			<tr>
				<td colspan="2" align="center">
					<div class="control-group" align="center">
						<button class="btn btn-primary btn-small" name="submit"
							type="submit">Submit</button>
						<button class="btn btn-primary btn-small" name="reset"
							type="reset">Cancel</button>
					</div>
				</td>
			</tr>
		</table>

	</form>
</body>
</html>