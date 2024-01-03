function throttle(func,time){
    console.log("throttle invoked");
    var timer=null;
    return function(){
        console.log("inner func invoked");
        var context=this;
        var args=arguments;
        if(!timer){
            timer=setTimeout(function(){
                func.apply(context,args);
                timer=null;
            },time);
        }
    }
}