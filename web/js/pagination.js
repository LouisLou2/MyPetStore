let currentPage=1;
let totalPage=-1;
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
function initPaginationRow(arequestUrl,atableId,aupdateFunc,totalpage,acurrentPage,apreparedParams=null){
    console.log("initPaginationRow");
    updateFunc=functionValt[aupdateFunc];
    totalPage=parseInt(totalpage);
    currentPage=parseInt(acurrentPage);
    tableId=atableId;
    preparedParams=apreparedParams;
    requestUrl=arequestUrl;
    let li = document.createElement("li");
    //添加class
    li.setAttribute("class", "button");
    li.innerHTML="Prev";
    li.addEventListener("click",handleClick)
    ul.appendChild(li);
    for(let i=1;i<=totalPage;++i){
        li = document.createElement("li");
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
    li = document.createElement("li");
    //添加class
    li.innerHTML="Last";
    ul.appendChild(li);
    li.addEventListener("click",handleClick);
    li = document.createElement("li");
    //添加class
    li.setAttribute("class", "button");
    li.innerHTML="Next";
    li.addEventListener("click",handleClick)
    ul.appendChild(li);
    //设置第一页被选中
    const lis = ul.getElementsByTagName("li");
    lis[currentPage].setAttribute("class","current");
    originClicked=lis[currentPage];
}

function handleClick(event){
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
    if(currentPage<1||currentPage>totalPage||currentPage===originPage){
        return;
    }
    //更新页码
    originPage=currentPage;
    //图标变化
    let theli=ul.children[currentPage];
    if(!isNaN(theli.innerHTML)){
        console.log("isnum:"+ul.children[currentPage].innerHTML);
        theli.classList.add("current");
    }else{
        console.log("notnum:"+ul.children[currentPage].innerHTML);
    }
    if(originClicked!=null){
        //删除原来的current
        if(originClicked.classList.contains("current")){
            originClicked.classList.remove("current");
        }
    }
    console.log("origin:"+originClicked.innerHTML);
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
        console.log("get orderList");
        console.log(items);
        //更新列表
        //删除除了表头的所有行
        let table=document.getElementById(tableId);
        console.log("table.rows.length:"+table.rows.length);
        while(table.rows.length>1){
            table.deleteRow(1);
        }
        for(let i=0;i<items.length;++i){
            let item = items[i];
            let row=table.insertRow(i+1);
            let cell1=row.insertCell(0);
            let cell2=row.insertCell(1);
            let cell3=row.insertCell(2);
            cell1.innerHTML = "<a href=\""+contextPath+ "/shop/view/order?orderId=" + item.orderId + "\">" + item.orderId + "</a>";
            cell2.textContent = new Date(item.orderDate).toLocaleString();
            cell3.textContent = '$' + item.totalPrice;
        }
    }).catch(function (error) {
        console.error("An error occurred: " + error);
    });
}