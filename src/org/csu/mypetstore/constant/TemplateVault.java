package org.csu.mypetstore.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TemplateVault {
    FOlDER((byte) 0,"/templates"),
    EMAIL_VERIFY((byte) 1,"/verification_code.html"),
    PHONE_VERIFY((byte) 2,"/verification_code.html");
    private byte type;
    private String location;
    public String getAbsolutePath(){
        return LocationEnum.CLASSPATH+FOlDER.getLocation()+location;
    }
}
