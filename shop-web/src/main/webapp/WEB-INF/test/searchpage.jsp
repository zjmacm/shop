<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>卖客</title>
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
	<link rel="stylesheet" href="/css/user_pc/public/searchpage.css" />
	<link rel="stylesheet" href="/css/user_pc/public/page-select.css" />

	<link rel="stylesheet" href="/css/user_pc/searchpage/searchpage_middle.css" />
	<link rel="stylesheet" href="/css/user_pc/searchpage/searchpage_item.css" />
	<link rel="stylesheet" href="/css/user_pc/searchpage/searchpage_font.css" />
</head>
<body>
<div id="area">
	<div id="header">
        <div class="header-logo">
            <img src="/images/user_pc/public/header-logo.png" id="header-logo"/>
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
					<div class="search-top">
						<div class="search-by">
							<div class="search-by-tag">
								<p>搜索商品</p>
							</div>
							<div class="search-by-list">
								<div>
									<a href="">综合</a>
									<a href="">发布时间</a>
									<a href="">价格</a>
									<a href="">热度</a>
								</div>
							</div>
						</div>
					</div>
					<div class="search-content">
						<div class="item" role="area1-item">
							<div class="i-row0">
								<img src="./public/image/item_img1.png"/>
								<div class="school">
									<img src="./public/image/position.png"/>
									<div class="school-name">软件学院</div>
								</div>
								<div class="hide-row">
									<div class="release-time">
										<span>发布时间</span><span></span>
									</div>
									<div class="fresh">
										<span>新鲜度</span><span></span>
									</div>
								</div>
							</div>
							<div class="i-row1">
								<span class="i-name">创意花瓶</span>
								<span class="i-price">
									<span class="i-c">¥</span>
									<span class="i-num">48</span>
								</span>
							</div>
							<div class="i-row2">
								<span class="i-school">大连理工大学</span>
								<span class="i-id">学号姓名认证</span>
							</div>
						</div>
						<div class="item" role="area1-item">
							<div class="i-row0">
								<img src="./public/image/item_img1.png"/>
								<div class="school">
									<img src="./public/image/position.png"/>
									<div class="school-name">软件学院</div>
								</div>
								<div class="hide-row">
									<div class="release-time">
										<span>发布时间</span><span></span>
									</div>
									<div class="fresh">
										<span>新鲜度</span><span></span>
									</div>
								</div>
							</div>
							<div class="i-row1">
								<span class="i-name">创意花瓶</span>
								<span class="i-price">
									<span class="i-c">¥</span>
									<span class="i-num">48</span>
								</span>
							</div>
							<div class="i-row2">
								<span class="i-school">大连理工大学</span>
								<span class="i-id">学号姓名认证</span>
							</div>
						</div>
						<div class="item" role="area1-item">
							<div class="i-row0">
								<img src="./public/image/item_img1.png"/>
								<div class="school">
									<img src="./public/image/position.png"/>
									<div class="school-name">软件学院</div>
								</div>
								<div class="hide-row">
									<div class="release-time">
										<span>发布时间</span><span></span>
									</div>
									<div class="fresh">
										<span>新鲜度</span><span></span>
									</div>
								</div>
							</div>
							<div class="i-row1">
								<span class="i-name">创意花瓶</span>
								<span class="i-price">
									<span class="i-c">¥</span>
									<span class="i-num">48</span>
								</span>
							</div>
							<div class="i-row2">
								<span class="i-school">大连理工大学</span>
								<span class="i-id">学号姓名认证</span>
							</div>
						</div>
						<div class="item" role="area1-item">
							<div class="i-row0">
								<img src="./public/image/item_img1.png"/>
								<div class="school">
									<img src="./public/image/position.png"/>
									<div class="school-name">软件学院</div>
								</div>
								<div class="hide-row">
									<div class="release-time">
										<span>发布时间</span><span></span>
									</div>
									<div class="fresh">
										<span>新鲜度</span><span></span>
									</div>
								</div>
							</div>
							<div class="i-row1">
								<span class="i-name">创意花瓶</span>
								<span class="i-price">
									<span class="i-c">¥</span>
									<span class="i-num">48</span>
								</span>
							</div>
							<div class="i-row2">
								<span class="i-school">大连理工大学</span>
								<span class="i-id">学号姓名认证</span>
							</div>
						</div>
						<div class="item" role="area1-item">
							<div class="i-row0">
								<img src="./public/image/item_img1.png"/>
								<div class="school">
									<img src="./public/image/position.png"/>
									<div class="school-name">软件学院</div>
								</div>
								<div class="hide-row">
									<div class="release-time">
										<span>发布时间</span><span></span>
									</div>
									<div class="fresh">
										<span>新鲜度</span><span></span>
									</div>
								</div>
							</div>
							<div class="i-row1">
								<span class="i-name">创意花瓶</span>
								<span class="i-price">
									<span class="i-c">¥</span>
									<span class="i-num">48</span>
								</span>
							</div>
							<div class="i-row2">
								<span class="i-school">大连理工大学</span>
								<span class="i-id">学号姓名认证</span>
							</div>
						</div>
						<div class="item" role="area1-item">
							<div class="i-row0">
								<img src="./public/image/item_img1.png"/>
								<div class="school">
									<img src="./public/image/position.png"/>
									<div class="school-name">软件学院</div>
								</div>
								<div class="hide-row">
									<div class="release-time">
										<span>发布时间</span><span></span>
									</div>
									<div class="fresh">
										<span>新鲜度</span><span></span>
									</div>
								</div>
							</div>
							<div class="i-row1">
								<span class="i-name">创意花瓶</span>
								<span class="i-price">
									<span class="i-c">¥</span>
									<span class="i-num">48</span>
								</span>
							</div>
							<div class="i-row2">
								<span class="i-school">大连理工大学</span>
								<span class="i-id">学号姓名认证</span>
							</div>
						</div>
						<div class="item" role="area1-item">
							<div class="i-row0">
								<img src="./public/image/item_img1.png"/>
								<div class="school">
									<img src="./public/image/position.png"/>
									<div class="school-name">软件学院</div>
								</div>
								<div class="hide-row">
									<div class="release-time">
										<span>发布时间</span><span></span>
									</div>
									<div class="fresh">
										<span>新鲜度</span><span></span>
									</div>
								</div>
							</div>
							<div class="i-row1">
								<span class="i-name">创意花瓶</span>
								<span class="i-price">
									<span class="i-c">¥</span>
									<span class="i-num">48</span>
								</span>
							</div>
							<div class="i-row2">
								<span class="i-school">大连理工大学</span>
								<span class="i-id">学号姓名认证</span>
							</div>
						</div>
						<div class="item" role="area1-item">
							<div class="i-row0">
								<img src="./public/image/item_img1.png"/>
								<div class="school">
									<img src="./public/image/position.png"/>
									<div class="school-name">软件学院</div>
								</div>
								<div class="hide-row">
									<div class="release-time">
										<span>发布时间</span><span></span>
									</div>
									<div class="fresh">
										<span>新鲜度</span><span></span>
									</div>
								</div>
							</div>
							<div class="i-row1">
								<span class="i-name">创意花瓶</span>
								<span class="i-price">
									<span class="i-c">¥</span>
									<span class="i-num">48</span>
								</span>
							</div>
							<div class="i-row2">
								<span class="i-school">大连理工大学</span>
								<span class="i-id">学号姓名认证</span>
							</div>
						</div>
						<div class="item" role="area1-item">
							<div class="i-row0">
								<img src="./public/image/item_img1.png"/>
								<div class="school">
									<img src="./public/image/position.png"/>
									<div class="school-name">软件学院</div>
								</div>
								<div class="hide-row">
									<div class="release-time">
										<span>发布时间</span><span></span>
									</div>
									<div class="fresh">
										<span>新鲜度</span><span></span>
									</div>
								</div>
							</div>
							<div class="i-row1">
								<span class="i-name">创意花瓶</span>
								<span class="i-price">
									<span class="i-c">¥</span>
									<span class="i-num">48</span>
								</span>
							</div>
							<div class="i-row2">
								<span class="i-school">大连理工大学</span>
								<span class="i-id">学号姓名认证</span>
							</div>
						</div>
						<div class="item" role="area1-item">
							<div class="i-row0">
								<img src="./public/image/item_img1.png"/>
								<div class="school">
									<img src="./public/image/position.png"/>
									<div class="school-name">软件学院</div>
								</div>
								<div class="hide-row">
									<div class="release-time">
										<span>发布时间</span><span></span>
									</div>
									<div class="fresh">
										<span>新鲜度</span><span></span>
									</div>
								</div>
							</div>
							<div class="i-row1">
								<span class="i-name">创意花瓶</span>
								<span class="i-price">
									<span class="i-c">¥</span>
									<span class="i-num">48</span>
								</span>
							</div>
							<div class="i-row2">
								<span class="i-school">大连理工大学</span>
								<span class="i-id">学号姓名认证</span>
							</div>
						</div>
						<div class="item" role="area1-item">
							<div class="i-row0">
								<img src="./public/image/item_img1.png"/>
								<div class="school">
									<img src="./public/image/position.png"/>
									<div class="school-name">软件学院</div>
								</div>
								<div class="hide-row">
									<div class="release-time">
										<span>发布时间</span><span></span>
									</div>
									<div class="fresh">
										<span>新鲜度</span><span></span>
									</div>
								</div>
							</div>
							<div class="i-row1">
								<span class="i-name">创意花瓶</span>
								<span class="i-price">
									<span class="i-c">¥</span>
									<span class="i-num">48</span>
								</span>
							</div>
							<div class="i-row2">
								<span class="i-school">大连理工大学</span>
								<span class="i-id">学号姓名认证</span>
							</div>
						</div>
						<div class="item" role="area1-item">
							<div class="i-row0">
								<img src="./public/image/item_img1.png"/>
								<div class="school">
									<img src="./public/image/position.png"/>
									<div class="school-name">软件学院</div>
								</div>
								<div class="hide-row">
									<div class="release-time">
										<span>发布时间</span><span></span>
									</div>
									<div class="fresh">
										<span>新鲜度</span><span></span>
									</div>
								</div>
							</div>
							<div class="i-row1">
								<span class="i-name">创意花瓶</span>
								<span class="i-price">
									<span class="i-c">¥</span>
									<span class="i-num">48</span>
								</span>
							</div>
							<div class="i-row2">
								<span class="i-school">大连理工大学</span>
								<span class="i-id">学号姓名认证</span>
							</div>
						</div>
						<div class="item" role="area1-item">
							<div class="i-row0">
								<img src="./public/image/item_img1.png"/>
								<div class="school">
									<img src="./public/image/position.png"/>
									<div class="school-name">软件学院</div>
								</div>
								<div class="hide-row">
									<div class="release-time">
										<span>发布时间</span><span></span>
									</div>
									<div class="fresh">
										<span>新鲜度</span><span></span>
									</div>
								</div>
							</div>
							<div class="i-row1">
								<span class="i-name">创意花瓶</span>
								<span class="i-price">
									<span class="i-c">¥</span>
									<span class="i-num">48</span>
								</span>
							</div>
							<div class="i-row2">
								<span class="i-school">大连理工大学</span>
								<span class="i-id">学号姓名认证</span>
							</div>
						</div>
					</div>
					<div class="search-bottom">
						<div class="page-select">
							<div class="last-page block"><a href=""><上一页</a></div>
							<div class="page-num block">&nbsp<a href="">1</a>&nbsp</div>
							<div class="page-num block">&nbsp<a href="">2</a>&nbsp</div>
							<div class="page-num block">&nbsp<a href="">3</a>&nbsp</div>
							<span class="suspension">...</span>
							<div class="next-page block"><a href="">下一页></a></div>
							<div class="total-page">共<span>100</span>页</div>
							<div class="input-page">
								<span>去第
								<input type="text" name="page-num" value="1" />
								<a class="page-jump-btn block" href="">确定</a>
								页</span>
							</div>
						</div>
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
