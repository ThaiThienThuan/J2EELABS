package thaithienthuan.lab02.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Account student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "enroll_date")
    private LocalDate enrollDate;
}