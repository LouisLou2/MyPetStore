//悬浮窗JS
let itemCards = document.getElementsByClassName("homepageItem");
let floatingBox = document.getElementById("floatingBox");
for(let card of itemCards){
    card.addEventListener("mouseenter", function(e) {
        let cardRect = e.target.getBoundingClientRect();
        let floatingBoxLeft = cardRect.right + 10;
        let floatingBoxTop = cardRect.top;
        floatingBox.style.position = "fixed";
        floatingBox.style.left = floatingBoxLeft + "px";
        floatingBox.style.top = floatingBoxTop + "px";
        floatingBox.style.display = "block";
        //开始进行box信息的填充
        let id=card.querySelector(".id").innerText;
        let name=card.querySelector(".name").innerText;
        let price=card.querySelector(".price").innerText;
        let picture=card.querySelector(".pic").src;
        floatingBox.querySelector("#id").innerText=id;
        floatingBox.querySelector("#name").innerText=name;
        floatingBox.querySelector("#price").innerText=price;
        floatingBox.querySelector("#pic").src=picture;
        //发送ajax请求
        axios.get(getSimpleItemUrl,{
            params:{
                itemId:id
            }
        }).then(function (response) {
            if(response.data.code==0){
                let item=response.data.loadings.item;
                floatingBox.querySelector("#description").innerText=item.description;
                floatingBox.querySelector("#quantity").innerText=item.quantity;
                floatingBox.querySelector("#loader").style.display="none";
                floatingBox.querySelector("#boxContent").style.display="block";
            }else{
                alert(response.data.loadings.error);
            }
        }).catch(function (error) {
            console.log(error);
        });
    });
    card.addEventListener("mouseleave", function() {
        floatingBox.style.display = "none";
        floatingBox.querySelector("#loader").style.display="block";
        floatingBox.querySelector("#boxContent").style.display="none";
    });
}