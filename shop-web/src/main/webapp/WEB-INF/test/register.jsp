<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"  scope="page"/>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卖客用户买家注册</title>
</head>
<body>
<!--<p><div id="calculate" style="color:red; position:relative;display:block; " >显示</div></p>
--><p><div id="register" style="color:red; position:relative; display:block;" >注册</div></p>
<div id="bg">
<img id='closeBg' src='${ctx}/images/register/20080528160825246.png'  />
</div>
<div id="coverBox">


<div id="body">
	<div id="bodyHead" >
<!--	<img src="../images/register/注册修改版.png"   style="position:absolute; clip:rect(0px,360px,150px,0px);"  />	-->
	</div>
	<div id="bodyBottom">
	<form id="registerForm" action="register.php">
		<div class="emptyInput"></div>
		<input type="text" value="昵称"  id="name"class="inputBox" />
		<div class="emptyInput"></div>
		<input type="text" value="邮箱" class="inputBox" />
		<div class="emptyInput"></div>
		<input type="text" value="密码" class="inputBox" />
		<div class="emptyInput"></div>
		<input type="text" value="确认密码" class="inputBox" />
		<div class="emptyButton"></div>
		<input type="button" value="注册" class="clickBox" />
		<p><a class="clickButton" href="/login">登录</a></p>
	</form>
	</div>
</div>



</div>
<script src="${ctx}/js/user_pc/tool/sea.js"></script>
<script>
    seajs.config({
        base: "${ctx}/js/user_pc/register&login/",
        alias: {
            "jquery": "${ctx}/js/user_pc/tool/jquery-1.11.1.min.js"
        }
    });

    seajs.use("main");
</script>
</body>
</html>
