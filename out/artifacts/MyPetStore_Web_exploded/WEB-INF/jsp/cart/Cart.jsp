<%@ include file="../common/IncludeTop.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/cartChange.js"></script>

<div class="container">
	<div class="row">
		<div class="col"></div>
		<div class="col-8">
			<h2>Shopping Cart</h2>
			<hr>
			<table id="cartTable" class="simple-table">
				<thead>
				<tr>
					<th>Picture</th>
					<th>Item Id</th>
					<th>Product Id</th>
					<th>Detail</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Total Cost</th>
					<th>Remove</th>
				</tr>
				</thead>
				<tbody id="carTableTbody">
				<c:forEach var="cartItem" items="${requestScope.cart.cartItems}">
					<tr>
						<td>
							<img src="${pageContext.request.contextPath}${cartItem.item.product.picture}" height="100" width="auto" />
						</td>
						<td>
							<a href="${pageContext.request.contextPath}/shop/view/item?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
						</td>
						<td>
								${cartItem.item.product.productId}
						</td>
						<td>
								${cartItem.item.attribute1}
								${cartItem.item.attribute2}
								${cartItem.item.attribute3}
								${cartItem.item.attribute4}
								${cartItem.item.attribute5}
								${cartItem.item.product.name}
						</td>
						<td>
							<input type="number" id="quantity" name="${cartItem.item.itemId}" value="${cartItem.quantity}" min="1" onblur="updateCart(this.parentElement.parentElement)">
							<div id="cartMsg"></div>
						</td>
						<td>
							<fmt:formatNumber value="${cartItem.item.listPrice}" pattern="$#,##0.00" />
						</td>
						<td>
								<fmt:formatNumber value="${cartItem.total}" pattern="$#,##0.00" />
						<td>
								<%--						<button type="button" name="remove" onclick="removeBtnClicked(this)" class="btn btn-outline-dark">Remove</button>	--%>
							<button type="button" name="remove" onclick="removeBtnClicked(this)" class="btn btn-dark">Remove</button>
						</td>
					</tr>
				</c:forEach>
				<tr id="lastTR">
					<td colspan="7" id="lastTD">
						Sub Total:&nbsp;<strong id="subtotal">${requestScope.cart.subTotal}</strong>
						<!--<fmt:formatNumber value="${requestScope.cart.subTotal}" pattern="$#,##0.00" />-->
					</td>
					<td>&nbsp;</td>
				</tr>
				</tbody>
			</table>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-3"> <!-- 设置您想要的宽度，例如 col-6 -->
                        <c:if test="${requestScope.cart.numberOfItems > 0}">
                            <a class="btn btn-success" role="button" href="${pageContext.request.contextPath}/page/shop/newOrder">Checkout</a>
                        </c:if>
                    </div>
                </div>
            </div>
		</div>
		<div class="col"></div>
	</div>
</div>
<%-------------------------------------------------%>
<script>
	let arequestUrl="${pageContext.request.contextPath}/rest/shop/removecart/item";
	function removeBtnClicked(btnEle){
		let trEle=btnEle.parentNode.parentNode;
		let itemId = trEle.children[1].children[0].innerHTML;
		removeItemFromCart("carTableTbody",itemId,trEle);
	}
	//为什么添加click事件监听仍然没反应
</script>
<%@ include file="../common/IncludeBottom.jsp"%>