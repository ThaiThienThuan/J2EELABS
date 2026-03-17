package thaithienthuan.lab02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thaithienthuan.lab02.model.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

List<Course> findByNameContainingIgnoreCase(String keyword);

}
