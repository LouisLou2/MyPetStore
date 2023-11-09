package org.csu.mypetstore.constant.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public enum ErrorEnum {
    ;
    
    public enum VerificationError{
        IMAGECODE(1,"验证码错误或已过期"),
        EMAILCODE(2,"邮箱验证码错误或已过期"),
        PHONECODE(3,"手机验证码错误"),
        NOTMATCH(4,"用户名或密码错误"),
        NOTEXIST(5,"用户不存在");
            
        private int code;
        private String message;
        VerificationError(int i, String s) {
            this.code=i;
            this.message=s;
        }
        public String getMessage(){
            return this.message;
        }
    }
    
    //1为空，2为格式错误，3为已存在,4为未知错误
    public enum AccountPara{
        USERNAME(1,"用户名",2),//1为空，2为格式错误，3为已存在,4为未知错误
        PASSWORD(2,"密码",2),//1为空，2为格式错误
        REPASSWORD(3,"确认密码",2),//1为空，2为格式错误
        EMAIL(4,"邮箱",2),//1为空，2为格式错误
        FIRSTNAME(5,"姓",1),//1为空
        LASTNAME(6,"名",1),//1为空
        STATUS(7,"状态",1),//1为空
        ADDRESS1(8,"地址1",1),//1为空
        ADDRESS2(9,"地址2",1),//1为空
        CITY(10,"城市",1),//1为空
        STATE(11,"省份",1),//1为空
        ZIP(12,"邮编",1),//1为空
        COUNTRY(13,"国家",1),//1为空
        PHONE(14,"电话",1),//1为空
        FAVCATEGORY(15,"喜欢的类别",1),//1为空
        LANGPREF(16,"语言偏好",1),//1为空
        FAVCATEGORY1(17,"喜欢的类别1",1),//1为空
        FAVCATEGORY2(18,"喜欢的类别2",1),//1为空
        FAVCATEGORY3(19,"喜欢的类别3",1),//1为空
        FAVCATEGORY4(20,"喜欢的类别4",1),//1为空
        BANNERNAME(21,"横幅名",1),//1为空
        BANNERNAME1(22,"横幅名1",1);//1为空
        private int field;
        private String fieldname;
        private int errortype;
        AccountPara(int i, String name, int i1) {
            this.field=i;
            this.fieldname=name;
            this.errortype=i1;
        }
        public String getErrorInfo(){
            String errorinfo="";
            switch (this.errortype){
                case 1:
                    errorinfo="不能为空";
                    break;
                case 2:
                    errorinfo="格式错误";
                    break;
                case 3:
                    errorinfo="已存在";
                    break;
            }
            return this.fieldname+errorinfo;
        }

        public void setErrortype(int errortype) {
            this.errortype = errortype;
        }
    }
}
