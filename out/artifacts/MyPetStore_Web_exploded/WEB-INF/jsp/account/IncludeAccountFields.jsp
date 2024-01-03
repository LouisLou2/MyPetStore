<h3>Account Information</h3>

<table>
	<tr>
		<td>First name:</td>
		<td><input type="text" name="firstName" value="${sessionScope.account.firstName}"/></td>
	</tr>
	<tr>
		<td>Last name:</td>
		<td><input type="text" name="lastName" value="${sessionScope.account.lastName}"/></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td><input type="text" size="40" name="email" value="${sessionScope.account.email}" /></td>
	</tr>
<%--	<tr>--%>
<%--		<td>EmailCode:</td>--%>
<%--		<td><input type="text" size="40" name="emailcode" value="${sessionScope.account.email}" /></td>--%>
<%--		<td><input type="button" id="emailcodeBtn" value="Get Email Code" onclick="sendEmailCodeClicked(this)" /></td>--%>
<%--		<p><span id="emailcodeTime">60</span>秒后重获取</p>--%>
<%--	</tr>--%>
	<tr>
		<td>Phone:</td>
		<td><input type="text" name="phone" value="${sessionScope.account.phone}" /></td>
	</tr>
	<tr>
		<td>Address 1:</td>
		<td><input type="text" size="40" name="address1" value="${sessionScope.account.address1}" /></td>
	</tr>
	<tr>
		<td>Address 2:</td>
		<td><input type="text" size="40" name="address2" value="${sessionScope.account.address2}" /></td>
	</tr>
	<tr>
		<td>City:</td>
		<td><input type="text" name="city" value="${sessionScope.account.city}" /></td>
	</tr>
	<tr>
		<td>State:</td>
		<td><input type="text" size="4" name="state" value="${sessionScope.account.state}" /></td>
	</tr>
	<tr>
		<td>Zip:</td>
		<td><input type="text" size="10" name="zip" value="${sessionScope.account.zip}" /></td>
	</tr>
	<tr>
		<td>Country:</td>
		<td><input type="text" size="15" name="country" value="${sessionScope.account.country}" /></td>
	</tr>
</table>

<h3>Profile Information</h3>

<table>
	<tr>
		<td>Language Preference:</td>
			<td>
				<select name="languagePreference">
					<option value="english" selected="selected">english</option>
					<option value="japanese">japanese</option>
			    </select>
			</td>
	</tr>
	<tr>
		<td>Favourite Category:</td>
		<td>
			<select name="favouriteCategoryId">
				<option value="FISH">FISH</option>
				<option selected="selected" value="DOGS">DOGS</option>
				<option value="REPTILES">REPTILES</option>
				<option value="CATS">CATS</option>
				<option value="BIRDS">BIRDS</option>
		    </select>
		</td>
	</tr>
	<tr>
		<td>Enable MyList</td>
		<td><input type="checkbox" name="listOption" value="${sessionScope.account.listOption}"/></td>
	</tr>
	<tr>
		<td>Enable MyBanner</td>
		    <td><input type="checkbox" name="bannerOption" value="${sessionScope.account.bannerOption}"/></td>
	</tr>

</table>
	<script type="text/javascript">
		function isEmailValid(email) {
			const EMAIL_REGEX = /[\w\-]+@([\w\-]+\.)+[\w\-]+/;
			return EMAIL_REGEX.test(email);
		}
		function sendEmailCodeClicked(targetElement) {
			//获取输入的邮箱
			let email = document.getElementsByName("email")[0].value;
			console.log(email);
			if(!isEmailValid(email)){
				alert("The Email Address is Invalid!");
				return;
			}
			axios.request({
				url: verifyEmailCodeUrl,
				method: 'get',
				params: {
					email: email
				}
			}).then(function (response) {
				if(parseInt(response.data.code) !== 0){
					alert(response.data.error);
					return;
				}
				setTime(targetElement);
			}).catch(function (error) {
				console.log(error);
			})
		}
		function setTime(targetElement) {
			//倒计时初始化秒数
			var countdown = 60;
			var id = setInterval(setCountdown, 1000, targetElement);
			//设置倒计时
			function setCountdown(targetElement) {
			if (countdown == 0) {
			targetElement.removeAttribute("disabled");
			targetElement.value = "Get Email Code";
			countdown = 60;
			clearInterval(id);//停止调用函数
		} else {
			targetElement.setAttribute("disabled", true);
			targetElement.value = "Again(" + countdown + ")";
			countdown--;
		}
			}
		}
</script>
