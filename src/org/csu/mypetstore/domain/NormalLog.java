package org.csu.mypetstore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NormalLog {
    protected String username;
    protected String ip;
    protected long time;
    protected byte action;
    public NormalLog(){
        username = "";
        ip = "";
        time = 0;
        action = 0;
        time=System.currentTimeMillis();
    }
}
