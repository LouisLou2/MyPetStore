package org.csu.mypetstore.test;

import org.csu.mypetstore.utils.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestRedis {
    public static void test1(){
        //VerifiyService.SendEmailVerifyCode("865113609@qq.com","1235");
        //Object obj=null;
        //String str=(String)obj;
        //System.out.println(str);
        //String id="ACID";
        //Account account= AccountService.getAccount(id);
        //RedisCache.setAccountModelById(id,account);
        //
        //Map<String,String>map= RedisCache.getAccountModelById(id);
        //Account account1= BeanUtil.INSTANCE.toAccount(map);
        //String insertQuery = "INSERT INTO product (productid, category, name, descn) VALUES (?, 'FISH', ?, ?)";
        //try (PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(insertQuery)) {
        //    for (int i = 10; i <= 30; i++) {
        //        String productCode = "FI-SW-" + String.format("%02d", i);
        //        String name = "Fish " + i;
        //        String description = "<image src=\"../images/fish" + i + ".gif\"> Fish number " + i;
        //
        //        preparedStatement.setString(1, productCode);
        //        preparedStatement.setString(2, name);
        //        preparedStatement.setString(3, description);
        //
        //        preparedStatement.executeUpdate();
        //    }
        //    System.out.println("Inserted 30 records successfully.");
        //} catch (SQLException e) {
        //    throw new RuntimeException(e);
        //}
    }
    public static void test3(){
        try {
            Connection connection = DBUtil.getConnection();
            String selectQuery = "SELECT productId, descn FROM product";
            String updateQuery = "UPDATE product SET descn = ? WHERE productId = ?";
            String updateQuery2 = "UPDATE product SET picture = ? WHERE productId = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            PreparedStatement updateStatement2 = connection.prepareStatement(updateQuery2);
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("productId");
                String originalDescn = resultSet.getString("descn");
                int spl=originalDescn.indexOf('\"');
                String picture = originalDescn.substring(0, spl);
                String descn = originalDescn.substring(spl+2, originalDescn.length());
                updateStatement.setString(1, descn);
                updateStatement.setString(2, id);
                updateStatement2.setString(1, picture);
                updateStatement2.setString(2, id);
                updateStatement.executeUpdate();
                updateStatement2.executeUpdate();
            }

            selectStatement.close();
            updateStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String sql1="insert into item (itemid,productid,listprice,unitcost,supplier,status,attr1) values (?,?,?,?,?,?,?)";
        String sql2="insert into inventory (itemid,qty) values (?,?)";
        Connection connection=DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql1);
            for(int i=100;i<=130;++i){
                preparedStatement.setString(1,"EST-"+i);
                preparedStatement.setString(2,"FI-FW-01");
                preparedStatement.setBigDecimal(3, BigDecimal.valueOf(12.99));
                preparedStatement.setBigDecimal(4, BigDecimal.valueOf(12.99));
                preparedStatement.setInt(5,1);
                preparedStatement.setString(6,"P");
                preparedStatement.setString(7,"Normal");
                preparedStatement.executeUpdate();
            }
            preparedStatement.close();
            PreparedStatement preparedStatement1=connection.prepareStatement(sql2);
            for(int i=100;i<=130;++i){
                preparedStatement1.setString(1,"EST-"+i);
                preparedStatement1.setInt(2,999);
                preparedStatement1.executeUpdate();
            }
            preparedStatement1.close();
            connection.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally{
            DBUtil.closeConnection(connection);
        }
    }
}
