var autocompleteList=null;
function fill_autocomplete(id,keyword){
    if(autocompleteList==null) initAutocompleteList(id);
    close_autocompleteList();
    let url=searchProductBasicInfoUrl;
    axios.get(url,{
        params:{
            keyword:keyword
        }
    }).then(function (response) {
        if(response.data.code==0){
            fill_autocomplete_with_data(id,response.data.loadings['productList']);
        }else{
            alert(response.data.loadings.error);
        }
    }).catch(function (error) {
        console.log(error);
    });
}
function fill_autocomplete_with_data(id,data){
    for(let i=0;i<data.length;++i){
        let item=document.createElement("div");
        item.setAttribute("class","auto_out");
        item.innerHTML="<a href=\""+contextPath+ "/shop/view/product?productId=" + data[i].productId + "\">" + data[i].name + "</a>";
        autocompleteList.appendChild(item);
    }
}
function close_autocompleteList(){
    autocompleteList.innerHTML="";
}
function initAutocompleteList(id){
    autocompleteList=document.getElementById(id);
}