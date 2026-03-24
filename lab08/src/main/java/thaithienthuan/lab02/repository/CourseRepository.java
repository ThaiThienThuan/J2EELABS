package thaithienthuan.lab02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thaithienthuan.lab02.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.data.domain.Sort;
public interface CourseRepository extends JpaRepository<Course, Long> {

List<Course> findByNameContainingIgnoreCase(String keyword);
List<Course> findAll(Sort sort);
Page<Course> findByCategoryId(Long categoryId, Pageable pageable);

}
