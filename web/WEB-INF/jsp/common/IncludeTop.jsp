<%--
  Created by IntelliJ IDEA.
  User: Summer
  Date: 2018/12/10
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link rel="StyleSheet" href="${pageContext.request.contextPath }/css/jpetstore.css" type="text/css" media="screen" />
    <link rel="StyleSheet" href="${pageContext.request.contextPath }/css/searchProduct.css" type="text/css" media="screen" />
    <link rel="StyleSheet" href="${pageContext.request.contextPath }/css/cartChange.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/mouseEventInform.css" type="text/css" media="screen" />
<%--    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="css/demo.css">--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/axios.min.js"></script>
    <script src="https://cdn.bootcss.com/qs/6.7.0/qs.min.js"></script>
    <meta name="generator" content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org" />
    <title>MyPetStore</title>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
    <meta http-equiv="Cache-Control" content="max-age=0" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="Pragma" content="no-cache" />

    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.3.1.js"></script>
</head>

<body>
<span id="contextPath" style="display: none">${pageContext.request.contextPath}</span>
<div id="Header">

    <div id="Logo">
        <div id="LogoContent">
            <a href="${pageContext.request.contextPath}/main"><img src="${pageContext.request.contextPath}/images/logo-topbar.gif" /></a>
        </div>
    </div>

    <div id="Menu">
        <div id="MenuContent">
            <!--购物车-->
            <a href="${pageContext.request.contextPath}/shop/view/cart"><img align="middle" name="img_cart" src="${pageContext.request.contextPath}/images/cart.gif" /></a>
            <img align="middle" src="${pageContext.request.contextPath}/images/separator.gif" />
            <c:if test="${sessionScope.account == null}">
                <a href="${pageContext.request.contextPath}/page/account/signin">Sign In</a>
                <img align="middle" src="${pageContext.request.contextPath}/images/separator.gif" />
                <a href="${pageContext.request.contextPath}/page/account/register">Sign Up</a>
            </c:if>
            <c:if test="${sessionScope.account != null}">
                <a href="${pageContext.request.contextPath}/account/signoff">Sign Out</a>
                <!---signOff-->
            </c:if>

            <!--分隔符-->
            <c:if test="${sessionScope.account != null}">
                <img align="middle" src="${pageContext.request.contextPath}/images/separator.gif" />
                <a href="${pageContext.request.contextPath}/editAccount">My Account</a>
            </c:if>
            <img align="middle" src="${pageContext.request.contextPath}/images/separator.gif" />
            <!--暂未提供-->
            <a href="../help.html">?</a>
        </div>
    </div>

    <div id="Search">
        <div id="SearchContent">
            <!--搜索栏目-->
            <form action="${pageContext.request.contextPath}/shop/search/product" method="post">
                <input type="text" id="keyword" name="keyword" size="14"/>
                <div class="auto hidden" id="auto">
                    <div class="auto_out">1</div>
                    <div class="auto_out">2</div>
                </div>
                <input type="submit" name="searchProducts" value="Search" />
                <script src="${pageContext.request.contextPath}/js/searchProduct.js"></script>
            </form>
        </div>
    </div>
    <div id="QuickLinks">
        <a href="${pageContext.request.contextPath}/shop/view/category?categoryId=FISH"><img src="${pageContext.request.contextPath}/images/sm_fish.gif" /></a>
        <img src="${pageContext.request.contextPath}/images/separator.gif" />
        <a href="${pageContext.request.contextPath}/shop/view/category?categoryId=DOGS"><img src="${pageContext.request.contextPath}/images/sm_dogs.gif" /></a>
        <img src="${pageContext.request.contextPath}/images/separator.gif" />
        <a href="${pageContext.request.contextPath}/shop/view/category?categoryId=REPTILES"><img src="${pageContext.request.contextPath}/images/sm_reptiles.gif" /></a>
        <img src="${pageContext.request.contextPath}/images/separator.gif" />
        <a href="${pageContext.request.contextPath}/shop/view/category?categoryId=CATS"><img src="${pageContext.request.contextPath}/images/sm_cats.gif" /></a>
        <img src="${pageContext.request.contextPath}/images/separator.gif" />
        <a href="${pageContext.request.contextPath}/shop/view/category?categoryId=BIRDS"><img src="${pageContext.request.contextPath}/images/sm_birds.gif" /></a>
    </div>
</div>

<div id="Content">
