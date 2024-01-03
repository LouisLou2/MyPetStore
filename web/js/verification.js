var contextPath=document.getElementById("contextPath").innerHTML;
function changeImg(img_ele) {
    console.log("enter");
    console.log(img_ele);
    axios.request({
    url: imgCodeUrl,
    method: 'get',
    responseType: 'blob'
    }).then(function (response) {
        img_ele.src = URL.createObjectURL(response.data);
        console.log(img_ele.src);
    }).catch(function (error) {
        console.log(error);
    });
}
function isPhoneValid(phone) {
    const PHONE_REGEX = /^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\d{8}$/;
    return PHONE_REGEX.test(phone);
}

function isEmailValid(email) {
    const EMAIL_REGEX = /[\w\.\-]+@([\w\-]+\.)+[\w\-]+/;
    return EMAIL_REGEX.test(email);
}
function hasBlank(formElements){
    // 遍历输入字段，并检查是否为空
    for (var i = 0; i < formElements.length; i++) {
        console.log(formElements[i].type+" "+formElements[i].name+" "+formElements[i].value);
        if (formElements[i].type !== "checkbox"&&formElements[i].type !== "radio"&&formElements[i].type!=="submit"&&formElements[i].type!=="button"&&formElements[i].value.trim() === "") {
            console.log(formElements[i].type+" "+formElements[i].name+" "+formElements[i].value);
            alert("Please fill out all fields.");
            return true; // 如果发现一个字段为空，中止循环
        }
    }
    return false;
}
function getFields(formElements){
    // 创建一个空对象，用于存储表单数据
    let formDataObject = {};
    // 遍历表单元素，并将其添加到 formDataObject
    for (let i = 0; i < formElements.length; i++) {
        let element = formElements[i];
        // 检查元素是否为表单字段
        if (element.name) {
            if (element.type === "checkbox" || element.type === "radio") {
                formDataObject[element.name] = element.checked;
            } else {
                formDataObject[element.name] = element.value;
            }
        }
    }
    return formDataObject;
}
function signIn(formid,url,img_ele,preparedParams=undefined) {
    // 获取表单元素
    var form = document.getElementById(formid);
    if(hasBlank(form.elements)){
        console.log("has blank fields");
        return;
    }
    // 获取表单中的所有输入字段
    let formElements = form.elements;
    let formDataObject= getFields(formElements);
    if(preparedParams!==undefined&&preparedParams!=null){
        for(var key in preparedParams){
            formDataObject[key]=preparedParams[key];
        }
    }
    //开始提交
    axios.request({
        url: url,
        method: form.method,
        data:formDataObject,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }).then(function (response) {
        console.log(response);
        if(response.data.code==1){
            alert(response.data.loadings.error);
            changeImg(img_ele);
            return;
        }
        let newLocation = response.data.location;
        if(newLocation == null||newLocation.trim()===""){
            newLocation=localStorage.getItem('originalLink');
        }
        console.log("at:verification.signIn "+newLocation);
        window.location.replace(newLocation);
    }).catch(function (error) {
        console.error(error);
    });
}
function CheckAndSubmit(formid,url,preparedParams=undefined) {
    // 获取表单元素
    var form = document.getElementById(formid);
    // 获取表单中的所有输入字段
    var formElements = form.elements;
    if(hasBlank(form.elements)){
        return;
    }
    //验证手机号
    var phoneInput = form.elements["phone"];
    if (!isPhoneValid(phoneInput.value)) {
        alert("Please enter a valid phone number.");
        return;
    }
    //验证邮箱
    var emailInput = form.elements["email"];
    if (!isEmailValid(emailInput.value)) {
        alert("Please enter a valid email address.");
        return;
    }
    // 创建一个空对象，用于存储表单数据
    let formDataObject = getFields(formElements);
    if(preparedParams!=undefined){
        for(var key in preparedParams){
            formDataObject[key]=preparedParams[key];
        }
    }
    //开始提交
    axios.request({
        url: url,
        method: form.method,
        data:formDataObject,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }).then(function (response) {
        console.log(response);
        if(parseInt(response.data.code)!==0){
            alert(response.data.loadings.error);
            return;
        }else{
            let newLocation = response.data.location;
            console.log("at:verification.CheckAndSubmit "+newLocation);
            if(newLocation==null||newLocation==undefined||newLocation.trim()===""){
                newLocation=localStorage.getItem('originalLink');
            }
            window.location.replace(newLocation);
        }
    }).catch(function (error) {
        console.error(error);
    });
}
