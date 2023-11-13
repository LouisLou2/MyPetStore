<%@ include file="../common/IncludeTop.jsp"%>

<h2>My Orders</h2>

<table id="orderTable">
	<tr>
		<th>Order ID</th>
		<th>Date</th>
		<th>Total Price</th>
	</tr>

	<c:forEach var="order" items="${requestScope.orderList}">
		<tr>
			<td>
				<a href="${pageContext.request.contextPath}/shop/view/order?orderId=${order.orderId}">${order.orderId}</a>
			</td>
			<td>
				<fmt:formatDate value="${order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" />
			</td>
			<td>
				<fmt:formatNumber value="${order.totalPrice}" pattern="$#,##0.00" />
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="../common/paginationRow.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/pagination.js"></script>
<script>
	let arequestUrl="${pageContext.request.contextPath }/rest/shop/view/orderlist";
	initPaginationRow(arequestUrl,"orderTable","updateOrderList",${requestScope.totalPage},1);
</script>
<%@ include file="../common/IncludeBottom.jsp"%>
