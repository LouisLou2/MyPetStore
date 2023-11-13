package org.csu.mypetstore.constant;

public interface AppProperties {
    //应用名称
    String APP_NAME = "MyPetStore";
    String APP_ROOT= "/MyPetStore_Web";
    String MAIN_SERVLET = "/main";
    String APP_ADDRESS="localhost";
    String APP_PORT="8080";
    String APP_ROOT_WITH_ADDRESS_AND_PORT="http://"+APP_ADDRESS+":"+APP_PORT+APP_ROOT;
}
