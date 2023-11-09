package org.csu.mypetstore.service;

import org.csu.mypetstore.constant.enums.DataTypeEnum;
import org.csu.mypetstore.constant.enums.ExpireTime;

import java.util.concurrent.ConcurrentHashMap;

public class ExpireTimeManager {
    private static ConcurrentHashMap<Byte,Integer> expireTimeMap = new ConcurrentHashMap<>();
    static{
        expireTimeMap.put(DataTypeEnum.IMAGE_CODE.getType(), ExpireTime.TEN_MINUTE.getTime());
        expireTimeMap.put(DataTypeEnum.SMS_CODE.getType(), ExpireTime.THREE_MINUTE.getTime());
        expireTimeMap.put(DataTypeEnum.EMAIL_CODE.getType(), ExpireTime.FIVE_MINUTE.getTime());
        expireTimeMap.put(DataTypeEnum.MODEL.getType(), ExpireTime.ONE_HOUR.getTime());
    }
    public static int getPerfectExpireTime(byte type) {
        return expireTimeMap.get(type);
    }
    public static int getPerfectExpireTime(DataTypeEnum dataTypeEnum) {
        return expireTimeMap.get(dataTypeEnum.getType());
    }
}
