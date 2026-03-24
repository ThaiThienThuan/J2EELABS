package thaithienthuan.lab02.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String image;

    @NotNull
    private Integer credits;

    private String lecturer;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @NotNull
    private Double price;
}