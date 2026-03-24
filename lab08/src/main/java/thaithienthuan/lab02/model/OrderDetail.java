package thaithienthuan.lab02.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private double price;

    private int quantity; // course có quantity là do đề bài

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}