let interval = 5000;
let index = 0; //定义初始下标
let banner = document.getElementsByClassName("banner")[0];
let itemList = banner.querySelectorAll(".item");
let pagination = banner.querySelector(".pagination");
let paginationList = pagination.querySelectorAll("div");
itemList[0].style.opacity = 1;
paginationList[0].style.background = "blue" //初始第一张图对应的小圆点的颜色为蓝色
var up = document.getElementsByClassName("div1")[0];
var next = document.getElementsByClassName("div2")[0];
//下一张图片（封装方便下面自动播放定时器调用）
function showPic(index){
    itemList[index].style.transition = "opacity 1s ease 0.2s" //增加过渡效果
    itemList[index].style.opacity = 1;//图片透明度为1图片显现
    paginationList[index].style.background = "blue"//图片显现小圆点的颜色为蓝色
}
function nextPic() {
    index = (index >= itemList.length - 1) ? 0 : ++index; //判断点击到了最后一张图片再次点击返回到第一张图
    for (let i = 0; i < itemList.length; i++) {
        itemList[i].style.opacity = 0; //图片透明度为0图片隐藏
        paginationList[i].style.background = "white " //图片没显现小圆点的颜色为白色
    }
    showPic(index);
}
function prePic(){
    index = (index <= 0) ? itemList.length - 1 : --index; //判断点击到了第一张图片再次点击返回到最后一张图
    for (let i = 0; i < itemList.length; i++) {
        itemList[i].style.opacity = 0;
        paginationList[i].style.background = "white"
    }
    showPic(index);
}
next.onclick = nextPic; //下一张（点击事件）点击切换下一张图片
up.onclick = prePic;
//设置定时器，自动向下播放图片
let t1 = setInterval(nextPic, interval)
banner.onmouseover = function () {//用户鼠标悬停表示正在浏览，不要再切换
    clearInterval(t1);
}
banner.onmouseout = function () {//鼠标移出则继续自动轮播
    t1 = setInterval(nextPic, interval)
}
// 事件委托
pagination.onclick = function (event) {
    event = window.event || event
    console.log(event);
    if (event.target.className === "pagination") {
        console.log("点击的是父元素");
    } else {
        let id = event.target.id;
        let photoIndex = null;
        for (let i = 0; i < paginationList.length; i++) {
            paginationList[i].style.background = "white"
            if (id.indexOf(i) >= 0) {
                photoIndex = i;
            }
        }
        event.target.style.background = "blue"
        index = photoIndex; // 将小圆点对应的下标与图片下标对应
        for (let i = 0; i < itemList.length; i++) {
            itemList[i].style.opacity = 0;
        }
        itemList[index].style.transition = "opacity 1s ease 0.2s"
        itemList[photoIndex].style.opacity = 1;
    }
}