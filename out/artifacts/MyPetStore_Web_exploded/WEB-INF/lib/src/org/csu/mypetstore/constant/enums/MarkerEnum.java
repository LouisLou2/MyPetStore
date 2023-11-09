package org.csu.mypetstore.constant.enums;

import lombok.Getter;

//身份标识类型
@Getter
public enum MarkerEnum {
    //JSESSIONID
    JSESSIONID((byte) 1),
    //token
    TOKEN((byte) 2),
    //userId
    USER_ID((byte) 3),
    TEMPLATETYPE((byte) 4),
    EMAIL((byte) 5),
    ;
    private byte type;
    MarkerEnum(byte b) {
        this.type = b;
    }
}
