package org.csu.mypetstore.repository;

import com.alibaba.fastjson2.JSON;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import org.csu.mypetstore.constant.enums.DataTypeEnum;
import org.csu.mypetstore.constant.enums.ExpireTime;
import org.csu.mypetstore.constant.enums.MarkerEnum;
import org.csu.mypetstore.service.ExpireTimeManager;
import org.csu.mypetstore.utils.BeanFlattener;
import org.csu.mypetstore.utils.ClassUtil;
import org.csu.mypetstore.utils.RedisUtil;

import java.util.Map;

public class RedisCache {
    public static String getPlainString(String key) {
        StatefulRedisConnection connection = RedisUtil.getConnection();
        RedisCommands rdCommand = connection.sync();
        String res= (String) rdCommand.get(key);
        RedisUtil.closeConnection(connection);
        return res;
    }
    public static <T> void setPlainValue(String key,T obj){
        StatefulRedisConnection connection = RedisUtil.getConnection();
        RedisCommands rdCommand = connection.sync();
        if(ClassUtil.isDirect(obj))
            rdCommand.set(key,obj.toString());
        else{
            rdCommand.set(key,JSON.toJSONString(obj));
        }
        RedisUtil.closeConnection(connection);
    }
    private static <T> void setJavaObject(String key, T t, ExpireTime expireTime) {
        setJavaObject(key, t, expireTime.getTime());
    }
    private static <T> void setJavaObject(String key, T t, int expireTime) {
        //用json序列化
        String json = JSON.toJSONString(t);
        StatefulRedisConnection connection = RedisUtil.getConnection();
        RedisCommands rdCommand = connection.sync();
        rdCommand.set(key, json);
        rdCommand.expire(key, expireTime);
        //关闭连接
        RedisUtil.closeConnection(connection);
    }
    public static void setImageCode(String code, String JSESSIONID) {
        StatefulRedisConnection connection = RedisUtil.getConnection();
        RedisAsyncCommands rdCommand = connection.async();
        String key = RedisUtil.MakeRedisKey(DataTypeEnum.IMAGE_CODE.getType(), MarkerEnum.JSESSIONID.getType(), JSESSIONID);
        rdCommand.set(key, code);
        rdCommand.expire(key, ExpireTimeManager.getPerfectExpireTime(DataTypeEnum.IMAGE_CODE));
        RedisUtil.closeConnection(connection);
    }
    public static void setEmailCode(String code, String email) {
        StatefulRedisConnection connection = RedisUtil.getConnection();
        RedisAsyncCommands rdCommand = connection.async();
        String key = RedisUtil.MakeRedisKey(DataTypeEnum.EMAIL_CODE.getType(), MarkerEnum.EMAIL.getType(), email);
        rdCommand.set(key, code);
        rdCommand.expire(key, ExpireTimeManager.getPerfectExpireTime(DataTypeEnum.EMAIL_CODE));
        RedisUtil.closeConnection(connection);
    }
    public static String getImageCode(String JSESSIONID) {
        StatefulRedisConnection connection = RedisUtil.getConnection();
        RedisCommands rdCommand = connection.sync();
        String key = RedisUtil.MakeRedisKey(DataTypeEnum.IMAGE_CODE.getType(), MarkerEnum.JSESSIONID.getType(), JSESSIONID);
        String res= (String) rdCommand.get(key);
        RedisUtil.closeConnection(connection);
        return res;
    }
    public static String getEmailCode(String email){
        StatefulRedisConnection connection = RedisUtil.getConnection();
        RedisCommands rdCommand = connection.sync();
        String key = RedisUtil.MakeRedisKey(DataTypeEnum.EMAIL_CODE.getType(), MarkerEnum.EMAIL.getType(), email);
        String res= (String) rdCommand.get(key);
        RedisUtil.closeConnection(connection);
        return res;
    }
    
    public static <T> void setModel(String key, T t) {

    }
    //我是真的不明白，在存map时，使用异步命令的时候，不会报错但不会存入redis，使用同步命令直接就报错，所以我只能分开map,一个一个存
    public static void setModelByMap(String key, Map<String, Object> map,int expireTime) {
        StatefulRedisConnection connection = RedisUtil.getConnection();
        RedisCommands rdCommand = connection.sync();
        for(Map.Entry<String,Object> entry:map.entrySet()){
            String valueStr=(ClassUtil.isDirect(entry.getValue()))?entry.getValue().toString():JSON.toJSONString(entry.getValue());
            rdCommand.hset(key, entry.getKey(), valueStr);
        }
        rdCommand.expire(key, expireTime);
        RedisUtil.closeConnection(connection);
    }
    public static <T> void setModelById(String id,MarkerEnum type,T t) {
        int expireTime = ExpireTimeManager.getPerfectExpireTime(DataTypeEnum.MODEL);
        String key = RedisUtil.MakeRedisKey(DataTypeEnum.MODEL.getType(), type.getType(), id);
        Map<String, Object> map;
        try {
            map = BeanFlattener.deepToMap(t);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        setModelByMap(key, map, expireTime);
    }
    public static <T> void setModelFieldById(String id,MarkerEnum type,String field,T value) {
        int expireTime = ExpireTimeManager.getPerfectExpireTime(DataTypeEnum.MODEL);
        StatefulRedisConnection connection = RedisUtil.getConnection();
        RedisCommands rdCommand = connection.sync();
        String key = RedisUtil.MakeRedisKey(DataTypeEnum.MODEL.getType(), type.getType(), id);
        String valueStr=(ClassUtil.isDirect(value))?value.toString():JSON.toJSONString(value);
        rdCommand.hset(key, field, valueStr);
        rdCommand.expire(key, expireTime);
        RedisUtil.closeConnection(connection);
    }
    public static <T> void setAccountModelById(String id,T value) {
        setModelById(id, MarkerEnum.USER_ID, value);
    }
    public static Map<String,String> getModelById(String id,MarkerEnum type) {
        StatefulRedisConnection connection = RedisUtil.getConnection();
        RedisCommands rdCommand = connection.sync();
        String key = RedisUtil.MakeRedisKey(DataTypeEnum.MODEL.getType(), type.getType(), id);
        Map<String,String> res=rdCommand.hgetall(key);
        RedisUtil.closeConnection(connection);
        return res;
    }
    public static String getModelFieldById(String id,MarkerEnum type,String field) {
        StatefulRedisConnection connection = RedisUtil.getConnection();
        RedisCommands rdCommand = connection.sync();
        String key = RedisUtil.MakeRedisKey(DataTypeEnum.MODEL.getType(), type.getType(), id);
        String res= (String) rdCommand.hget(key, field);
        RedisUtil.closeConnection(connection);
        return res;
    }
    public static Map<String,String> getAccountModelById(String id) {
        return getModelById(id, MarkerEnum.USER_ID);
    }
}
