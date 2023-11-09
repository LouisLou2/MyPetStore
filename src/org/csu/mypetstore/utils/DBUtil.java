package org.csu.mypetstore.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static Properties pro=null;
    private static DataSource dataSource = null;
    private static String propertiesName = "druid.properties";
    static{
        Properties pro=new Properties();
        try {
            pro.load(DBUtil.class.getClassLoader().getResourceAsStream(propertiesName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            dataSource= DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() {
        Connection con=null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
    public static void closeConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void closeStatement(Statement statement){
        if(statement != null){
            try {
                statement.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void closePreparedStatement(PreparedStatement preparedStatement){
        if(preparedStatement != null){
            try {
                preparedStatement.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void closeResultSet(ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
