package org.csu.mypetstore.constant.enums;

import lombok.Getter;
import lombok.Setter;


public enum ErrorTypeEnum {
    //1为空，2为格式错误，3为已存在,4为未知错误
    BLANK(1),
    FORMAT(2),
    EXIST(3),
    UNKNOWN(4);
    private int type;
    ErrorTypeEnum(int i) {
        this.type=i;
    }
}
