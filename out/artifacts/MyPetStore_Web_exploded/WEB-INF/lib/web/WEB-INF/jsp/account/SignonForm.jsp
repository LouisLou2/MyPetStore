<%@ include file="../common/IncludeTop.jsp"%>

<div id="Content">
	<c:if test="${sessionScope.messageSignOn != null}">
		<ul class="messages">
			<li>${sessionScope.messageSignOn}</li>
		</ul>
	</c:if>

<div id="Catalog">
	<form id="signonInfo" action="${pageContext.request.contextPath}/account/signin" method="post">
		<p>Please enter your username and password.</p>
		<p>
			Username:<input type="text" name="username" value="j2ee"/><br />
		    Password:<input type="password" name="password" value="j2ee"/><br />
			VerificationCode:<input type="text" name="vCode" size="5" maxlength="4"/>
			<img id ="img_code" border="0" src="${pageContext.request.contextPath }/verify/get_img_code" name="checkcode">
		</p>
<%--		<input type="submit" name="signon" value="Login" />--%>
		<button type="button" onclick="submitClicked()">Login</button>
	</form>
		Need a user name and password?
	    <!--newAccountForm-->
	    <a href="${pageContext.request.contextPath}/page/account/register">Register Now!</a>
</div>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/verification.js"></script>
	<script>
		document.getElementById("img_code").addEventListener("click", function (e) {
			console.log("Listener");
			e.preventDefault(); // 防止链接的默认行为（刷新页面）
			changeImg("img_code");
		});
		let formId= "signonInfo";
		let url = "${pageContext.request.contextPath }/account/signin";
		function submitClicked(){
			signIn(formId,url);
		}
	</script>

<%@ include file="../common/IncludeBottom.jsp"%>

