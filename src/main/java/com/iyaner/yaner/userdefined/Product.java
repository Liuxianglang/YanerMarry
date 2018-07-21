package com.iyaner.yaner.userdefined;

import com.iyaner.yaner.userdefined.annotation.Column;
import com.iyaner.yaner.userdefined.annotation.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Table("my_product")
@Data
public class Product {

    @Column("pro_id")
    private String productId;
    @Column("product_name")
    private String productName;
    @Column("price")
    private Double price;
    @Column("counts")
    private Integer countNum;

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", countNum=" + countNum +
                '}';
    }
}
