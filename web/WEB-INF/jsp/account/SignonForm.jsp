<%@ include file="../common/IncludeTop.jsp"%>
<div class="container">
	<div class="row">
		<div class="col"></div>
		<div class="col-6">
			<h2>Sign In</h2>
			<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
				<li class="nav-item" role="presentation">
					<button class="nav-link active" id="pills-home-tab" data-toggle="pill" data-target="#signonInfoTab" type="button" role="tab" aria-controls="pills-home" aria-selected="true">Using Password</button>
				</li>
				<li class="nav-item" role="presentation">
					<button class="nav-link" id="pills-profile-tab" data-toggle="pill" data-target="#signonInfoWithEmailTab" type="button" role="tab" aria-controls="pills-profile" aria-selected="false">Using Email</button>
				</li>
			</ul>
			<div class="tab-content" id="pills-tabContent">
				<div class="tab-pane fade show active" id="signonInfoTab" role="tabpanel" aria-labelledby="pills-home-tab">
					<form id="signonInfo" action="${pageContext.request.contextPath}/account/signin" method="post" enctype="application/x-www-form-urlencoded">
						<div class="form-group">
							<label for="username">UserName</label>
							<input type="text" name="username" class="form-control" id="username" aria-describedby="emailHelp" placeholder="User Name">
						</div>
						<div class="form-group">
							<label for="password">Password</label>
							<input type="password" name="password" class="form-control" id="password" placeholder="Password">
						</div>
						<div class="form-group">
							<label for="vCode">Image Code</label>
							<div class="form-row">
								<div class="col-md-4 mb-3">
									<input type="text" id="vCode" class="form-control" name="vCode" placeholder="Enter Image Code" value="" required>
								</div>
								<div class="col-md-4 mb-3">
									<img id ="img_code" border="0" src="" name="checkcode" width="50%" height="80%">
								</div>
							</div>
						</div>
						<span><button type="button" onclick="submitClicked('usePassword','signonInfo')" class="btn btn-primary">Sign In</button></span>
					</form>
				</div>
				<div class="tab-pane fade" id="signonInfoWithEmailTab" role="tabpanel" aria-labelledby="pills-home-tab">
					<form id="signonInfoWithEmail" action="${pageContext.request.contextPath}/account/signin" method="post" enctype="application/x-www-form-urlencoded">
						<div class="form-group">
							<label for="email">Email</label>
							<input type="text" name="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="User Name">
						</div>
						<div class="form-group">
							<label for="emailcode">Email Code</label>
							<div class="form-row">
								<div class="col-md-4 mb-3">
									<input type="text" class="form-control" name="emailCode" id="emailcode" placeholder="Email Code" value="" required>
								</div>
								<div class="col-md-4 mb-3">
									<button id="emailcodeBtn" type="button" onclick="sendEmailCodeClicked(this,'1')" class="btn btn-primary my-1">Send Code</button>
								</div>
							</div>
						</div>
						<span><button type="button" onclick="submitClicked('useEmail','signonInfoWithEmail')" class="btn btn-primary">Sign In</button></span>
					</form>
				</div>
			</div>
			<span class="pt-2" >
				Don't have an account?
				<a href="${pageContext.request.contextPath}/page/account/register" onclick="donthaveAccountClicked()"><strong>Register Now!</strong></a>
			</span>
		</div>
		<div class="col"></div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/verification.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/register.js"></script>
<script>
	//设置验证码地址
	let img_code=document.getElementById("img_code");
	img_code.src=imgCodeUrl;
	img_code.addEventListener("click", function (e) {
		e.preventDefault(); // 防止链接的默认行为（刷新页面）
		changeImg(img_code);
	});
	let url = "${pageContext.request.contextPath }/rest/account/signin";
	let preparedParams={}
	if(localStorage.getItem('active')!=null){
		preparedParams["active"]='1';
		localStorage.removeItem('active');
	}
	function submitClicked(signinWay,formId){
		preparedParams["signinWay"]=signinWay;
		signIn(formId,url,img_code,preparedParams);
	}
	function donthaveAccountClicked(){
		localStorage.setItem('active','1');
	}
</script>

<%@ include file="../common/IncludeBottom.jsp"%>

