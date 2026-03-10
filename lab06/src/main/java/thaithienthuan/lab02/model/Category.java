package thaithienthuan.lab02.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Tên loại phòng không được để trống")
    @Column(nullable = false, length = 255)
    private String name;
}