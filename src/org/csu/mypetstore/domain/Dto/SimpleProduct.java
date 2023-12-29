package org.csu.mypetstore.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class SimpleProduct {
    String productId;
    String productName;
    String picture;
}
