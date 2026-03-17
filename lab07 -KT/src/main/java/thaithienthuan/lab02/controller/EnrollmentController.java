package thaithienthuan.lab02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import thaithienthuan.lab02.model.*;
import thaithienthuan.lab02.repository.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/enroll")
public class EnrollmentController {

@Autowired
private EnrollmentRepository enrollmentRepository;

@Autowired
private AccountRepository accountRepository;

@Autowired
private CourseRepository courseRepository;

@GetMapping("/{courseId}")
public String enrollCourse(@PathVariable Long courseId, Principal principal){

    Account student = accountRepository.findByUsername(principal.getName()).orElse(null);
    Course course = courseRepository.findById(courseId).orElse(null);

    if (student != null && course != null) {
        boolean alreadyEnrolled = enrollmentRepository.findByStudent(student)
                .stream()
                .anyMatch(e -> e.getCourse().getId().equals(courseId));

        if (!alreadyEnrolled) {
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            enrollment.setEnrollDate(LocalDate.now());

            enrollmentRepository.save(enrollment);
        }
    }

    return "redirect:/enroll/my-courses";
}

@GetMapping("/my-courses")
public String myCourses(Model model, Principal principal){

    Account student = accountRepository
            .findByUsername(principal.getName())
            .orElse(null);

    List<Enrollment> enrollments =
            enrollmentRepository.findByStudent(student);

    model.addAttribute("enrollments", enrollments);

    return "my-courses";
}

}
