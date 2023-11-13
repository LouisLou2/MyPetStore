package org.csu.mypetstore.service;

import java.util.HashSet;
import java.util.Set;

//判断资源类型
//静态资源
//传统servelt
//restful接口
public class ResourceManager {
    private static final String RestfulServlet="/rest";
    private static Set<String> staticResourceSuffix;
    static{
        staticResourceSuffix=new HashSet<>();
        staticResourceSuffix.add(".css");
        staticResourceSuffix.add(".js");
        staticResourceSuffix.add(".jpg");
        staticResourceSuffix.add(".jpeg");
        staticResourceSuffix.add(".html");
        staticResourceSuffix.add(".png");
        staticResourceSuffix.add(".ico");
        staticResourceSuffix.add(".gif");
    }
    public static boolean isStaticResource(String url){
        int index=url.lastIndexOf('.');
        if(index==-1){
            return false;
        }
        String end=url.substring(index);
        if(staticResourceSuffix.contains(end)){
            return true;
        }
        return false;
    }
    public static boolean isTraditionalServlet(String url){
        return !isRestfulServlet(url)&&!isStaticResource(url);
    }
    public static boolean isRestfulServlet(String url){
        int index1=url.indexOf("/");
        int index2=url.indexOf('/',index1+1);
        if(index2==-1) return false;
        String firstPart=url.substring(index2, index2+RestfulServlet.length());
        return firstPart.equals(RestfulServlet);
    }
}
