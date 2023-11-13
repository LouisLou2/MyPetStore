<%@ include file="../common/IncludeTop.jsp"%>
<div class="container">
	<div class="row">
		<div class="col"></div>
		<div class="col-6">
			<h2>Sign In</h2>
			<form id="signonInfo" action="${pageContext.request.contextPath}/account/signin" method="post" enctype="application/x-www-form-urlencoded">
				<div class="form-group">
					<label for="exampleInputEmail1">UserName</label>
					<input type="text" name="username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="User Name">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">Password</label>
					<input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
				</div>
				<div class="form-group">
					<label for="vCode">Image Code</label>
					<div class="form-row">
						<div class="col-md-4 mb-3">
							<input type="text" id="vCode" class="form-control" name="vCode" placeholder="Email Code" value="" required>
						</div>
						<div class="col-md-4 mb-3">
							<img id ="img_code" border="0" src="" name="checkcode" width="50%" height="80%">
						</div>
					</div>
				</div>
				<span><button type="button" onclick="submitClicked()" class="btn btn-primary">Sign In</button></span>
				<div class="form-row">
					<span>
					Don't have an account?
					<a href="${pageContext.request.contextPath}/page/account/register"><strong>Register Now!</strong></a>
				</span>
				</div>
			</form>
		</div>
		<div class="col"></div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/verification.js"></script>
<script>
	//设置验证码地址
	let img_code=document.getElementById("img_code");
	img_code.src=imgCodeUrl;
	img_code.addEventListener("click", function (e) {
		console.log("Listener");
		e.preventDefault(); // 防止链接的默认行为（刷新页面）
		changeImg("img_code");
	});
	let formId= "signonInfo";
	let url = "${pageContext.request.contextPath }/rest/account/signin";
	preparedParams={};
	if(localStorage.getItem("originalLink")!=null){
		preparedParams["originalLink"]=localStorage.getItem("originalLink");
		localStorage.setItem("originalLink",null);
	}
	function submitClicked(){
		console.log(preparedParams);
		signIn(formId,url,preparedParams);
	}
</script>

<%@ include file="../common/IncludeBottom.jsp"%>

