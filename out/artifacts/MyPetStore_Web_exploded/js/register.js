var contextPath=document.getElementById("contextPath").innerHTML;
function usernameIsExist() {
    var username = document.getElementById('username').value;
    sendRequest(contextPath+"usernameIsExistServlet?username=" + username);
}

function sendRequest(url) {
    axios.get(url)
        .then(function (response) {
            processResponse(response);
        })
        .catch(function (error) {
            console.error("An error occurred: " + error);
        });
}

function processResponse(response) {
    if (response.status === 200) {
        var xmlResponse = new DOMParser().parseFromString(response.data, "text/xml");
        var responseInfo = xmlResponse.getElementsByTagName("msg")[0].textContent;

        var div1 = document.getElementById('usernameMsg');

        if (responseInfo === "Exist") {
            div1.innerHTML = "<font color='red'>用户名已存在</font>";
        } else {
            div1.innerHTML = "<font color='green'>用户名可用</font>";
        }
    }
}