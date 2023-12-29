var contextPath=document.getElementById("contextPath").innerHTML;
var FieldEle;
var feedbackId;
var key;
function CheckExist(id,akey,afeedbackId) {
    FieldEle = document.getElementById(id);
    feedbackId = afeedbackId;
    key = akey;
    let value = FieldEle.value;
    sendRequest(checkExistUrl,{key:key,value:value});
}

function sendRequest(url,params=undefined) {
    console.log(params);
    axios.request({
        url: url,
        method: 'post',
        data:params,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }).then(function (response) {
        console.log(response);
        let code=parseInt(response.data.code);
        giveTip(code);
    }).catch(function (error) {
        console.error("An error occurred: " + error);
    });
}

function giveTip(code) {
    console.log("into giveTip");
    var div1 = document.getElementById(feedbackId);
    if (code === 0) {
        if(FieldEle.classList.contains("is-invalid")) {
            FieldEle.classList.remove("is-invalid");
        }
        if(!FieldEle.classList.contains("is-valid")) {
            FieldEle.classList.add("is-valid");
        }
        if(div1.classList.contains("invalid-feedback")) {
            div1.classList.remove("invalid-feedback");
        }
        if(!div1.classList.contains("valid-feedback")) {
            div1.classList.add("valid-feedback");
        }
        div1.innerHTML = "Available";
        console.log(key+"available");
    } else {
        if(FieldEle.classList.contains("is-valid")) {
            FieldEle.classList.remove("is-valid");
        }
        if(!FieldEle.classList.contains("is-invalid")) {
            FieldEle.classList.add("is-invalid");
        }
        if(div1.classList.contains("valid-feedback")) {
            div1.classList.remove("valid-feedback");
        }
        if(!div1.classList.contains("invalid-feedback")) {
            div1.classList.add("invalid-feedback");
        }
        div1.innerHTML = key+" Already Exists";
        console.log(key+"already exists");
    }
}
function isEmailValid(email) {
    const EMAIL_REGEX = /[\w\.\-]+@([\w\-]+\.)+[\w\-]+/;
    return EMAIL_REGEX.test(email);
}
function sendEmailCodeClicked(targetElement,aexpectation) {
    let email = document.getElementsByName("email")[0].value;
    if(!isEmailValid(email)){
        console.log("The Email Address is Invalid!");
        alert("The Email Address is Invalid!");
        return;
    }
    //获取输入的邮箱
    setTime(targetElement);
    let url= emailCodeUrl;
    console.log(email);
    console.log("aexpectation");
    console.log(aexpectation);
    axios.request({
        url: url,
        method: 'get',
        params: {
            email: email,
            expectation: aexpectation,
        }
    }).then(function (response) {
        if(parseInt(response.data.code) !== 0){
            alert(response.data.loadings.error);
        }
    }).catch(function (error) {
        console.log(error);
    })
}
function setTime(targetElement) {
    //倒计时初始化秒数
    let countdown = 60;
    let id = setInterval(setCountdown, 1000, targetElement);
    //设置倒计时
    function setCountdown(targetElement) {
        if (countdown === 0) {
            targetElement.removeAttribute("disabled");
            targetElement.innerHTML = "Send Code";
            countdown = 60;
            clearInterval(id);//停止调用函数
        } else {
            targetElement.setAttribute("disabled", true);
            targetElement.innerHTML = "Again(" + countdown + ")";
            countdown--;
        }
    }
}