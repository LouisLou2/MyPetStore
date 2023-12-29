package org.csu.mypetstore.repository;

import org.csu.mypetstore.domain.NormalLog;
import org.csu.mypetstore.domain.ShopLog;

public interface LogDAO {
    void  insertNomalLog(NormalLog log);
    void  insertShopLog(ShopLog log);
}
