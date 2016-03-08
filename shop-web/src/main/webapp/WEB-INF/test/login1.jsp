<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
	<script type="text/javascript">
		$(document).ready(function(){
			$('login_submit').click(function() {
				var userName = userName;
				var password = password;
				$.ajax({
					type: "post",
					url: "reset_login",
					dataType: "json",
					data: {"userName": userName, "password": password},
					success: function (data) {
					/**
					 *跳转到主界面
					 */
						if(data.result == "success"){
							//jcl.go("");
							/*登陆成功*/
						}
						else if (data.result == "error") {
							alert("用户名或密码错误");
						}
						else if (data.result == "null") {
							alert("用户名或者密码为空");
						}
					}
				});
			});
		});
	</script>
<title>Login Page</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>
<body onload='document.loginForm.username.focus();'>

	<h1>Spring Security Login Form (Remember Me Example)</h1>

	<div id="login-box">

		<h3>Login with Username and Password</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm'
			action="<c:url value='/login_check' />"
			method='POST'>

			<table>
				<tr>
					<td>User:</td>
					<td><input type='text' name='username'></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
				</tr>

				<!-- if this is login for update, ignore remember me check -->
				<c:if test="${empty loginUpdate}">
					<tr>
						<td></td>
						<td>Remember Me: <input type="checkbox" name="remember-me" /></td>
					</tr>
				</c:if>

				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit" /></td>
				</tr>

			</table>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form>

	</div>

</body>
</html>