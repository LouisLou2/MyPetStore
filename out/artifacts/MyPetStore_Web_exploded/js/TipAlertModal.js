function hideAlert(alertId){
    var alert = document.getElementById(alertId);
    //用display属性来控制显示或隐藏
    alert.style.display = "none";
}
function showSuccessAlert(alertId,alertMsg){
    let alert = document.getElementById(alertId);
    if(alert.classList.contains("alert-danger")){
        alert.classList.remove("alert-danger");
    }
    if(alert.classList.contains("alert-warning")){
        alert.classList.remove("alert-warning");
    }
    if(!alert.classList.contains("alert-success")){
        alert.classList.add("alert-success");
    }
    showAlert(alert,alertMsg)
}
function showFailAlert(alertId,alertMsg){
    let alert = document.getElementById(alertId);
    if(alert.classList.contains("alert-success")){
        alert.classList.remove("alert-success");
    }
    if(alert.classList.contains("alert-warning")){
        alert.classList.remove("alert-warning");
    }
    if(!alert.classList.contains("alert-danger")){
        alert.classList.add("alert-danger");
    }
    showAlert(alert,alertMsg)
}
function showAlert(alertEle,alertMsg){
    let content = alertEle.querySelector("div[name='alertMsg']");
    content.innerHTML = alertMsg;
    //用display属性来控制显示或隐藏
    alertEle.style.display = "block";
}
function initModal(id){
    $(id).modal({
        backdrop: 'static', // 设置静态背景，点击背景不会关闭 Modal
        keyboard: true,      // 允许通过按下 ESC 键关闭 Modal
        focus: true,         // 初始化时聚焦 Modal
        show: false           // 初始化时不显示 Modal
    });
}

// 显示 Modal 的函数
function showModal(id) {
    console.log("showModal");
    $(id).modal('show');
}

// 隐藏 Modal 的函数
function hideModal(id) {
    $(id).modal('hide');
}