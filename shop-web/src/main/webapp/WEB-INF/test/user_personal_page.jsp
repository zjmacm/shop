<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>卖客</title>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="/js/user_pc/tool/jquery-1.11.1.min.js" type="text/javascript"></script>

    <link rel="stylesheet" href="/css/user_pc/public/global.css" />
    <link rel="stylesheet" href="/css/user_pc/public/header.css" />
    <link rel="stylesheet" href="/css/user_pc/public/footer.css" />
    <link rel="stylesheet" href="/css/user_pc/public/middle.css" />
    <link rel="stylesheet" href="/css/user_pc/public/item.css" />
    <link rel="stylesheet" href="/css/user_pc/public/list-area.css" />
    <link rel="stylesheet" href="/css/user_pc/public/left-bar.css" />
    <link rel="stylesheet" href="/css/user_pc/public/right-bar.css" />
    <link rel="stylesheet" href="/css/user_pc/public/font.css" />

    <link rel="stylesheet" href="/css/user_pc/public/page-select.css" />

    <link rel="stylesheet" href="/css/user_pc/user_personal_page/user_personal_page_item.css" />
    <link rel="stylesheet" href="/css/user_pc/user_personal_page/user_personal_page_middle.css" />

    <link rel="stylesheet" href="/css/user_pc/user_persoanal_page/">
    <link rel="stylesheet" href="/css/user_pc/user_persoanal_page/">
    <link rel="stylesheet" href="/css/user_pc/user_persoanal_page/">
    <link rel="stylesheet" href="/css/user_pc/user_persoanal_page/">

</head>
<body>
<div id="area">
    <div class="hide-head">
        <div class="search" role="in-hide-head">
            <input type="text" name="seasunrch-text" placeholder="搜搜有惊喜" />
            <input type="submit" value=""/>
        </div>
    </div>
    <div id="header">
    	<div class="header-logo"><img src="/images/user_pc/public/header-logo.png" id="header-logo"/>
    	</div>
    	<div class="header-content">
    		<div class="header-row1">
        		<div class="search" role="in-header">
        			<input type="text" name="seasunrch-text" placeholder="搜搜有惊喜" />
        			<input type="submit" value=""/>
        		</div>
    			<div class="search-right">
    				<a class="login" href="">登录</a>
    				<a class="login" href="">注册</a>
    			</div>
    		</div>
    		<div class="header-row2">
                <div class="search-below">
            		<a>热门推荐 :</a>
            		<a href="">教材</a>
            		<a href="">耳机</a>
                </div>
    		</div>
    		<div class="header-row3">
    			<div class="select-bar">
    				<a href="/">首页</a>
                    <a href="">二手商城</a>
                    <a href="/user_personal_page">个人主页</a>
    			</div>
    			<hr id="header-line"/>
    		</div>
    	</div>
    </div>
    <div class="container">
        <div class="middle">
            <div class="left-aside">
                <div id="left-bar">
                    <a class="bar-head"><span>商品分类</span></a>
                    <a class="bar-list" href=""><img src="/images/user_pc/public/lb_1.png"/><span>所有商品</span></a>
                    <a class="bar-list" href=""><img src="/images/user_pc/public/lb_2.png"/><span>代步工具</span></a>
                    <a class="bar-list" href=""><img src="/images/user_pc/public/lb_3.png"/><span>电脑</span></a>
                    <a class="bar-list" href=""><img src="/images/user_pc/public/lb_4.png"/><span>手机、数码</span></a>
                    <a class="bar-list" href=""><img src="/images/user_pc/public/lb_5.png"/><span>数码配件</span></a>
                    <a class="bar-list" href=""><img src="/images/user_pc/public/lb_6.png"/><span>图书</span></a>
                    <a class="bar-list" href=""><img src="/images/user_pc/public/lb_7.png"/><span>服装、鞋靴</span></a>
                    <a class="bar-list" href=""><img src="/images/user_pc/public/lb_8.png"/><span>个护化妆</span></a>
                    <a class="bar-list" href=""><img src="/images/user_pc/public/lb_9.png"/><span>运动健身</span></a>
                    <a class="bar-list" href=""><img src="/images/user_pc/public/lb_10.png"/><span>生活用品</span></a>
                    <a class="bar-list" href=""><img src="/images/user_pc/public/lb_11.png"/><span>娱乐</span></a>
                    <a class="bar-list" href=""><img src="/images/user_pc/public/lb_12.png"/><span>食品</span></a>
                    <a class="bar-list" href=""><img src="/images/user_pc/public/lb_13.png"/><span>其他</span></a>
                </div>
                <div id="clear-list">
                    <a class="list-entry"><span>1</span></a>
                    <a class="list-entry"><span>2</span></a>
                    <a class="list-entry"><span>3</span></a>
                </div>
            </div>
            <div class="content">
                <div class="center-content">
                    <div class="u-p-row1">
                        <div class="u-p-r1-portrait">
                            <img src="/images/user_pc/public/item_img1.png" />
                        </div>
                        <div class="u-p-r1-info">
                            <div class="u-p-r1-info-row1">
                                <span class="u-p-r1-info-row1-n">XXXXX</span>
                                <span class="u-p-r1-info-row1-x">(学号姓名认证)</span>
                            </div>
                            <div class="u-p-r1-info-row2">
                                <img src="/images/user_pc/public/position.png"/>
                                <span class="u-p-r1-info-row2-c">大连理工大学</span>
                                <span class="u-p-r1-info-row2-s">软件学院</span>
                            </div>
                        </div>
                    </div>
                    <div class="u-p-row2">
                        <div class="panel" id="u-p-r2-p1">
                            <a class="panel1-text">信 息</a>
                        </div>
                        <div class="panel" id="u-p-r2-p2">
                            <a class="panel2-text">我的订单</a>
                        </div>
                        <div class="panel" id="u-p-r2-p3">
                            <a class="panel3-text">收藏夹</a>
                        </div>
                        <div class="panel" id="u-p-r2-p4">
                            <a class="panel4-text">购物车</a>
                        </div>
                    </div>
                    <div class="u-p-row3">
                        <iframe src="/user_info" scrolling="no" id="tab1"></iframe>
                        <iframe src="/user_bought" scrolling="no" id="tab2"></iframe>
                        <iframe src="/user_favorites" scrolling="no" id="tab3"></iframe>
                        <iframe src="/user_shoppingcart" scrolling="no" id="tab4"></iframe>
                    </div>
                </div>
                <div class="right-aside">
                    <div id="tdcode">
                        <img src="/images/user_pc/public/2dcode.png" />
                    </div>
                    <div id="notice">
                        <span>公告</span>
                        <div class="notice-text">lalala</div>
                    </div>
                </div>
            </div>
        </div>
        <div id="footer">
            <span>鄂ICP备14003265号  Powered by XXXX</span>
            <br/>
        			<span>
        			<a href="">意见反馈</a>
        			<a href="">联系我们</a>
        			<a href="">加入我们</a>
        			</span>
        </div>
    </div>
</div>
<script src="/js/user_pc/tool/sea.js"></script>
<script>
    seajs.config({
        base: "/js/user_pc/public/",
        alias: {
            "jquery": "/js/user_pc/tool/jquery-1.11.1.min.js"
        }
    });
    seajs.use("main");
</script>
</body>
</html>