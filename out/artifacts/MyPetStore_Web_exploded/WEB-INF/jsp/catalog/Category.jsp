<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
    <a href="${pageContext.request.contextPath }/main">Return to Main Menu</a>
</div>

<div id="Catalog">

<h2>${sessionScope.category.name}</h2>

<table id="categoryTable">
	<tr>
		<th>Product ID</th>
		<th>Name</th>
	</tr>
	<c:forEach var="product" items="${sessionScope.productList}">
		<tr>
			<td>
			     <a href="${pageContext.request.contextPath}/shop/view/product?productId=${product.productId}">${product.productId}</a>
			</td>
			<td>
				${product.name}
			</td>
		</tr>
	</c:forEach>
</table>
	
</div>
<%@ include file="../common/paginationRow.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/pagination.js"></script>
<script>
	let arequestUrl="${pageContext.request.contextPath }/rest/shop/view/category";
	preparedParams={
		"categoryId":"${sessionScope.category.categoryId}"
	};
	initPaginationRow(arequestUrl,preparedParams,"categoryTable","updateCatagoryList",${requestScope.totalPage},1);
</script>
<%@ include file="../common/IncludeBottom.jsp"%>

