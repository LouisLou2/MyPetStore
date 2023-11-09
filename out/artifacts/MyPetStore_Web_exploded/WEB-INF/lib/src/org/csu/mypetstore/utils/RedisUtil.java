package org.csu.mypetstore.utils;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import org.csu.mypetstore.constant.TemplateVault;
import org.csu.mypetstore.constant.enums.DataTypeEnum;
import org.csu.mypetstore.constant.enums.MarkerEnum;

import java.time.Duration;
import java.util.Properties;

public class RedisUtil {
    private static RedisURI redisURI=null;
    private static Properties pro=null;
    private static RedisClient redisClient=null;
    private static final String seperator=":";
    static{
        pro=new Properties();
        try {
            pro.load(RedisUtil.class.getClassLoader().getResourceAsStream("redis.properties"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        redisURI=RedisURI.builder()
                .withHost(pro.getProperty("host"))
                .withPort(Integer.parseInt(pro.getProperty("port")))
                .withDatabase(Integer.parseInt(pro.getProperty("database")))
                .withTimeout(Duration.ofMillis(3000)) // 设置连接超时时间为 3000 毫秒
                .build();
        redisClient=RedisClient.create(redisURI);
    }
    // 获取连接
    public static StatefulRedisConnection getConnection(){
        return redisClient.connect();
    }
    // 关闭连接
    public static void closeConnection(StatefulRedisConnection connection){
        if(connection != null){
            connection.close();
        }
    }
    // 关闭客户端
    public static void closeClient(){
        if(redisClient != null){
            redisClient.shutdown();
        }
    }
    // 关闭连接和客户端
    public static void close(StatefulRedisConnection connection){
        closeConnection(connection);
        closeClient();
    }
    public static String MakeRedisKey(String dataType, String marker, String markerValue,String... args){
        StringBuilder sb = new StringBuilder();
        sb.append(dataType);
        sb.append(seperator);
        sb.append(marker);
        sb.append(seperator);
        sb.append(markerValue);
        for(String arg : args){
            sb.append(seperator);
            sb.append(arg);
        }
        return sb.toString();
    }
    public static String MakeRedisKey(DataTypeEnum dataType, MarkerEnum marker, String markerValue,String... args){
        StringBuilder sb = new StringBuilder();
        sb.append(dataType.getType());
        sb.append(seperator);
        sb.append(marker.getType());
        sb.append(seperator);
        sb.append(markerValue);
        for(String arg : args){
            sb.append(seperator);
            sb.append(arg);
        }
        return sb.toString();
    }
    public static String MakeRedisKey(byte type, byte type1, String jsessionid, String... args) {
        //转化为字符串
        String dataType = String.valueOf(type);
        String marker = String.valueOf(type1);
        return MakeRedisKey(dataType, marker, jsessionid);
    }
    public static String MakeTemplateKeyByName(TemplateVault tplkind){
        return MakeRedisKey(DataTypeEnum.TEMPLATE, MarkerEnum.TEMPLATETYPE, String.valueOf(tplkind.getType()));
    }
}