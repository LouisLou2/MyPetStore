<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
	<a href="${pageContext.request.contextPath}/main">Return to Main Menu</a>
</div>
<div class="container">
	<div class="container text-center">
		<div>
			<h2>Order Confirmation</h2>
			<hr>
		</div>
	</div>
	<div class="row">
		<div class="col"></div>
		<div class="col-5">
			<table id="OrderBillConfirmationTable" class="simple-table">
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
		</div>
		<div class="col-5">
			<table id="OrderShippingConfirmationTable" class="simple-table">
				<thead>
				<tr>
					<th colspan="2">
						Shipping Address
					</th>
				</tr>
				</thead>
				<tbody id="OrderShippingConfirmationTableTbody">
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
				</tbody>
			</table>
		</div>
		<div class="col"></div>
	</div>
	<div class="container text-center">
		<div class="col align-self-center">
			<button type="button" class="btn btn-outline-success" onclick="ConfirmOrder()">Confirm</button>
		</div>
	</div>
</div>
<script>
	function ConfirmOrder() {
		//使用axios
		axios({
			method: 'get',
			url: restConfirmOrderUrl,
			data: {
			}
		}).then(function (response) {
			console.log(response);
			if (response.data.code==0) {
				alert("Order confirmed!");
			} else {
				alert(response.data.loadings.error);
			}
		}).catch(function (error) {
			console.log(error);
		});
		
	}
</script>
<%@ include file="../common/IncludeBottom.jsp"%>





