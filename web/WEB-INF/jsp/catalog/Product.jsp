<%@ include file="../common/IncludeTop.jsp"%>
<%--<h2>${requestScope.product.name}</h2>--%>
<div class="container">
	<div class="row">
		<div class="col"></div>
		<div class="col-8">
			<div class="card mb-3">
				<div class="row no-gutters">
					<div class="col-md-4">
						<img src='${pageContext.request.contextPath}${requestScope.product.picture}' class="card-img" alt="${requestScope.product.name}">
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<h5 class="card-title">${requestScope.product.name}</h5>
							<p class="card-text">${requestScope.product.description}</p>
							<p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
						</div>
					</div>
				</div>
			</div>
			<table id="productTable" class="table">
				<thead class="thead-light">
				<tr>
					<th scope="col">Item Id</th>
					<th scope="col">Product Id</th>
					<th scope="col">Detail</th>
					<th scope="col">Price</th>
				</tr>
				</thead>
				<tbody>
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
							<fmt:formatNumber value="${item.listPrice}" pattern="$#,##0.00" /></td>
						<td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="col"></div>
	</div>
</div>
<%@ include file="../common/paginationRow.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/pagination.js"></script>
<script>
	let arequestUrl="${pageContext.request.contextPath}/rest/shop/view/product";
	preparedParams={
		"productId":"${requestScope.product.productId}"
	};
	initPaginationRow(arequestUrl,"productTable","updateProductList",${requestScope.totalPage},1,preparedParams);
</script>
<%@ include file="../common/IncludeBottom.jsp"%>





