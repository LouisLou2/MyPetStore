package org.csu.mypetstore.constant;

import java.net.URL;

public class LocationEnum {
    public static String CLASSPATH;
    static{
        ClassLoader classLoader = LocationEnum.class.getClassLoader();
        URL classesURL = classLoader.getResource("");
        CLASSPATH = classesURL.getPath();
        CLASSPATH=CLASSPATH.substring(0,CLASSPATH.length()-1);
    }
}
