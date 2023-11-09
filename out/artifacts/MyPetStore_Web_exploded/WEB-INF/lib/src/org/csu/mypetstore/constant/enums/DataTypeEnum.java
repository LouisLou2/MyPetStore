package org.csu.mypetstore.constant.enums;

import lombok.Getter;

@Getter
public enum DataTypeEnum {
    //针对不同的存储数据类型，设置不同的过期时间
    //图片验证码
    IMAGE_CODE((byte) 1),
    //短信验证码
    SMS_CODE((byte) 2),
    //邮箱验证码
    EMAIL_CODE((byte) 3),
    //model信息
    MODEL((byte) 4),
    //模版文件
    TEMPLATE((byte) 5),
    ;
    private byte type;
    DataTypeEnum(byte type) {
        this.type = type;
    }
}
