package thaithienthuan.lab02.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên phòng không được để trống")
    @Column(nullable = false, length = 255)
    private String name;

    @NotNull(message = "Giá thuê không được để trống")
    @Min(value = 1, message = "Giá không được nhỏ hơn 1")
    private long price;

    @Length(max = 200, message = "Link ảnh không quá 200 kí tự")
    @Column(length = 200)
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}