package org.csu.mypetstore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class ShopLog extends NormalLog {
    //浏览的是category,product还是item
    private byte type;
    //id值
    private String info;

    public ShopLog(String username, String ip, long time, byte action) {
        super(username, ip, time, action);
    }
    public ShopLog(){
        super();
        type=-1;
        info="";
    }
}
