package org.csu.mypetstore.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExpireTime {
    // 一分钟
    ONE_MINUTE(60),
    // 三分钟
    THREE_MINUTE(60 * 3),
    // 五分钟
    FIVE_MINUTE(60 * 5),
    // 十分钟
    TEN_MINUTE(60 * 10),
    // 三十分钟
    THIRTY_MINUTE(60 * 30),
    // 一小时
    ONE_HOUR(60 * 60),
    // 一天
    ONE_DAY(60 * 60 * 24),
    // 一周
    ONE_WEEK(60 * 60 * 24 * 7),
    // 一个月
    ONE_MONTH(60 * 60 * 24 * 30),
    // 一年
    ONE_YEAR(60 * 60 * 24 * 365),
    // 永久
    PERMANENT(-1);

    private int time;
    public int getTime() {
        return time;
    }
}
