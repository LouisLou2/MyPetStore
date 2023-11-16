//remove item from cart
function removeItemFromCart(tableId,itemId,trEle) {
    console.log("removeItemFromCart");
    //先发axios请求，再删除
    axios.get(removeItemFromCartUrl,{
        params:{
            itemId:itemId
        }
    }).then(function (response) {
        if(response.data.code==0){
            console.log(response.data);
            alert("删除成功");
            //删除表格中的行
            console.log(trEle);
            let table = document.getElementById(tableId);
            table.removeChild(trEle);
            console.log("trEle removed")
        }else{
            alert("删除失败");
        }
    }).catch(function (error) {
        console.log(error);
    });
}
function updateCart(therow){
    console.log(therow);
    console.log(therow.cells[4]);
    console.log(therow.cells[4].firstElementChild);
    console.log(therow.cells[4].firstElementChild.value);
    //获取当前行的商品数量
    let quantity = therow.cells[4].firstElementChild.value;
    if(quantity<1){
        alert("数量不能小于1");
        return;
    }
    //获取当前行的商品id
    let itemId = therow.cells[1].firstElementChild.innerText;
    //保存现在的总价
    var totalPriceEle = therow.cells[6];
    let oriTotalPriceStr = totalPriceEle.innerText;
    let oriTotalPrice = parseFloat(oriTotalPriceStr.replace("$",""));
    //得到购物车总价值的Ele
    let subTotalEle = document.getElementById("subtotal");
    //先发axios请求，再更新
    console.log(itemId);
    console.log(quantity);
    axios.get(updateCartUrl,{
        params:{
            itemId:itemId,
            quantity:quantity
        }
    }).then(function (response) {
        if(response.data.code==0){
            let totalPriceStr = "$"+response.data.loadings.totalPrice;
            let totalPrice = parseFloat(totalPriceStr.replace("$",""));
            //更新总价
            totalPriceEle.innerText = totalPriceStr;
            //更新购物车总价值
            let subTotal = parseFloat(subTotalEle.innerText.replace("$",""));
            let newSubTotal = subTotal - oriTotalPrice + totalPrice;
            subTotalEle.innerText = "$"+newSubTotal;
        }else{
            alert(response.data.loadings.error);
        }
    }).catch(function (error) {
        console.log(error);
    });
    
}