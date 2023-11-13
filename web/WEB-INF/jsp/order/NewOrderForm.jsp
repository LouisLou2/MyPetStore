<%@ include file="../common/IncludeTop.jsp"%>
<div id="signInWarningAlert" class="alert alert-warning alert-dismissible fade show" role="alert">
	Incorrect details may lead to order review and return. Please be careful.
	<button type="button" class="close"  data-dismiss="alert" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>
<script>
	$(document).ready(function() {
		$("#signInWarningAlert").alert();
	});
</script>
<div class="container">
	<div class="row">
		<div class="col"></div>
		<div class="col-6">
			<h3>Payment Details</h3>
			<form id="registerInfo" action="${pageContext.request.contextPath}/page/shop/confirm/order" method="post" enctype="application/x-www-form-urlencoded">
				<div class="col-auto my-1">
					<label class="mr-sm-2" for="cardType">Card Type:</label>
					<select class="custom-select mr-sm-2" name="cardType" id="cardType">
						<option value="Visa" selected="selected">Visa</option>
						<option value="MasterCard">MasterCard</option>
						<option value="American Express">American Express</option>
					</select>
				</div>
				<div class="form-group">
					<label for="creditCard">Phone Number</label>
					<input type="number" name="creditCard" value="999999999999" id="creditCard" class="form-control" aria-describedby="cardNumberHelp" placeholder="Card Number" required>
					<small id="cardNumberHelp" class="form-text text-muted">* Use a fake number!</small>
					<div id="cardNumberFeedback" class="invalid-feedback"></div>
				</div>
				<div class="form-group">
					<label for="expireDate">Expire Data</label>
					<input type="text" name="expireDate" value="12/03" id="expireDate" class="form-control" aria-describedby="emailHelp" placeholder="Expire Date" required>
					<div id="expireDateFeedback" class="invalid-feedback"></div>
				</div>
				<h4>Billing Information</h4>
				<div class="form-row">
					<div class="col-md-4 mb-3">
						<label for="billToFirstName">First name</label>
						<input type="text"  name="billToFirstName" value="${requestScope.order.billToFirstName}" class="form-control" id="billToFirstName" placeholder="First name" required>
					</div>
					<div class="col-md-4 mb-3">
						<label for="billToLastName">Last name</label>
						<input type="text" name="billToLastName" value="${requestScope.order.billToLastName}" class="form-control" id="billToLastName" placeholder="Last name" required>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-3 mb-3">
						<label for="billCountry">Country</label>
						<input type="text" name="billCountry" value="${requestScope.order.billCountry}"  class="form-control" id="billCountry" placeholder="Country" required>
					</div>
					<div class="col-md-3 mb-3">
						<label for="billState">State</label>
						<input type="text" name="billState" value="${requestScope.order.billState}"class="form-control" id="billState" placeholder="State" required>
					</div>
					<div class="col-md-3 mb-3">
						<label for="billCity">City</label>
						<input type="text" name="billCity" value="${requestScope.order.billCity}"  class="form-control" id="billCity" placeholder="City" required>
					</div>
					<div class="col-md-3 mb-3">
						<label for="billZip">Zip</label>
						<input type="text" name="billZip" value="${requestScope.order.billZip}" class="form-control" id="billZip" placeholder="Zip" required>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group">
						<label for="billAddress1">Address1</label>
						<input type="text" name="billAddress1" value="${requestScope.order.billAddress1}"  class="form-control" id="billAddress1"  placeholder="Enter Address" required>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group">
						<label for="billAddress2">Address2</label>
						<input type="text"  name="billAddress2" value="${requestScope.order.billAddress2}" class="form-control" id="billAddress2"  placeholder="Enter Different Address" required>
					</div>
				</div>

				<div class="form-row">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="hasDifferentShippingInfo" value="1" id="shippingAreaChecker" onchange="shippingAreaChange()" >
						<label class="form-check-label" for="shippingAreaChecker">
							Use Address Different From Billing Address
						</label>
					</div>
				</div>
				
				<div id="shippingArea" style="" class="mt-3 mb-3">
					<h4>Shipping Information</h4>
					<div class="form-row">
						<div class="col-md-4 mb-3">
							<label for="shipToFirstName">First name</label>
							<input type="text"  name="shipToFirstName" value="${requestScope.order.shipToFirstName}" class="form-control" id="shipToFirstName" placeholder="First name" required>
						</div>
						<div class="col-md-4 mb-3">
							<label for="shipToLastName">Last name</label>
							<input type="text" name="shipToLastName" value="${sessionScope.order.shipToLastName}" class="form-control" id="shipToLastName" placeholder="Last name" required>
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-3 mb-3">
							<label for="shipCountry">Country</label>
							<input type="text" name="shipCountry" value="${requestScope.order.shipCountry}"  class="form-control" id="shipCountry" placeholder="Country" required>
						</div>
						<div class="col-md-3 mb-3">
							<label for="shipState">State</label>
							<input type="text" name="shipState" value="${requestScope.order.shipState}"class="form-control" id="shipState" placeholder="State" required>
						</div>
						<div class="col-md-3 mb-3">
							<label for="shipCity">City</label>
							<input type="text" name="shipCity" value="${requestScope.order.shipCity}"  class="form-control" id="shipCity" placeholder="City" required>
						</div>
						<div class="col-md-3 mb-3">
							<label for="shipZip">Zip</label>
							<input type="text" name="shipZip" value="${requestScope.order.shipZip}" class="form-control" id="shipZip" placeholder="Zip" required>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group">
							<label for="shipAddress1">Address1</label>
							<input type="text" name="shipAddress1" value="${requestScope.order.shipAddress1}"  class="form-control" id="shipAddress1"  placeholder="Enter Address" required>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group">
							<label for="shipAddress2">Address2</label>
							<input type="text"  name="shipAddress2" value="${requestScope.order.shipAddress2}" class="form-control" id="shipAddress2"  placeholder="Enter Different Address" required>
						</div>
					</div>
				</div>
				<button class="btn btn-primary" type="submit">Submit Order</button>
			</form>
		</div>
		<div class="col"></div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/verification.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/register.js"></script>
<script>
	var shippingArea = document.getElementById("shippingArea");
	var shippingAreaInputs = shippingArea.getElementsByTagName("input");
	var shippingAreaChecker = document.getElementById("shippingAreaChecker");
	function shippingAreaChange() {
		if (shippingAreaChecker.checked) {
			shippingArea.style.display = 'block';
			for (var i = 0; i < shippingAreaInputs.length; i++) {
				shippingAreaInputs[i].required = true;
			}
		} else {
			shippingArea.style.display = 'none';
			for (var i = 0; i < shippingAreaInputs.length; i++) {
				shippingAreaInputs[i].required = false;
			}	
		}
	}
	shippingAreaChange();
</script>

<%@ include file="../common/IncludeBottom.jsp"%>