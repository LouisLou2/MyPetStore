package org.csu.mypetstore.constant.enums;

public enum ShopLevelEnum {
    CATEGORY((byte)1, "category"),
    PRODUCT((byte)2, "product"),
    ITEM((byte)3, "item");
    private byte level;
    private String levelName;
    ShopLevelEnum(byte level, String levelName){
        this.level = level;
        this.levelName = levelName;
    }
}
