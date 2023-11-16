package org.csu.mypetstore.utils;

import org.csu.mypetstore.constant.AppProperties;

public class URLHelper {
    //from 1
    public static String getLayerFromURL(int layer, String url) {
        String[] split = url.split("/");
        return split[layer+2];
    }
    //from 1
    public static String getLayerFromURI(int layer, String url) {
        String[] split = url.split("/");
        return split[layer];
    }
    public static String getLocationWithRoot(String location) {
        return AppProperties.APP_ROOT+location;
    }
    //这个函数应该传uri
    public static String getFullURL(String path) {
        if(path==null){
            return null;
        }
        if(path.startsWith("http://")||path.startsWith("https://")){
            return path;
        }
        if(path.startsWith(AppProperties.APP_ROOT)) 
            return AppProperties.ADDRESS_AND_PORT + path;
        return AppProperties.APP_ROOT_WITH_ADDRESS_AND_PORT +path;
    }
}
