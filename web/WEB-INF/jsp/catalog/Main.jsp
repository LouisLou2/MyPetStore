<%@include file="../common/IncludeTop.jsp"%>

<div id="Main">
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

    <div id="inform" style="display: none">sssssssssss</div>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/mouseEvent.js"></script>

    <div id="MainImage">
        <div id="MainImageContent">
            <!--中间显示栏-->
            <map name="estoremap">
                <area alt="BIRDS" coords="72,2,280,250" href="${pageContext.request.contextPath}/shop/view/category?categoryId=BIRDS" shape="rect"/>
                <area alt="FISH" coords="2,180,72,250" href="${pageContext.request.contextPath}/shop/view/category?categoryId=FISH" shape="rect"/>
                <area alt="DOGS" coords="60,250,130,320" href="${pageContext.request.contextPath}/shop/view/category?categoryId=DOGS" shape="rect"/>
                <area alt="REPTILES" coords="140,270,210,340" href="${pageContext.request.contextPath}/shop/view/category?categoryId=REPTILES" shape="rect"/>
                <area alt="CATS" coords="225,240,295,310" href="${pageContext.request.contextPath}/shop/view/category?categoryId=CATS" shape="rect"/>
                <area alt="BIRDS" coords="280,180,350,250" href="${pageContext.request.contextPath}/shop/view/category?categoryId=BIRDS" shape="rect"/>
            </map>
            <img height="355" src="${pageContext.request.contextPath}/images/splash.gif" align="middle" usemap="#estoremap" width="350" />
        </div>
    </div>
    <div id="Separator">&nbsp;</div>
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