<%@ include file="../common/IncludeTop.jsp"%>

<div id="Content">
	<ul class="messages">
		<li>${requestScope.message}</li>
	</ul>

<div id="BackLink">
	<a href="${pageContext.request.contextPath}/main">Return to Main Menu</a>
</div>

<div class="container">
	<div class="container text-center">
		<div class="row">
			<div class="col"></div>
			<div class="col-10">
				<h2>Order #${requestScope.order.orderId}
					<fmt:formatDate value="${requestScope.order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" /></h2>
				<hr>
			</div>
			<div class="col"></div>
		</div>
		<div class="row">
			<div class="col"></div>
			<div class="col-6">
				<%@ include file="../widget/creditCard.jsp"%>
				<table id="PaymentDetailsTable" class="simple-table" style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								Payment Details
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Card Number:</td>
							<td><c:out value="${requestScope.order.creditCard}" /> * Fake number!</td>
						</tr>
						<tr>
							<td>Expiry Date (MM/YYYY):</td>
							<td><c:out value="${requestScope.order.expiryDate}" /></td>
						</tr>
						<tr>
							<td>Card Type:</td>
							<td><c:out value="${requestScope.order.cardType}" /></td>
						</tr>
					</tbody>
				</table>
				<table id="OrderBillTable" class="simple-table" style="width: 100%">
					<thead>
					<tr>
						<th colspan="2">
							Billing Address
						</th>
					</tr>
					</thead>
					<tbody id="OrderBillConfirmationTableTbody">
					<tr>
						<td>First name:</td>
						<td><c:out value="${requestScope.order.billToFirstName}" /></td>
					</tr>
					<tr>
						<td>Last name:</td>
						<td><c:out value="${requestScope.order.billToLastName}" /></td>
					</tr>
					<tr>
						<td>Address 1:</td>
						<td><c:out value="${requestScope.order.billAddress1}" /></td>
					</tr>
					<tr>
						<td>Address 2:</td>
						<td><c:out value="${requestScope.order.billAddress2}" /></td>
					</tr>
					<tr>
						<td>City:</td>
						<td><c:out value="${requestScope.order.billCity}" /></td>
					</tr>
					<tr>
						<td>State:</td>
						<td><c:out value="${requestScope.order.billState}" /></td>
					</tr>
					<tr>
						<td>Zip:</td>
						<td><c:out value="${requestScope.order.billZip}" /></td>
					</tr>
					<tr>
						<td>Country:</td>
						<td><c:out value="${requestScope.order.billCountry}" /></td>
					</tr>
					</tbody>
				</table>
				<table id="OrderShippingTable" class="simple-table" style="width: 100%">
					<thead>
					<tr>
						<th colspan="2">
							Shipping Address
						</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<td>First name:</td>
						<td><c:out value="${requestScope.order.shipToFirstName}" /></td>
					</tr>
					<tr>
						<td>Last name:</td>
						<td><c:out value="${requestScope.order.shipToLastName}" /></td>
					</tr>
					<tr>
						<td>Address 1:</td>
						<td><c:out value="${requestScope.order.shipAddress1}" /></td>
					</tr>
					<tr>
						<td>Address 2:</td>
						<td><c:out value="${requestScope.order.shipAddress2}" /></td>
					</tr>
					<tr>
						<td>City:</td>
						<td><c:out value="${requestScope.order.shipCity}" /></td>
					</tr>
					<tr>
						<td>State:</td>
						<td><c:out value="${requestScope.order.shipState}" /></td>
					</tr>
					<tr>
						<td>Zip:</td>
						<td><c:out value="${requestScope.order.shipZip}" /></td>
					</tr>
					<tr>
						<td>Country:</td>
						<td><c:out value="${requestScope.order.shipCountry}" /></td>
					</tr>
					<tr>
						<td>Courier:</td>
						<td><c:out value="${requestScope.order.courier}" /></td>
					</tr>
					<tr>
						<td colspan="2">Status: <c:out value="${requestScope.order.status}" /></td>
					</tr>
					</tbody>
				</table>
				<table id="LineItemTable" class="simple-table" style="width: 100%">
					<thead>
						<tr>
							<th>Picture</th>
							<th>Item ID</th>
							<th>Description</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Total Cost</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="lineItem" items="${requestScope.order.lineItems}">
						<tr>
							<td>
								<img class="mr-3" src="${pageContext.request.contextPath}${lineItem.item.product.picture}" alt="image">
							</td>
							<td>
								<a href="${pageContext.request.contextPath}/shop/view/item?itemId=${lineItem.item.itemId}">${lineItem.item.itemId}</a>
							</td>
							<td><c:if test="${lineItem.item != null}">
								${lineItem.item.attribute1}
								${lineItem.item.attribute2}
								${lineItem.item.attribute3}
								${lineItem.item.attribute4}
								${lineItem.item.attribute5}
								${lineItem.item.product.name}
							</c:if>
								<c:if test="${lineItem.item == null}">
									<i>{description unavailable}</i>
								</c:if></td>

							<td>${lineItem.quantity}</td>
							<td><fmt:formatNumber value="${lineItem.unitPrice}" pattern="$#,##0.00" /></td>
							<td><fmt:formatNumber value="${lineItem.total}" pattern="$#,##0.00" /></td>
						</tr>
					</c:forEach>
					<tr>
						<th colspan="5">
							Total: <fmt:formatNumber value="${requestScope.order.totalPrice}" pattern="$#,##0.00" />
						</th>
					</tr>
					</tbody>
				</table>
			</div>
			<div class="col"></div>
		</div>
	</div>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
