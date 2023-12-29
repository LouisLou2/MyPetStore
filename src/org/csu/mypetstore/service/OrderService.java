package org.csu.mypetstore.service;

import org.csu.mypetstore.constant.enums.PageCapacityEnum;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.domain.Sequence;
import org.csu.mypetstore.repository.Impl.ItemDAOImpl;
import org.csu.mypetstore.repository.Impl.LineItemDAOImpl;
import org.csu.mypetstore.repository.Impl.OrderDAOImpl;
import org.csu.mypetstore.repository.Impl.SequenceDAOImpl;
import org.csu.mypetstore.repository.ItemDAO;
import org.csu.mypetstore.repository.LineItemDAO;
import org.csu.mypetstore.repository.OrderDAO;
import org.csu.mypetstore.repository.SequenceDAO;

import java.util.List;

public class OrderService {

  private static ItemDAO itemDAO;
  private static OrderDAO orderDAO;
  private static SequenceDAO sequenceDAO;
  private static LineItemDAO lineItemDAO;

  static{
    itemDAO= ItemDAOImpl.INSTANCE;
    orderDAO= OrderDAOImpl.INSTANCE;
    sequenceDAO= SequenceDAOImpl.INSTANCE;
    lineItemDAO= LineItemDAOImpl.INSTANCE;
  }
  
    public static int getTotalPage(String username){
        int totalRecord = orderDAO.getRecordCount(username);
        return  totalRecord% PageCapacityEnum.ORDER ==0?totalRecord/PageCapacityEnum.ORDER:totalRecord/PageCapacityEnum.ORDER+1;
    }
  public static String insertOrder(Order order) {
    int orderId = getNextId("ordernum");
    order.setOrderId(orderId);
    for (int i = 0; i < order.getLineItems().size(); i++) {
      LineItem lineItem = (LineItem) order.getLineItems().get(i);
      String itemId = lineItem.getItemId();
      Integer increment = lineItem.getQuantity();
      itemDAO.decrementInventoryQuantity(itemId, increment);
    }
    orderDAO.insertOrder(order);
    orderDAO.insertOrderStatus(order);
    for (int i = 0; i < order.getLineItems().size(); i++) {
      LineItem lineItem = (LineItem) order.getLineItems().get(i);
      lineItem.setOrderId(order.getOrderId());
      lineItemDAO.insertLineItem(lineItem);
    }
    return String.valueOf(orderId);
  }

  public static Order getOrder(int orderId) {
    Order order = orderDAO.getOrder(orderId);
    if(order == null) return null;
    order.setLineItems(lineItemDAO.getLineItemsByOrderId(orderId));
    for (int i = 0; i < order.getLineItems().size(); i++) {
      LineItem lineItem = (LineItem) order.getLineItems().get(i);
      Item item = itemDAO.getItem(lineItem.getItemId());
      item.setQuantity(itemDAO.getInventoryQuantity(lineItem.getItemId()));
      lineItem.setItem(item);
    }
    return order;
  }

  public static List<Order> getOrdersByUsername(String username) {
    return orderDAO.getOrdersByUsername(username);
  }
  public static List<Order> getOrdersByUsernameWithPage(String username, int page) {
    return orderDAO.getOrdersByUsernameWithPage(username, page);
  }
  public static int getNextId(String name) {
    Sequence sequence = new Sequence(name, -1);
    sequence = sequenceDAO.getSequence(sequence);
    if (sequence == null) {
      throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
          + " sequence).");
    }
    Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
    sequenceDAO.updateSequence(parameterObject);
    return sequence.getNextId();
  }

}
