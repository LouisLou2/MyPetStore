<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
	<a href="${pageContext.request.contextPath}/main">Return to Main Menu</a>
</div>

<div id="Catalog">

<table>
	<tr>
		<th>&nbsp;</th>
		<th>Product ID</th>
		<th>Name</th>
	</tr>
	<c:forEach var="product" items="${sessionScope.productList}">
		<tr>
			<td>
				<a href="${pageContext.request.contextPath}/shop/view/product?productId=${product.productId}">${product.description}</a>
			</td>
			<td><b>
				<a href="${pageContext.request.contextPath}/shop/view/product?productId=${product.productId}">${product.productId}</a>
			</b></td>
			<td>${product.name}</td>
		</tr>
	</c:forEach>
	<tr>
		<td></td>
	</tr>

</table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>




