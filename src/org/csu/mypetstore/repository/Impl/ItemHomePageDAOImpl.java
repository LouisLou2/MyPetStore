package org.csu.mypetstore.repository.Impl;

import org.csu.mypetstore.repository.ItemHomePageDAO;
import org.csu.mypetstore.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ItemHomePageDAOImpl implements ItemHomePageDAO {
    public static final int PAGE_SIZE = 12;
    public static final ItemHomePageDAOImpl INSTANCE = new ItemHomePageDAOImpl();
    public static final String GET_ITEM_ID_LIST = "SELECT ID FROM ITEM_HOME_PAGE LIMIT ?";
    public static final String GET_ITEM_COUNT = "SELECT COUNT(1) AS COUNT FROM ITEM_HOME_PAGE";
    private ItemHomePageDAOImpl(){}
    @Override
    public int getItemCount() {
        int count = 0;
        try{
            //1.获得连接
            Connection connection = DBUtil.getConnection();
            //3.执行对象
            PreparedStatement pStatement = connection.prepareStatement(GET_ITEM_COUNT );
            //4.结果回收
            ResultSet resultSet = pStatement.executeQuery();
            //
            while (resultSet.next()){
                count=resultSet.getInt(1);
            }
            //关闭结果集
            DBUtil.closeResultSet(resultSet);
            //关闭执行对象
            DBUtil.closePreparedStatement(pStatement);
            //关闭连接
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<String> getItemIdList() {
        List<String> itemIdList = new ArrayList<>();
        try{
            //1.获得连接
            Connection connection = DBUtil.getConnection();
            //3.执行对象
            PreparedStatement pStatement = connection.prepareStatement(GET_ITEM_ID_LIST);
            pStatement.setInt(1, PAGE_SIZE);
            //4.结果回收
            ResultSet resultSet = pStatement.executeQuery();
            //
            while (resultSet.next()){
                String itemId = resultSet.getString(1);
                itemIdList.add(itemId);
            }
            //关闭结果集
            DBUtil.closeResultSet(resultSet);
            //关闭执行对象
            DBUtil.closePreparedStatement(pStatement);
            //关闭连接
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return itemIdList;
    }
}
