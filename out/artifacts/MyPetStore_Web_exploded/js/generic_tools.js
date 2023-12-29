function throttle(func,time){
    var timer=null;
    return function(){
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