package org.csu.mypetstore.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@AllArgsConstructor
@Setter
@Getter
public class SimpleItem {
    String itemId;
    String productName;
    String picture;
    int quantity;
    BigDecimal listPrice;
    String description;
}
