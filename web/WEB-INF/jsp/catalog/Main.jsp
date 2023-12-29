<%@include file="../common/IncludeTop.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/carousel_img.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/showInfo.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/loading.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/boxes.css">
<script src="${pageContext.request.contextPath }/js/carousel_img_control.js" defer></script>
<script src="${pageContext.request.contextPath }/js/floating_box.js" defer></script>
<%--<script src="${pageContext.request.contextPath }/js/showAtHomePage.js" defer></script>--%>

<div class="container-fluid">
    <div class="row">
        <div class="col">
        </div>
        <div class="col-8">
            <div class="banner">
                <div class="warp">
                    <div class="item"><img src="${pageContext.request.contextPath}/images/carousel_pics/discount.jpg" class="d-block w-100" alt="discount.jpg"></div>
                    <div class="item"><img src="${pageContext.request.contextPath}/images/carousel_pics/happiness.jpg" class="d-block w-100" alt="happiness.jpg"></div>
                    <div class="item"><img src="${pageContext.request.contextPath}/images/carousel_pics/petchoy.jpg" class="d-block w-100" alt="petchoy.jpg"></div>
                </div>
                <div class="div1">
                    <
                </div>
                <div class="div2 ">
                    >
                </div>
                <!-- 小圆点 -->
                <div class="pagination">
                    <div id="pagination-item0"></div>
                    <div id="pagination-item1"></div>
                    <div id="pagination-item2"></div>
                </div>
            </div>
            <div class="itemContainer">
                <div class="listProduct">
                    <c:forEach var="item" items="${requestScope.itemList}">
                        <a href="${pageContext.request.contextPath}/shop/view/item?itemId=${item.itemId}" class="homepageItem">
                            <img class="pic" src="${pageContext.request.contextPath}${item.picture}">
                            <h2 class="id">${item.itemId}</h2>
                            <h2 class="name">${item.productName}</h2>
                            <div class="price">$${item.listPrice}</div>
                        </a>
                    </c:forEach>
                </div>
            </div>
            <div class="floatingBox" id="floatingBox" style="display: none">
                <div class="loader" id="loader"></div>
                <div class="row no-gutters" id="boxContent" style="display: none">
                    <div class="col-md-4">
                        <img src="" id="pic" class="card-img" alt="...">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title" id="id" style="text-align: left;"></h5>
                            <p class="card-text m-1" id="name" style="text-align: left;"></p>
                            <p class="card-text m-1" id="description" style="text-align: left;"></p>
                            <h3 class="card-text ml-0" id="price" style="text-align: left;"><span class="badge badge-success m-2"></span></h3>
                            <p class="card-text" style="text-align: left;">
                                <strong id="quantity">${requestScope.item.quantity}</strong> In Stock
                            </p>
                            <p class="card-text" style="text-align: left;"><small class="text-muted" id="attr"></small></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col"></div>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col"></div>
            <div class="col-4">
                <div class="alert alert-success alert-dismissible fade show" style="margin-top: 20px" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4  class="alert-heading">Great Deals Now Available!</h4>
                    <p>Aww yeah, it's not real purchasing.</p>
                    <hr>
                    <p class="mb-0">Powered By CSU</p>
                </div>
            </div>
        </div>
    </div>
    <%@include file="../common/IncludeBottom.jsp"%>
</div>
    