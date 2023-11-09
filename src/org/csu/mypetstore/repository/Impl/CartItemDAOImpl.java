package org.csu.mypetstore.repository.Impl;

import org.csu.mypetstore.constant.enums.PageCapacityEnum;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.repository.CartItemDAO;
import org.csu.mypetstore.repository.ItemDAO;
import org.csu.mypetstore.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class CartItemDAOImpl implements CartItemDAO {
    private static final String GET_CART_ITEM_LIST_WITH_PAGE = "SELECT ITEMID, QUANTITY FROM CART WHERE USERNAME = ? LIMIT ?, ?";
    private static final String INSERT_CART_ITEM = "INSERT INTO CART (USERNAME, ITEMID, QUANTITY) VALUES (?, ?, ?)";
    private static final String UPDATE_CART_ITEM = "UPDATE CART SET QUANTITY = ? WHERE USERNAME = ? AND ITEMID = ?";
    private static final String DELETE_CART_ITEM = "DELETE FROM CART WHERE USERNAME = ? AND ITEMID = ?";
    private static final String DELETE_CART_ITEM_BY_USERNAME = "DELETE FROM CART WHERE USERNAME = ?";
    private static final String GET_CART_ITEM_COUNT = "SELECT COUNT(1) AS COUNT FROM CART WHERE USERNAME = ?";
    public static CartItemDAOImpl INSTANCE;
    public static ItemDAO itemDAO;
    static{
        INSTANCE = new CartItemDAOImpl();
        itemDAO = ItemDAOImpl.INSTANCE;
    }
    @Override
    public List<CartItem> getCartItemListWithPage(String username,int page) {
        Connection con=DBUtil.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<CartItem> cartItemList=null;
        String itemId=null;
        Item item=null;
        int quantity=0;
        try{
            ps=con.prepareStatement(GET_CART_ITEM_LIST_WITH_PAGE);
            ps.setString(1,username);
            ps.setInt(2,(page-1)* PageCapacityEnum.CART);
            ps.setInt(3,PageCapacityEnum.CART);
            rs=ps.executeQuery();
            while(rs.next()){
                CartItem cartItem=new CartItem();
                itemId=rs.getString(1);
                quantity=rs.getInt(2);
                item=itemDAO.getItem(itemId);
                cartItem.setQuantity(quantity);
                cartItem.setItem(itemDAO.getItem(itemId));
                if(item.getQuantity()>quantity) {
                    cartItem.setInStock(true);
                }else{
                    cartItem.setInStock(false);
                }
                cartItemList.add(cartItem);
            }
        }catch(Exception e){
            cartItemList=null;
        }
        return cartItemList;
    }

    @Override
    public int getCartItemCount(String username) {
        Connection con=DBUtil.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        int count=0;
        try{
            ps=con.prepareStatement(GET_CART_ITEM_COUNT);
            ps.setString(1,username);
            rs=ps.executeQuery();
            if(rs.next()){
                count=rs.getInt(1);
            }
        }catch(Exception e){
            count=0;
        }
        return count;
    }
}
