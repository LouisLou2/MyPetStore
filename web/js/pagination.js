var currentPage=1;
var totalPage=-1;
var ul=document.getElementById("catalogul");;
var originClicked;
var originPage=1;
var tableId;
var preparedParams;
var requestUrl;
var updateFunc;
var contextPath=document.getElementById("contextPath").innerHTML;
var functionValt={};
functionValt["updateCategoryList"]=updateCategoryList;
functionValt["updateProductList"]=updateProductList;
functionValt["updateOrderList"]=updateOrderList;

var orderElementClone;
function initPaginationRow(arequestUrl,atableId,aupdateFunc,totalpage,acurrentPage,apreparedParams=null){
    console.log("initPaginationRow");
    updateFunc=functionValt[aupdateFunc];
    totalPage=parseInt(totalpage);
    currentPage=parseInt(acurrentPage);
    tableId=atableId;
    preparedParams=apreparedParams;
    requestUrl=arequestUrl;
    orderElementClone=document.getElementById(tableId).firstElementChild.cloneNode(true);
    let prevli = document.createElement("li");
    //添加class
    prevli.setAttribute("class", "button");
    prevli.innerHTML="Prev";
    prevli.addEventListener("click",handleClick)
    ul.appendChild(prevli);
    for(let i=1;i<=totalPage;++i){
        let li = document.createElement("li");
        li.addEventListener("click",handleClick);
        if(i==5&&totalpage>=10){
            li.innerHTML = "...";
            ul.appendChild(li);
            i=totalpage-3;
            continue;
        }
        li.innerHTML = i.toString();
        ul.appendChild(li);
    }
    //添加末页按钮
    let lastli = document.createElement("li");
    //添加class
    lastli.innerHTML="Last";
    ul.appendChild(lastli);
    lastli.addEventListener("click",handleClick);
    lastli = document.createElement("li");
    //添加class
    lastli.setAttribute("class", "button");
    lastli.innerHTML="Next";
    lastli.addEventListener("click",handleClick)
    ul.appendChild(lastli);
    //设置第一页被选中
    currentPage=1;
    const lis = ul.getElementsByTagName("li");
    lis[currentPage].setAttribute("class","current");
    originClicked=lis[currentPage];
}

