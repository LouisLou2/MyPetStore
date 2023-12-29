<%@ include file="../common/IncludeTop.jsp"%>

<div class="container">
	<div class="row">
		<div class="col"></div>
		<div class="col-9">
			<h2>${requestScope.category.name}</h2>
			<hr>
			<ul id="categoryTable" class="list-unstyled">
				<c:forEach var="product" items="${requestScope.productList}">
					<li class="media">
						<img class="mr-3" src="${pageContext.request.contextPath}${product.picture}" alt="image">
						<div class="media-body">
							<h5 class="mt-0 mb-1"><a href="${pageContext.request.contextPath}/shop/view/product?productId=${product.productId}">${product.productId}</a></h5>
							<strong>${product.name}</strong>
							<p style="padding: 0;">${product.description}</p>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="col"></div>
	</div>
</div>
<%@ include file="../common/paginationRow.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/pagination.js"></script>
<script>
	let arequestUrl="${pageContext.request.contextPath }/rest/shop/view/category";
	preparedParams={
		"categoryId":"${requestScope.category.categoryId}"
	};
	initPaginationRow(arequestUrl,"categoryTable","updateCategoryList",${requestScope.totalPage},1,preparedParams);
</script>
<%@ include file="../common/IncludeBottom.jsp"%>

