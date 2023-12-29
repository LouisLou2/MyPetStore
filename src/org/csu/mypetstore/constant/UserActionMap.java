package org.csu.mypetstore.constant;

import java.util.concurrent.ConcurrentHashMap;

public class UserActionMap {
    private static ConcurrentHashMap<String, Byte> map = new ConcurrentHashMap<String, Byte>();
    private static ConcurrentHashMap<String,Byte> typemap = new ConcurrentHashMap<>();
    static{
        map.put("signin", (byte)1);
        map.put("signout", (byte)2);
        map.put("register", (byte)3);
        map.put("update", (byte)4);
        map.put("view", (byte)5);
        map.put("buy", (byte)6);
        map.put("addcart", (byte)7);
        map.put("removecart", (byte)8);
        map.put("search", (byte)9);
        map.put("viewcart", (byte)10);
        map.put("vieworder", (byte)11);
        map.put("confirm", (byte)12);
        
        typemap.put("category", (byte)1);
        typemap.put("product", (byte)2);
        typemap.put("item", (byte)3);
    }
    public static byte getActionCode(String key){
        if(map.get(key)==null) return -1;
        return map.get(key);
    }
    public static byte getTypeCode(String key){
        if(typemap.get(key)==null) return -1;
        return typemap.get(key);
    }
}
