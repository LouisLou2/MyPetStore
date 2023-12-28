<%@include file="../common/IncludeTop.jsp"%>
<div class="container-fluid">
    <div class="row">
        <div class="col">
            <div id="row">
                <div id="Sidebar">
                    <!--左侧导航栏-->
                    <div id="SidebarContent">
                        <a href="${pageContext.request.contextPath}/shop/view/category?categoryId=FISH"><img src="images/fish_icon.gif" /></a>
                        <br/> Saltwater, Freshwater <br/>
                        <a href="${pageContext.request.contextPath}/shop/view/category?categoryId=DOGS"><img src="images/dogs_icon.gif" /></a>
                        <br /> Various Breeds <br />
                        <a href="${pageContext.request.contextPath}/shop/view/category?categoryId=CATS"><img src="images/cats_icon.gif" /></a>
                        <br /> Various Breeds, Exotic Varieties <br />
                        <a href="${pageContext.request.contextPath}/shop/view/category?categoryId=REPTILES"><img src="images/reptiles_icon.gif" /></a>
                        <br /> Lizards, Turtles, Snakes <br />
                        <a href="${pageContext.request.contextPath}/shop/view/category?categoryId=BIRDS"><img src="images/birds_icon.gif" /></a>
                        <br /> Exotic Varieties
                    </div>
                </div>
            </div>
        </div>
        <div class="col-6">
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="${pageContext.request.contextPath}/images/carousel_pics/discount.jpg" class="d-block w-100" alt="discount.jpg">
                    </div>
                    <div class="carousel-item">
                        <img src="${pageContext.request.contextPath}/images/carousel_pics/happiness.jpg" class="d-block w-100" alt="happiness.jpg">
                    </div>
                    <div class="carousel-item">
                        <img src="${pageContext.request.contextPath}/images/carousel_pics/petchoy.jpg" class="d-block w-100" alt="petchoy.jpg">
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-target="#carouselExampleIndicators" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-target="#carouselExampleIndicators" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </button>
            </div>
        </div>
        <div class="col"></div>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col"></div>
            <div class="col-4">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
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
    