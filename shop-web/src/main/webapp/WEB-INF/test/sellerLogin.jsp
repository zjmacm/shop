<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"  scope="page"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>卖家管理中心</title>
    <link rel="stylesheet" type="text/css" href="/css/ext/ext-theme-classic-all.css" />
    <link rel="stylesheet" type="text/css" href="/css/seller/login.css" />

    <!-- GC -->
    <!-- LIBS -->
    <!-- ENDLIBS -->
    <script type="text/javascript" src="/js/ext/bootstrap.js"></script>



    <script type="text/javascript" src="/seller/app/view/Login.js"></script>
</head>

<body>
<script>
    Ext.onReady(function() {
        var loginWin = Ext.create('Seller.view.Login');
        loginWin.show();
    }
    );
</script>
</body>

</html>
