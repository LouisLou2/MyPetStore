<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
	<a href="${pageContext.request.contextPath}/main">Return to Main Menu</a>
</div>
<div class="container">
	<div class="row">
		<div class="col"></div>
		<div class="col-9">
			<h2>${requestScope.category.name}</h2>
			<hr>
			<ul id="searchedProductTable" class="list-unstyled">
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
<%--<div class="container">--%>
<%--	<div class="container text-center">--%>
<%--		<div class="row">--%>
<%--			<div class="col"></div>--%>
<%--			<div class="col-6">--%>
<%--				<table id="PaymentDetailsTable" class="simple-table" style="width: 100%">--%>
<%--					<thead>--%>
<%--						<tr>--%>
<%--							<th>Product ID</th>--%>
<%--							<th>Name</th>--%>
<%--							<th>Description</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
<%--					<tbody>--%>
<%--					<c:forEach var="product" items="${requestScope.productList}">--%>
<%--						<tr>--%>
<%--							<td><b>--%>
<%--								<a href="${pageContext.request.contextPath}/shop/view/product?productId=${product.productId}">${product.productId}</a>--%>
<%--							</b></td>--%>
<%--							<td>${product.name}</td>--%>
<%--							<td>--%>
<%--								<a href="${pageContext.request.contextPath}/shop/view/product?productId=${product.productId}">${product.description}</a>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</c:forEach>--%>
<%--					</tbody>--%>
<%--				</table>--%>
<%--			</div>--%>
<%--			<div class="col"></div>--%>
<%--	</div>--%>
<%--</div>--%>

<%@ include file="../common/IncludeBottom.jsp"%>




