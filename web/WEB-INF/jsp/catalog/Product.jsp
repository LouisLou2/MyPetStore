<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
	<a href="${pageContext.request.contextPath}/shop/view/category?categoryId=${requestScope.product.categoryId}">Return to ${requestScope.product.categoryId}</a>
</div>

<div id="Catalog">

<h2>${requestScope.product.name}</h2>

<table id="productTable">
	<tr>
		<th>Item ID</th>
		<th>Product ID</th>
		<th>Description</th>
		<th>List Price</th>
		<th>&nbsp;</th>
	</tr>
	<c:forEach var="item" items="${requestScope.itemList}">
		<tr>
			<td>
				<a href="${pageContext.request.contextPath}/shop/view/item?itemId=${item.itemId}">${item.itemId}</a>
			</td>
			<td>
				${item.product.productId}
			</td>
			<td>
				${item.attribute1}
				${item.attribute2}
				${item.attribute3}
			    ${item.attribute4}
				${item.attribute5}
				${requestScope.product.name}
			</td>
			<td>
				<fmt:formatNumber value="${item.listPrice}"
				pattern="$#,##0.00" /></td>
			<td>
				<a class="Button" href="${pageContext.request.contextPath}/shop/addcart/item?workingItemId=${item.itemId}">Add to Cart</a>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td>
		</td>
	</tr>
</table>

</div>
<%@ include file="../common/paginationRow.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/pagination.js"></script>
<script>
	let arequestUrl="${pageContext.request.contextPath}/rest/shop/view/product";
	preparedParams={
		"productId":"${requestScope.product.productId}"
	};
	initPaginationRow(arequestUrl,preparedParams,"productTable","updateProductList",${requestScope.totalPage},1);
</script>
<%@ include file="../common/IncludeBottom.jsp"%>





