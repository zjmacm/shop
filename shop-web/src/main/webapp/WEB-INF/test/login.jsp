<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"  scope="page"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卖客用户登陆</title>
</head>
<body>
<p><div id="calculate" style="color:red; position:relative;display:block; " >显示</div></p>
<p><div id="register" style="color:red; position:relative; display:block;" >登录</div></p>
<div id="bg">
<img id='closeBg' src='${ctx}/images/user_pc/register/20080528160825246.png' width="40px" height="40px" />
</div>
<div id="coverBox">


<div id="body">
	<div id="bodyHead" >
	<img class="clipImg" src="${ctx}/images/user_pc/register/register.png"   />
	</div>
	<div id="bodyBottom">
	<form id="registerForm" >
		<div class="emptyInputLogin"></div>
		<input id="username " name="username"  class="inputBox required" type="text" placeholder="用户名" autocomplete=off  />
		<div class="emptyInputLogin state1"></div>
		<input id="password" name="password" class="inputBox required" type="password " placeholder="密码" autocomplete=off  onblur="dataHanding(password)
"/>
		<div class="emptyInputLogin state1"></div>
		<table class="validateBox">
		  <tr>
		     <td class="tableValidate vali">
		      验证码  
			 </td>
			 <td class="tableValidate">
		      <input id="validate" type="text"  />
			 </td>
			 <td class="tableValidate">
			 <img src="###" />
			 </td>
		</table>
		<div class="emptyInputLogin state1"></div>
		<table class="validateBox">
		  <tr>
		     <td class="tablePassword" >
		      <input id="remember" type="checkbox" />
		      <a class="validateBoxA">记住密码</a>
			 </td>
			 <td class="tablePassword" >
			 <a class="validateBoxA fr">忘记密码</a>
			 </td>
		</table>
		<div class="emptyInputSmall"></div>
	    <input type="button" value="登录" class="clickBox" />
		<div class="emptyInputSmall"></div>
		<a class="clickButton" href="/register">注册</a>
		
		<p><a class="validateBoxA fr">其他方式登录</a></p>
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
