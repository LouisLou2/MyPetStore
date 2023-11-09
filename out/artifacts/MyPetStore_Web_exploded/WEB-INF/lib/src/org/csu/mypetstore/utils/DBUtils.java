package org.csu.mypetstore.utils;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.Properties;

public class DBUtils {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String ADDRESS = "jdbc:mysql://localhost:3306";
    private static final String DBNAME = "mypetstore";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "lou..200499";
    private static Driver dr= null;
    private static Properties pro=null;
    static{
        try{
            dr= new Driver();
        }catch( SQLException e) {
            e.printStackTrace();
        }
        pro=new Properties();
        pro.setProperty("user",USERNAME);
        pro.setProperty("password",PASSWORD);
    }
    public static Connection getConnection() {
        Connection con;
        try {
            con = dr.connect(ADDRESS + '/' + DBNAME, pro);
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