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