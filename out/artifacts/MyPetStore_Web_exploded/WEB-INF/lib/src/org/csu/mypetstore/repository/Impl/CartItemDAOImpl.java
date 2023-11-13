package org.csu.mypetstore.repository.Impl;

import org.csu.mypetstore.constant.enums.PageCapacityEnum;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Dto.CartItemDto;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.repository.CartItemDAO;
import org.csu.mypetstore.repository.ItemDAO;
import org.csu.mypetstore.utils.BeanUtil;
import org.csu.mypetstore.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAOImpl implements CartItemDAO {
    private static final String GET_CART_ITEM_LIST = "SELECT ITEMID, QUANTITY FROM CARTITEM WHERE USERNAME = ?";
    private static final String GET_CART_ITEM_LIST_WITH_PAGE = "SELECT ITEMID, QUANTITY FROM CARTITEM WHERE USERNAME = ? LIMIT ?, ?";
    private static final String INSERT_CART_ITEM = "INSERT INTO CARTITEM ( ITEMID, QUANTITY,USERNAME,TIME) VALUES (?, ?, ?,?)";
    private static final String UPDATE_QUANTITY_CART_ITEM = "UPDATE CARTITEM SET QUANTITY = ? WHERE USERNAME = ? AND ITEMID = ?";
    private static final String INCREMENT_QUANTITY_CART_ITEM = "UPDATE CARTITEM SET QUANTITY = QUANTITY + ? WHERE USERNAME = ? AND ITEMID = ?";
    private static final String DELETE_CART_ITEM = "DELETE FROM CARTITEM WHERE USERNAME = ? AND ITEMID = ?";
    private static final String CONTAINS_ITEM = "SELECT IFNULL((SELECT 1 FROM CARTITEM WHERE USERNAME = ? AND ITEMID = ? LIMIT 1), 0)";
    private static final String GET_CART_ITEM_COUNT = "SELECT COUNT(1) AS COUNT FROM CARTITEM WHERE USERNAME = ?";
    private static final String INSERT_OR_UPDATE_CART_ITEM = "INSERT INTO CARTITEM ( ITEMID, QUANTITY,USERNAME,TIME) VALUES (?, ?, ?,?) ON DUPLICATE KEY UPDATE QUANTITY = QUANTITY + ?";
    
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
        List<CartItem> cartItemList=new ArrayList<>();
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
                cartItem.setItem(item);
                if(item.getQuantity()>quantity) {
                    cartItem.setInStock(true);
                }else{
                    cartItem.setInStock(false);
                }
                cartItemList.add(cartItem);
            }
        }catch(Exception e){
            cartItemList=null;
        }finally{
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(con);
        }
        return cartItemList;
    }

    @Override
    public List<CartItem> getCartItemList(String username) {
        Connection con=DBUtil.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<CartItem> cartItemList=new ArrayList<>();
        String itemId=null;
        Item item=null;
        int quantity=0;
        try{
            ps=con.prepareStatement(GET_CART_ITEM_LIST);
            ps.setString(1,username);
            rs=ps.executeQuery();
            while(rs.next()){
                CartItem cartItem=new CartItem();
                itemId=rs.getString(1);
                quantity=rs.getInt(2);
                item=itemDAO.getItem(itemId);
                cartItem.setQuantity(quantity);
                cartItem.setItem(item);
                if(item.getQuantity()>quantity) {
                    cartItem.setInStock(true);
                }else{
                    cartItem.setInStock(false);
                }
                cartItemList.add(cartItem);
            }
        }catch(Exception e){
            cartItemList=null;
        }finally{
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(con);
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
        }finally{
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(con);
        }
        return count;
    }

    @Override
    public void updateQuantity(String username, String itemId, int quantity) {
        Connection con=DBUtil.getConnection();
        PreparedStatement ps=null;
        try{
            if(quantity==0){
                ps=con.prepareStatement(DELETE_CART_ITEM);
                ps.setString(1,username);
                ps.setString(2,itemId);
                ps.executeUpdate();
            }else{
                ps=con.prepareStatement(UPDATE_QUANTITY_CART_ITEM);
                ps.setInt(1,quantity);
                ps.setString(2,username);
                ps.setString(3,itemId);
                ps.executeUpdate();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(con);
        }
    }

    @Override
    public boolean containsItem(String username, String itemId) {
        Connection con=DBUtil.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            ps=con.prepareStatement(CONTAINS_ITEM);
            ps.setString(1,username);
            ps.setString(2,itemId);
            rs=ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1)>0;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(con);
        }
        return false;
    }

    @Override
    public void insertItem(String usernamem, CartItem cartItem) {
        insertItem(usernamem, BeanUtil.INSTANCE.toCartItemDto(cartItem));
    }
    @Override
    public void insertItem(String username, CartItemDto cartItemDto) {
        Connection con=DBUtil.getConnection();
        PreparedStatement ps=null;
        try{
            ps=con.prepareStatement(INSERT_CART_ITEM);
            ps.setString(1,cartItemDto.getItemId());
            ps.setInt(2,cartItemDto.getQuantity());
            ps.setString(3,username);
            ps.setLong(4,cartItemDto.getTime());
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(con);
        }
    }

    @Override
    public void SmartAddCartItem(String username, CartItemDto cartItemDto) {
        Connection con=DBUtil.getConnection();
        PreparedStatement ps=null;
        try{
            ps=con.prepareStatement(CONTAINS_ITEM);
            ps.setString(1,username);
            ps.setString(2,cartItemDto.getItemId());
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                if(rs.getInt(1)>0){
                    ps=con.prepareStatement(INCREMENT_QUANTITY_CART_ITEM);
                    ps.setInt(1,cartItemDto.getQuantity());
                    ps.setString(2,username);
                    ps.setString(3,cartItemDto.getItemId());
                    ps.executeUpdate();
                }else{
                    ps=con.prepareStatement(INSERT_CART_ITEM);
                    ps.setString(1,cartItemDto.getItemId());
                    ps.setInt(2,cartItemDto.getQuantity());
                    ps.setString(3,username);
                    ps.setLong(4,cartItemDto.getTime());
                    ps.executeUpdate();
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(con);
        }
    }
    @Override
    public void deleteItem(String username, String itemId) {
        Connection con=DBUtil.getConnection();
        PreparedStatement ps=null;
        try{
            ps=con.prepareStatement(DELETE_CART_ITEM);
            ps.setString(1,username);
            ps.setString(2,itemId);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(con);
        }
    }
}
