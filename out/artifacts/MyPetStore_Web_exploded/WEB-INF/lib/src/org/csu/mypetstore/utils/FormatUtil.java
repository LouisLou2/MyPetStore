package org.csu.mypetstore.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatUtil {
    private static final String PHONE_REGEX="^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
    private static final String EMAIL_REGEX="[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";
    private static Pattern phonePattern;
    private static Pattern emailPattern;
    static{
        phonePattern=Pattern.compile(PHONE_REGEX);
        emailPattern=Pattern.compile(EMAIL_REGEX);
    }
    public static boolean checkPhone(String phone){
        Matcher matcher=phonePattern.matcher(phone);
        return matcher.matches();
    }
    public static boolean checkEmail(String email){
        Matcher matcher=emailPattern.matcher(email);
        return matcher.matches();
    }
}
