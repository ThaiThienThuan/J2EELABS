package thaithienthuan.lab02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import thaithienthuan.lab02.model.Course;
import thaithienthuan.lab02.repository.CourseRepository;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Pagination + Sort
    public Page<Course> getCoursesPage(Long categoryId, int page, String sortType) {
        int pageSize = 5;
        PageRequest pageRequest;

        if (sortType == null || sortType.equals("default")) {
            pageRequest = PageRequest.of(page, pageSize);
        } else if (sortType.equals("price_asc")) {
            pageRequest = PageRequest.of(page, pageSize, Sort.by("price").ascending());
        } else if (sortType.equals("price_desc")) {
            pageRequest = PageRequest.of(page, pageSize, Sort.by("price").descending());
        } else {
            pageRequest = PageRequest.of(page, pageSize);
        }
        if (categoryId == null || categoryId == 0) {
        // nếu không chọn category, trả về tất cả
        return courseRepository.findAll(pageRequest);
        }
        return courseRepository.findByCategoryId(categoryId, pageRequest);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public List<Course> searchCourse(String keyword) {
        return courseRepository.findByNameContainingIgnoreCase(keyword);
    }
}