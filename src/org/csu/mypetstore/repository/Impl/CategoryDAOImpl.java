package org.csu.mypetstore.repository.Impl;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.repository.CategoryDAO;
import org.csu.mypetstore.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    public static CategoryDAOImpl INSTANCE;
    private static final String GET_CATEGORY_LIST = "SELECT CATID AS categoryId, NAME, DESCN AS description FROM CATEGORY";
    private static final String GET_CATEGORY = "SELECT CATID AS categoryId, NAME, DESCN AS description FROM CATEGORY WHERE CATID = ?";
    static{
        INSTANCE = new CategoryDAOImpl();
    }
    @Override
    public List<Category> getCategoryList() {
        List<Category> categoryList = new ArrayList<Category>();
        try{
            //1.获得连接
            Connection connection = DBUtil.getConnection();
            //2.准备sql语句

            //3.执行对象
            PreparedStatement pStatement = connection.prepareStatement(GET_CATEGORY_LIST);
            //4.结果回收
            ResultSet resultSet = pStatement.executeQuery();
            //
            while (resultSet.next()){
                Category category = new Category();
                category.setCategoryId(resultSet.getString(1));
                category.setName(resultSet.getString(2));
                category.setDescription(resultSet.getString(3));

                categoryList.add(category);
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
        return categoryList;
    }

    @Override
    public Category getCategory(String categoryId) {
        Category category = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_CATEGORY);
            pStatement.setString(1, categoryId);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()){
                category = new Category();
                category.setCategoryId(resultSet.getString(1));
                category.setName(resultSet.getString(2));
                category.setDescription(resultSet.getString(3));
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

        return category;
    }
}
