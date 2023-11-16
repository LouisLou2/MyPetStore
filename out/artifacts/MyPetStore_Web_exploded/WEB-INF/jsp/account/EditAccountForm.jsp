<%@ include file="../common/IncludeTop.jsp"%>

<div class="container">
	<div class="row">
		<div class="col"></div>
		<div class="col-6">
			<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
				<li class="nav-item" role="presentation">
					<button class="nav-link active" id="pills-home-tab" data-toggle="pill" data-target="#pills-PasswordModify" type="button" role="tab" aria-controls="pills-home" aria-selected="true">Password Modify</button>
				</li>
				<li class="nav-item" role="presentation">
					<button class="nav-link" id="pills-profile-tab" data-toggle="pill" data-target="#pills-DetailInfoModify" type="button" role="tab" aria-controls="pills-profile" aria-selected="false">Other Information Modify</button>
				</li>
			</ul>
			<div class="tab-content" id="pills-tabContent">
				<div class="tab-pane fade show active" id="pills-PasswordModify" role="tabpanel" aria-labelledby="pills-home-tab">
					<form id="PasswordModifyForm"  method="post" enctype="application/x-www-form-urlencoded">
						<fieldset disabled>
							<div class="form-group">
								<label for="username">User Name</label>
								<input type="text" name="username" id="username" class="form-control" aria-describedby="usernameHelp" placeholder="" value="${sessionScope.account.username}">
								<small id="usernameHelp" class="form-text text-muted">Username can't be Modified Currently.</small>
							</div>
						</fieldset>
						<div class="form-group">
							<label for="password">Password</label>
							<input type="password" name="password" class="form-control" id="password" placeholder="Password" required>
						</div>
						<div class="form-group">
							<label for="newPassword">New Password</label>
							<input type="password" name="newPassword" class="form-control" id="newPassword" placeholder="New Password" required>
						</div>
						<button class="btn btn-primary" type="button" onclick="saveClicked('PasswordModify')">Save Change</button>
					</form>
				</div>
				<div class="tab-pane fade" id="pills-DetailInfoModify" role="tabpanel" aria-labelledby="pills-profile-tab">
					<form id="DetailInfoModifyForm" method="post" enctype="application/x-www-form-urlencoded">
						<div class="form-row">
							<div class="col-md-4 mb-3">
								<label for="validationServer01">First name</label>
								<input type="text" name="firstName" class="form-control" id="validationServer01" placeholder="First name" value="${sessionScope.account.firstName}" required>
							</div>
							<div class="col-md-4 mb-3">
								<label for="validationServer02">Last name</label>
								<input type="text" name="lastName" class="form-control" id="validationServer02" placeholder="Last name" value="${sessionScope.account.lastName}" required>
							</div>
						</div>
						<div class="form-row">
							<div class="col-md-3 mb-3">
								<label for="validationServer03">Country</label>
								<input type="text" name="country"  class="form-control" id="validationServer0" placeholder="Country" value="${sessionScope.account.country}" required>
							</div>
							<div class="col-md-3 mb-3">
								<label for="validationServer04">State</label>
								<input type="text" name="state" class="form-control" id="validationServer04" placeholder="State" value="${sessionScope.account.state}" required>
							</div>
							<div class="col-md-3 mb-3">
								<label for="validationServer03">City</label>
								<input type="text" name="city" class="form-control" id="validationServer03" placeholder="City" value="${sessionScope.account.city}" required>
							</div>
							<div class="col-md-3 mb-3">
								<label for="validationServer05">Zip</label>
								<input type="text" name="zip" class="form-control" id="validationServer05" placeholder="Zip" value="${sessionScope.account.zip}" required>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<label for="addr1">Address1</label>
								<input type="text" name="address1" class="form-control" id="addr1" value="${sessionScope.account.address1}" placeholder="Enter A Address">
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<label for="addr2">Address2</label>
								<input type="text" name="address2" class="form-control" id="addr2" value="${sessionScope.account.address2}" placeholder="Enter A Address">
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
						<button class="btn btn-primary" type="button" onclick="saveClicked('DetailInfoModify')">Save Change</button>
					</form>
				</div>
			</div>
		</div>
		<div class="col"></div>
	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/verification.js"></script>
<script>
	function saveClicked(operation) {
		let form = document.getElementById(operation+"Form");
		let formDataObject = getFields(form.elements);
		formDataObject['operation']=operation;
		axios.request({
			url: restUpdateAccountUrl,
			method: 'post',
			data: formDataObject,
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			}
		}).then(function (response) {
			if (parseInt(response.data.code)===0) {
				alert("Update Successfully!");
			} else {
				alert(response.data.loadings.error);
			}
		}).catch(function (error) {
			alert(error);
		});
	}
</script>

<%@ include file="../common/IncludeBottom.jsp"%>
