package org.csu.mypetstore.constant.enums;

public interface ResultCodeEnum {
    byte SUCCESS = 0;
    byte FAIL = 1;
    byte ERROR = 2;
    byte UNAUTHORIZED = 3;
    byte NOT_FOUND = 4;
    byte FORBIDDEN = 5;
    byte CONFLICT = 6;
    byte INTERNAL_SERVER_ERROR = 7;
    byte BAD_GATEWAY = 8;
    byte SERVICE_UNAVAILABLE = 9;
}
