package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.NormalLog;
import org.csu.mypetstore.domain.ShopLog;
import org.csu.mypetstore.repository.Impl.LogDAOImpl;
import org.csu.mypetstore.repository.LogDAO;

public class LogService {
    private static LogDAO logDAO;
    static {
        logDAO = LogDAOImpl.getInstance();
    }
    public static void InsertNomalLog(NormalLog log){
        logDAO.insertNomalLog(log);
    }
    public static void InsertShopLog(ShopLog log){
        logDAO.insertShopLog(log);
    }
}
