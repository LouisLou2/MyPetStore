<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script>
        var contextPath = '${pageContext.request.contextPath}';
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/axios.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/thirdparty/qs.min.js"></script>
    <title>MyPetStore</title>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
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
    <ul class="nav justify-content-center">
        <li class="nav-item">
            <a class="nav-link active" href="${pageContext.request.contextPath}/shop/view/category?categoryId=FISH">🐟&nbsp;Fish</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/shop/view/category?categoryId=DOGS">🐶&nbsp;Dogs</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/shop/view/category?categoryId=REPTILES">🐍&nbsp;Reptiles</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/shop/view/category?categoryId=CATS">🐈&nbsp;Cats</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/shop/view/category?categoryId=BIRDS">🐟&nbsp;Birds</a>
        </li>
    </ul>
</div>
<script>
    function navSignInOrUpClicked(){
        localStorage.setItem('active','1');
        localStorage.setItem('originalLink',window.location.href);
    }
    let searchInput= document.getElementById('keyword');
    initAutocompleteList('auto');
    let throttleFunc=throttle(()=>fill_autocomplete("auto",searchInput.value),302);
    searchInput.addEventListener('input',throttleFunc);
    document.addEventListener('click',close_autocompleteList);
</script>
