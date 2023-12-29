<%@ include file="../common/IncludeTop.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/register.js"></script>
${sessionScope.messageAccount}
<div class="container">
	<div class="row">
		<div class="col"></div>
		<div class="col-6">
			<h3>User Information</h3>
			<form id="registerInfo" action="account/register" method="post" enctype="application/x-www-form-urlencoded">
				<div class="form-group">
					<label for="email">Email Address</label>
					<input type="email" name="email" id="email" class="form-control" aria-describedby="emailHelp" placeholder="Enter email" onblur="CheckExist('email','email','emailFeedback');">
					<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
					<div id="emailFeedback" class="invalid-feedback"></div>
				</div>
				<div class="form-group">
					<label for="validationServer01">Email Code</label>
					<div class="form-row">
						<div class="col-md-4 mb-3">
							<input type="text" class="form-control" name="emailCode" placeholder="Email Code" value="" required>
						</div>
						<div class="col-md-4 mb-3">
							<button id="emailcodeBtn" type="button" onclick="sendEmailCodeClicked(this,'0')" class="btn btn-primary my-1">Send Code</button>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="phone">Phone Number</label>
					<input type="number" name="phone" id="phone" class="form-control" aria-describedby="emailHelp" placeholder="Phone Number" onblur="CheckExist('phone','phone','phoneFeedback');">
					<small id="phoneHelp" class="form-text text-muted">You phone number will be protected very well.</small>
					<div id="phoneFeedback" class="invalid-feedback"></div>
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">Password</label>
					<input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">Confirm Password</label>
					<input type="password" name="repeatedPassword" class="form-control" id="exampleInputPassword2" placeholder="Enter Password Again">
				</div>
				<div class="form-row">
					<div class="col-md-4 mb-3">
						<label for="validationServer01">First name</label>
						<input type="text" name="firstName" class="form-control" id="validationServer01" placeholder="First name" value="" required>
					</div>
					<div class="col-md-4 mb-3">
						<label for="validationServer02">Last name</label>
						<input type="text" name="lastName" class="form-control" id="validationServer02" placeholder="Last name" value="" required>
					</div>
					<div class="col-md-4 mb-3">
						<label for="username">Username</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text" id="inputGroupPrepend3">@</span>
							</div>
							<input type="text" name="username" id="username"  onblur="CheckExist('username','username','usernameFeedback');" class="form-control" placeholder="Username" aria-describedby="inputGroupPrepend3" required>
							<div id="usernameFeedback" class="invalid-feedback"></div>
						</div>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-3 mb-3">
						<label for="validationServer03">Country</label>
						<input type="text" name="country"  class="form-control" id="validationServer0" placeholder="Country" required>
					</div>
					<div class="col-md-3 mb-3">
						<label for="validationServer04">State</label>
						<input type="text" name="state" class="form-control" id="validationServer04" placeholder="State" required>
					</div>
					<div class="col-md-3 mb-3">
						<label for="validationServer03">City</label>
						<input type="text" name="city" class="form-control" id="validationServer03" placeholder="City" required>
					</div>
					<div class="col-md-3 mb-3">
						<label for="validationServer05">Zip</label>
						<input type="text" name="zip" class="form-control" id="validationServer05" placeholder="Zip" required>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group">
						<label for="addr1">Address1</label>
						<input type="text" name="address1" class="form-control" id="addr1"  placeholder="Enter A Address">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group">
						<label for="addr2">Address2</label>
						<input type="text" name="address2" class="form-control" id="addr2" placeholder="Enter Another Address">
					</div>
				</div>
				<div class="col-auto my-1">
					<label class="mr-sm-2" for="inlineFormCustomSelect">Language Preference:</label>
					<select class="custom-select mr-sm-2" name="languagePreference" id="inlineFormCustomSelect">
						<option value="english" selected="selected">english</option>
						<option value="japanese">japanese</option>
					</select>
				</div>
				<div class="col-auto my-1">
					<label class="mr-sm-2" for="inlineFormCustomSelect">Favorite Category</label>
					<select class="custom-select mr-sm-2" name="favouriteCategoryId" id="inlineFormCustomSelec">
						<option value="FISH">FISH</option>
						<option selected="selected" value="DOGS">DOGS</option>
						<option value="REPTILES">REPTILES</option>
						<option value="CATS">CATS</option>
						<option value="BIRDS">BIRDS</option>
					</select>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="checkbox" name="listOption" value="" id="defaultCheck1">
					<label class="form-check-label" for="defaultCheck1">
						Enable MyList
					</label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="checkbox" name="bannerOption" value="" id="defaultCheck2">
					<label class="form-check-label" for="defaultCheck2">
						Enable MyBanner
					</label>
				</div>
				<button class="btn btn-primary" type="button" onclick="submitClicked()">Submit form</button>
			</form>
		</div>
		<div class="col"></div>
	</div>
</div>
	
<script type="text/javascript" src="${pageContext.request.contextPath }/js/verification.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/register.js"></script>
<script>
	// document.getElementById("checkcode_img").addEventListener("click", function (e) {
	// 	console.log("Listener");
	// 	e.preventDefault(); // 防止链接的默认行为（刷新页面）
	// 	changeImg("checkcode_img");
	// });
	let formId = "registerInfo";
	let url="${pageContext.request.contextPath }/account/register";
	let preparedParams={
		"emailExpectation":"0",//表示期望的email不存在，虽然前端已经验证过了，但是后端也要验证
	}
	function submitClicked(){
		CheckAndSubmit("registerInfo",url);
	}
</script>
<%@ include file="../common/IncludeBottom.jsp"%>
