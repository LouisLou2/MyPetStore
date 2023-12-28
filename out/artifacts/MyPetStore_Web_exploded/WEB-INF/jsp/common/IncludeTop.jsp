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
    <script>
        var contextPath = '${pageContext.request.contextPath }';
    </script>
    <script src="${pageContext.request.contextPath }/js/locationCollection.js"></script>
    <script src="${pageContext.request.contextPath }/js/signIn.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/thirdparty/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="${pageContext.request.contextPath }/js/thirdparty/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath }/js/thirdparty/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath }/js/thirdparty/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath }/js/TipAlertModal.js"></script>
    <script src="${pageContext.request.contextPath }/js/autocomplete.js"></script>
    <script src="${pageContext.request.contextPath }/js/generic_tools.js"></script>
    <link rel="StyleSheet" href="${pageContext.request.contextPath }/css/jpetstore.css" type="text/css" media="screen" />
    <link rel="StyleSheet" href="${pageContext.request.contextPath }/css/searchProduct.css" type="text/css" media="screen" />
    <link rel="StyleSheet" href="${pageContext.request.contextPath }/css/cartChange.css" type="text/css" media="screen" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/axios.min.js"></script>
    <script src="https://cdn.bootcss.com/qs/6.7.0/qs.min.js"></script>
    <meta name="generator" content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>MyPetStore</title>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
    <meta http-equiv="Cache-Control" content="max-age=0" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="Pragma" content="no-cache" />
    <style>
        .selector-for-some-widget {
            box-sizing: content-box;
        }
    </style>
</head>

<body>
<span id="contextPath" style="display: none">${pageContext.request.contextPath}</span>
<div id="Header">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/main">
            <img src="${pageContext.request.contextPath}/images/logo-topbar.gif" alt="">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
<%--                Home--%>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/main">Home <span class="sr-only">(current)</span></a>
                </li>
                <c:if test="${sessionScope.account == null}">
                    <li class="nav-item">
                        <a class="nav-link" onclick="navSignInOrUpClicked()" href="${pageContext.request.contextPath}/page/account/signin">Sign In</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" onclick="navSignInOrUpClicked()" href="${pageContext.request.contextPath}/page/account/register">Sign Up</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.account != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/account/signoff">Sign Out</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.account != null}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            My Account
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/editAccount">Information</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/shop/view/cart?username=${sessionScope.account.username}">Shopping Cart</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/shop/view/listorder?username=${sessionScope.account.username}">My Orders</a>
                        </div>
                    </li>
                </c:if>
                <li class="nav-item">
                    <a class="nav-link" href="#">Help</a>
                </li>
            </ul>
            <form class="form-inline my-0 my-lg-0" action="${pageContext.request.contextPath}/shop/search/product" method="post">
                <div class="col">
                    <input id="keyword" name="keyword" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <div class="row">
                        <div class="auto" id="auto" style="z-index: 9999">
                    </div>
                </div>
                </div>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit" name="searchProducts">Search</button>
                <script src="${pageContext.request.contextPath}/js/searchProduct.js"></script>
            </form>
        </div>
    </nav>
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
<script>
    function navSignInOrUpClicked(){
        localStorage.setItem('active','1');
        localStorage.setItem('originalLink',window.location.href);
    }
    let searchInput= document.getElementById('keyword');
    initAutocompleteList('auto');
    searchInput.addEventListener('input',()=>throttle(fill_autocomplete("auto",searchInput.value),302));
    document.addEventListener('click',()=>close_autocompleteList());
</script>
