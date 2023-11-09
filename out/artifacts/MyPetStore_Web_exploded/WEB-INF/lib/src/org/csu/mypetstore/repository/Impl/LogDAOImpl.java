package org.csu.mypetstore.repository.Impl;

import org.csu.mypetstore.domain.NormalLog;
import org.csu.mypetstore.domain.ShopLog;
import org.csu.mypetstore.repository.LogDAO;
import org.csu.mypetstore.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LogDAOImpl implements LogDAO {

    private static final String insertNormalLog = "insert into normallog(username,ip,time,action) values(?, ?,?,?)";
    private static final String insertShopLog = "insert into shoplog(username,ip,time,action,type,info) values(?, ?,?,?,?,?)";
    public static LogDAOImpl INSTANCE = new LogDAOImpl();
    public static LogDAOImpl getInstance(){
        return INSTANCE;
    }
    public void  insertNomalLog(NormalLog log){
        //生成sql语句
        Connection con= DBUtil.getConnection();
        //执行sql语句
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(insertNormalLog);
            preparedStatement.setString(1, log.getUsername());
            preparedStatement.setString(2, log.getIp());
            preparedStatement.setLong(3, log.getTime());
            preparedStatement.setByte(4, log.getAction());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(con);
        }
    }
    public void insertShopLog(ShopLog log){
        //生成sql语句
        Connection con= DBUtil.getConnection();
        //执行sql语句
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(insertShopLog);
            preparedStatement.setString(1, log.getUsername());
            preparedStatement.setString(2, log.getIp());
            preparedStatement.setLong(3, log.getTime());
            preparedStatement.setByte(4, log.getAction());
            preparedStatement.setString(5, String.valueOf(log.getType()));
            preparedStatement.setString(6, log.getInfo());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(con);
        }
    }
}
