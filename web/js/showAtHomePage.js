let products = null;
// get datas from file json
fetch(getItemsShownAtHomePageUrl)
    .then(response => response.json())
    .then(data => {
        if(data.code !== 0){
            alert(data.loadings.error);
            return;
        }
        products = data.loadings.itemList;
        addDataToHTML();
    })

function addDataToHTML(){
    // remove datas default from HTML
    let listProductHTML = document.querySelector('.listProduct');
    // add new datas
    if(products != null){ // if has data
        products.forEach(product => {
            let newProduct = document.createElement('a');
            newProduct.href = jmpToViewItemUrl + product.itemId;
            newProduct.classList.add('homepageItem');
            newProduct.innerHTML =
                `<img src="${contextPath+product.picture}" alt="">
                <h2>${product.productName}</h2>
                <div class="price">$${product.listPrice}</div>`;
            listProductHTML.appendChild(newProduct);
        });
    }
}