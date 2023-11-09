<%@ include file="../common/IncludeTop.jsp"%>

${sessionScope.messageAccount}

<div id="Catalog">
	<form id="registerInfo" action="account/register" method="post" enctype="application/x-www-form-urlencoded">
		<h3>User Information</h3>
		<table>
			<tr>
				<td>User ID:</td>
				<td>
					<input type="text" name="username" id="username"  onblur="usernameIsExist();"/>
					<div id="usernameMsg"></div>
                    <script type="text/javascript" src="${pageContext.request.contextPath }/js/register.js"></script>
				</td>
			</tr>
			<tr>
				<td>New password:</td>
				<td><input type="text" name="password" /></td>
			</tr>
			<tr>
				<td>Repeat password:</td>
				<td><input type="text" name="repeatedPassword" /></td>
			</tr>
			<tr>
				<td>VerificationCode:</td>
				<td>
					<input type="text" name="vCode" size="5" maxlength="4"/>
					<img src="${pageContext.request.contextPath }/verify/get_img_code" id="checkcode_img" />
				</td>
			</tr>
		</table>

		<%@ include file="IncludeAccountFields.jsp"%>
		<button type="button" onclick="submitClicked()">Save Account Information</button>
<%--		<input type="submit" name="newAccount" value="Save Account Information" />--%>
	</form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/verification.js"></script>
<script>
	document.getElementById("checkcode_img").addEventListener("click", function (e) {
		console.log("Listener");
		e.preventDefault(); // 防止链接的默认行为（刷新页面）
		changeImg("checkcode_img");
	});
	let formId = "registerInfo";
	let url="${pageContext.request.contextPath }/account/register";
	function submitClicked(){
		CheckAndSubmit("registerInfo",url);
	}
</script>
<%@ include file="../common/IncludeBottom.jsp"%>
