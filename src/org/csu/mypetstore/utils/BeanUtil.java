package org.csu.mypetstore.utils;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Dto.CartItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Map;

@Mapper
public  interface BeanUtil {
    BeanUtil INSTANCE= Mappers.getMapper(BeanUtil.class);
    Account toAccount(Map<String, String> map);
    @Mapping(source = "cartItem.item.itemId",target = "itemId")
    CartItemDto toCartItemDto(CartItem cartItem);
}