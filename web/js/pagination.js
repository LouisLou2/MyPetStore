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
functionValt["updateCatagoryList"]=updateCatagoryList;
functionValt["updateProductList"]=updateProductList;
function initPaginationRow(arequestUrl,apreparedParams,atableId,aupdateFunc,totalpage,acurrentPage,) {
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
    preparedParams["page"]=currentPage;
    updateFunc(requestUrl,preparedParams);
}
function updateCatagoryList(url,reqParam){
    axios.request({
        url: url,
        method: 'get',
        params: reqParam
    }).then(function (response) {
        if(response.data.loadings.productList==null){
            return;
        }
        //更新列表
        let table=document.getElementById(tableId);
        table.innerHTML="";
        // 创建表头行
        var tableHeaderRow = document.createElement("tr");
        // 创建表头单元格
        var Header1 = document.createElement("th");
        Header1.textContent = "Product ID";
        var Header2 = document.createElement("th");
        Header2.textContent = "Name";
        // 将表头单元格添加到表头行
        tableHeaderRow.appendChild(Header1);
        tableHeaderRow.appendChild(Header2);
        // 将表头行添加到表格
        table.appendChild(tableHeaderRow);
        for(let i=0;i<response.data.loadings.productList.length;++i){
            var product = response.data.loadings.productList[i];
            // 创建新的<tr>元素
            var row = document.createElement("tr");

            // 创建<td>元素并填充数据
            var productIdCell = document.createElement("td");
            var productLink = document.createElement("a");
            productLink.href = contextPath+"/shop/view/product?productId=" + product.productId;
            productLink.textContent = product.productId;
            productIdCell.appendChild(productLink);

            var categoryNameCell = document.createElement("td");
            categoryNameCell.textContent = product.name;

            // 将<td>元素添加到<tr>元素
            row.appendChild(productIdCell);
            row.appendChild(categoryNameCell);
            // 将<tr>元素添加到表格
            table.appendChild(row);
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
        //更新列表
        //删除除了表头的所有行
        let table=document.getElementById(tableId);
        for(let i=1;i<table.rows.length;++i){
            table.deleteRow(i);
        }
        for(let i=0;i<items.length;++i){
            let item = items[i];
            let attributeStr="";
            for(let j=1;j<=5;++j){
                let attr=item["attribute"+j];
                if(attr===null||attr===undefined)continue;
                attributeStr+=attr+" ";
            }
            attributeStr+=item.product.name;
            // 创建新的<tr>元素
            let row = table.insertRow(i+1);
            let cell1=row.insertCell(0);
            let cell2=row.insertCell(1);
            let cell3=row.insertCell(2);
            let cell4=row.insertCell(3);
            let cell5=row.insertCell(4);
            cell1.innerHTML = "<a href=\""+contextPath+ "/shop/view/item?itemId=" + item.itemId + "\">" + item.itemId + "</a>";
            cell2.textContent = item.product.productId;
            cell3.textContent = attributeStr;
            cell4.textContent = '$' + item.listPrice;
            cell5.innerHTML = '<a class=\"Button\" href=\"' +contextPath+ "/shop/addcart/item?workingItemId=" + item.itemId + '\">Add to Cart</a>';
        }
    }).catch(function (error) {
        console.error("An error occurred: " + error);
    });
}
