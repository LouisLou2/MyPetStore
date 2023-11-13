<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
	<a href="${pageContext.request.contextPath}/shop/view/product?productId=${requestScope.product.productId}">Return to ${requestScope.product.productId}</a>
</div>
<%--决定是否显示尚未登录的warning条--%>
<c:if test="${sessionScope.account == null}">
	<div id="signInWarningAlert" class="alert alert-warning alert-dismissible fade show" role="alert">
		You are not logged in. Click to <strong><a onclick="SignInWithOriginal('/shop/view/item?itemId=${requestScope.item.itemId}')">log in</a></strong>!
		<button type="button" class="close"  data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
	<script>
		$(document).ready(function() {
			$("#signInWarningAlert").alert();
		});
	</script>
</c:if>
<%--一个添加购物车是否成功的条--%>
<div id="addResultTip" class="alert alert-success alert-dismissible fade show" role="alert">
	<div name="alertMsg">You should check in on some of those fields below.</div>
	<button type="button" class="close" aria-label="Close" onclick="hideAlert('addResultTip')">
		<span aria-hidden="true">&times;</span>
	</button>
</div>
<div class="container">
	<div class="row">
		<div class="col"></div>
		<div class="col-6">
			<div class="card mb-3" style="max-width: 540px;">
				<div class="row no-gutters">
					<div class="col-md-4">
						<img src="${pageContext.request.contextPath}${requestScope.item.product.picture}" class="card-img" alt="...">
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<h5 class="card-title" style="text-align: left;">${requestScope.item.itemId}</h5>
							<p class="card-text" style="text-align: left;">${requestScope.item.product.name}</p>
							<p class="card-text" style="text-align: left;">${requestScope.item.product.description}</p>
							<p class="card-text" style="text-align: left;"><fmt:formatNumber value="${requestScope.item.listPrice}" pattern="$#,##0.00" /></p>
							<p class="card-text" style="text-align: left;"><c:if test="${requestScope.item.quantity <= 0}">
								Back ordered.
							</c:if> <c:if test="${requestScope.item.quantity > 0}">
								${requestScope.item.quantity} in stock.
							</c:if></p>
							<div class="col-md-7 mb-1" style="padding-left: 0">
								<input type="number" class="form-control" id="addnumber" placeholder="Choose Quantity" value="1" min="1" required>
							</div>
							<p style="text-align: left;"><button style="text-align: left;" type="button" class="btn btn-outline-success" id="addcartbtn" onclick="queryAddItemToCart()">Add To Cart</button></p>
							<p class="card-text" style="text-align: left;"><small class="text-muted">${requestScope.item.attribute1}
								${requestScope.item.attribute2}
								${requestScope.item.attribute3}
								${requestScope.item.attribute4}
								${requestScope.item.attribute5}
								${requestScope.item.product.name}</small></p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col"></div>
	</div>
</div>
<%--Modal用来决定弹出是否要登录--%>
<div class="modal fade" id="signInModal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">You are NOT logged in. </h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="SignInWithOriginal('/shop/view/item?itemId=${requestScope.item.itemId}')">Sign In</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<script>
	let addSuccessMsg = "Add to cart successfully";
	let NumberFailMsg = "Please enter a positive integer";
	hideAlert("addResultTip");
	initModal("#signInModal");
	function checkNumber(num){
		var reg = /^[1-9]\d*$/;
		if (reg.test(num)) {
			return true;
		} else {
			return false;
		}
	}
	//不用jquery
	let url="${pageContext.request.contextPath}/rest/shop/addcart/item";
	function queryAddItemToCart(){
		var num = document.getElementById("addnumber").value;
		if(num==null||num==""||!checkNumber(num)){
			showFailAlert("addResultTip",NumberFailMsg);
			return;
		}
		axios.request({
			url:url,
			method:'post',
			params:{
				itemId:"${requestScope.item.itemId}",
				quantity:num
			}
		}).then(function(response){
			let code=response.data.code;
			console.log(code);
			console.log("at top");
			if(code==0){
				console.log("at 0");
				showSuccessAlert("addResultTip",addSuccessMsg);
				return;
			}
			if(code==3){
				console.log("at 3");
				showModal("#signInModal");
				return;
			}
			console.log("at bottom");
			showFailAlert("addResultTip",response.data.loadings.error);
		}).catch(function(error){
			alert(error);
		});
	}
</script>

<%@ include file="../common/IncludeBottom.jsp"%>