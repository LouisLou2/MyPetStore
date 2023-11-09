package org.csu.mypetstore.utils;

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
}
