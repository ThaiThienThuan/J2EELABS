package thaithienthuan.lab02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import thaithienthuan.lab02.model.Course;
import thaithienthuan.lab02.repository.CourseRepository;

import java.util.List;

@Service
public class CourseService {

@Autowired
private CourseRepository courseRepository;

public Page<Course> getCoursesPage(int page) {
    return courseRepository.findAll(PageRequest.of(page, 3));
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
