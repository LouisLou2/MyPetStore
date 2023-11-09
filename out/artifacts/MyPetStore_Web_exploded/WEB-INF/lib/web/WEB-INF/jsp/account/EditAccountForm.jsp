<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
<form id="modifyForm" action="${pageContext.request.contextPath}/account/update" method="post">
	<h3>User Information</h3>
	<table>
		<tr>
			<td>User ID:</td>
			<td>${sessionScope.account.username}</td>
		</tr>
		<tr>
			<td>New password:</td>
			<td><input type="text" name="password" autofocus="autofocus"/></td>
		</tr>
		<tr>
			<td>Repeat password:</td>
			<td><input type="text" name="repeatedPassword" /></td>
		</tr>
	</table>
	<%@ include file="IncludeAccountFields.jsp"%>
<%--	<input type="submit" name="editAccount" value="Save Account Information" />--%>
	<button type="button" onclick="modifyButtonClicked()">Save Account Information</button>
</form>
	<!--event="listOrders-->
	<a href="${pageContext.request.contextPath}/shop/view/listorder?username=${sessionScope.account.username}">My Orders</a>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/verification.js"></script>
<script>
	let formId="modifyForm";
	let url="${pageContext.request.contextPath}/account/update";
	let preparedParams={
		username:"${sessionScope.account.username}"
	};
	function modifyButtonClicked() {
		CheckAndSubmit(formId,url,preparedParams);
	}
</script>

<%@ include file="../common/IncludeBottom.jsp"%>