function handleClick(event){
    console.log("handleClick");
    var clickedElement = event.target;
    if(clickedElement.innerHTML=="..."||clickedElement.classList.contains("current")){
        return;
    }
    if(clickedElement.innerHTML=="Next"){
        currentPage+=1;
    }else if(clickedElement.innerHTML=="Prev"){
        currentPage-=1;
    }else if(clickedElement.innerHTML=="Last"){
        currentPage=totalPage;
    }
    else{
        currentPage=parseInt(this.innerHTML);
    }
    console.log("currentPage:"+currentPage);
    if(currentPage<1||currentPage>totalPage||currentPage===originPage){
        console.log("currentPage:"+currentPage+" originPage:"+originPage);
        currentPage=originPage;
        return;
    }
    //更新页码
    originPage=currentPage;
    //图标变化
    let theli=null;
    if(totalPage<10||(totalPage>=10&&currentPage<=4)){
        theli=ul.children[currentPage];
    }else if(totalPage>=10&&currentPage>totalPage-3){
        theli=ul.children[currentPage-totalPage+8];
    }else{
        theli=null;
    }
    if(theli!==null&&!isNaN(parseInt(theli.innerHTML))){
        console.log("num:"+ul.children[currentPage].innerHTML);
        theli.classList.add("current");
    }
    if(originClicked!=null){
        //如果是null，说明在...的位置，不需要删除
        //删除原来的current
        if(originClicked.classList.contains("current")){
            originClicked.classList.remove("current");
        }
    }
    originClicked=theli;
    //发送请求
    if(preparedParams==null) preparedParams={};
    preparedParams["page"]=currentPage;
    updateFunc(requestUrl,preparedParams);
}
function updateCategoryList(url,reqParam){
    axios.request({
        url: url,
        method: 'get',
        params: reqParam
    }).then(function (response) {
        if(response.data.loadings.productList==null){
            return;
        }
        let table=document.getElementById(tableId);
        table.textContent="";
        let products=response.data.loadings.productList;
        console.log(products);
        for(let aproduct of products){
            let li=document.createElement("li");
            li.setAttribute("class","media");
            let img=document.createElement("img");
            img.setAttribute("class","mr-3");
            img.setAttribute("src",contextPath+aproduct.picture);
            img.setAttribute("alt","image");
            li.appendChild(img);
            let div=document.createElement("div");
            div.setAttribute("class","media-body");
            let h5=document.createElement("h5");
            h5.setAttribute("class","mt-0 mb-1");
            let a=document.createElement("a");
            a.setAttribute("href",contextPath+"/shop/view/product?productId="+aproduct.productId);
            a.textContent=aproduct.productId;
            h5.appendChild(a);
            div.appendChild(h5);
            let nameStr=document.createElement("strong");
            nameStr.textContent=aproduct.name;
            div.appendChild(nameStr);
            let p=document.createElement("p");
            p.textContent=aproduct.description;
            p.style['padding']="0";
            div.appendChild(p);
            li.appendChild(div);
            table.appendChild(li);
        }
    }).catch(function (error) {
        console.error("An error occurred: " + error);
    });
}
function updateProductList(url,reqParam){
    axios.request({
        url: url,
        method: 'get',
        params: reqParam
    }).then(function (response) {
        let items=response.data.loadings.itemList;
        if(items==null){
            return;
        }
        console.log("get itemList");
        console.log(items);
        //更新列表
        //删除除了表头的所有行
        let table=document.getElementById(tableId);
        let tableBody=table.getElementsByTagName("tbody")[0];
        //清空tbody
        tableBody.textContent="";
        for(let item of items){
            let attributeStr="";
            for(let j=1;j<=5;++j){
                let attr=item["attribute"+j];
                if(attr===null||attr===undefined)continue;
                attributeStr+=attr+" ";
            }
            attributeStr+=item.product.name;
            // 创建新的<tr>元素
            let row = tableBody.insertRow();
            let cell1=row.insertCell(0);
            let cell2=row.insertCell(1);
            let cell3=row.insertCell(2);
            let cell4=row.insertCell(3);
            cell1.innerHTML = "<a href=\""+contextPath+ "/shop/view/item?itemId=" + item.itemId + "\">" + item.itemId + "</a>";
            cell2.textContent = item.product.productId;
            cell3.textContent = attributeStr;
            cell4.textContent = '$' + item.listPrice;
        }
    }).catch(function (error) {
        console.error("An error occurred: " + error);
    });
}
function updateOrderList(url,reqParam){
    axios.request({
        url: url,
        method: 'get',
        params: reqParam
    }).then(function (response) {
        let items=response.data.loadings.orderList;
        if(items==null){
            return;
        }
        let orderInfoList=document.getElementById(tableId);
        let orderId_a_list=orderInfoList.querySelectorAll('[name="orderId"]');
        let orderDate_h6_list=orderInfoList.querySelectorAll('[name="orderDate"]');
        let orderTotal_strong_list=orderInfoList.querySelectorAll('[name="totalPrice"]');
        let i=0;
        while(i<orderDate_h6_list.length&&i<items.length){
            orderId_a_list[i].textContent=items[i].orderId;
            orderId_a_list[i].href=contextPath+"/shop/view/order?orderId="+items[i].orderId;
            orderDate_h6_list[i].textContent=items[i].orderDate;
            orderTotal_strong_list[i].textContent='$'+items[i].totalPrice;
            ++i;
        }
        if(i<orderDate_h6_list.length) {
            //删除多余的
            while (i < orderDate_h6_list.length) {
                let targetdiv=orderDate_h6_list[i].parentElement.parentElement.parentElement.parentElement;
                orderInfoList.removeChild(targetdiv);
                ++i;
            }
            return;
        }
        while(i<items.length){
            //添加新的
            let newOrderElement=orderElementClone.cloneNode(true);
            newOrderElement.querySelector('[name="orderId"]').textContent=items[i].orderId;
            newOrderElement.querySelector('[name="orderId"]').href=contextPath+"/shop/view/order?orderId="+items[i].orderId;
            newOrderElement.querySelector('[name="orderDate"]').textContent=items[i].orderDate;
            newOrderElement.querySelector('[name="totalPrice"]').textContent='$'+items[i].totalPrice;
            orderInfoList.appendChild(newOrderElement);
            ++i;
        }
    }).catch(function (error) {
        console.error("An error occurred: " + error);
    });
}