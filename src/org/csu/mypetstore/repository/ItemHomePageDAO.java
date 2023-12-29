package org.csu.mypetstore.repository;

import java.util.List;

public interface ItemHomePageDAO {
    int getItemCount();
    List<String> getItemIdList();
}
