<%@ include file="../common/IncludeTop.jsp"%>

<div class="container">
	<div class="row">
		<div class="col"></div>
		<div class="col-8">
			<h2>My Orders</h2>
			<hr>
			<div id="orderListArea">
				<c:forEach var="order" items="${requestScope.orderList}">
					<div class="card mb-3 container">
						<div class="row g-0">
							<div class="col-md-2 p-1">
								<img  src="${pageContext.request.contextPath}/images/icons/green-shopbag.png" class="img-fluid rounded-start" alt="order">
							</div>
							<div class="col-md-8">
								<div class="card-body p-2">
									<h3 class="card-title"><a name="orderId" href="${pageContext.request.contextPath}/shop/view/order?orderId=${order.orderId}">${order.orderId}</a></h3>
									<span class="badge badge-success m-2">Order Date</span>
									<h6 name="orderDate" class="card-subtitle mb-2 text-muted ml-2"><fmt:formatDate value="${order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" /></h6>
									<p class="text-start"><span class="badge badge-success m-2">Total Price</span>&nbsp;<strong name="totalPrice"><fmt:formatNumber value="${order.totalPrice}" pattern="$#,##0.00" /></strong> </p>
									<p><small class="text-muted mt-2 mb-0">We are preparing to ship your order.</small></p>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="col"></div>
	</div>
</div>
<%@ include file="../common/paginationRow.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/pagination.js"></script>
<script>
	let arequestUrl="${pageContext.request.contextPath }/rest/shop/view/orderlist";
	initPaginationRow(arequestUrl,"orderListArea","updateOrderList",${requestScope.totalPage},1);
</script>
<%@ include file="../common/IncludeBottom.jsp"%>
