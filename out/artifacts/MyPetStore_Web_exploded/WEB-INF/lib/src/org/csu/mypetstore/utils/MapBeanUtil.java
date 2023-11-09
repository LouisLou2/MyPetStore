package org.csu.mypetstore.utils;

import org.csu.mypetstore.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Map;

@Mapper
public  interface MapBeanUtil {
    MapBeanUtil INSTANCE= Mappers.getMapper(MapBeanUtil.class);
    Account toAccount(Map<String, String> map);
}
