package org.csu.mypetstore.domain;

import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.csu.mypetstore.constant.enums.ResultCodeEnum;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Setter
@Getter
public class RestResponse {
    byte code;
    String location;
    Map<String,Object>loadings;
    //无参构造函数
    public RestResponse() {
        code= ResultCodeEnum.FAIL;
        location="";
        loadings=null;
    }
    public void insertLoading(String key,Object value){
        if(loadings==null)
            loadings=new HashMap<String,Object>();
        loadings.put(key,value);
    }
    public String ToJsonStr(){
        return JSON.toJSONString(this);
    }
}
