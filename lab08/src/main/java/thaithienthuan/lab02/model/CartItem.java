package thaithienthuan.lab02.model;

import lombok.Data;

@Data
public class CartItem {

    // Course info
    private Long id;
    private String name;
    private String image;
    private Double price;

    // Quantity (course có quantity là do đề bài yêu cầu)
    private int quantity;
}