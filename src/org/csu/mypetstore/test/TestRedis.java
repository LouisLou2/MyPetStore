package org.csu.mypetstore.test;

import org.csu.mypetstore.utils.DBUtil;

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
        //Account account1= MapBeanUtil.INSTANCE.toAccount(map);
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
    public static void main(String[] args) {
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
}
