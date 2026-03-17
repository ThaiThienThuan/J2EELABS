package thaithienthuan.lab02.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import thaithienthuan.lab02.model.Course;
import thaithienthuan.lab02.repository.CategoryRepository;
import thaithienthuan.lab02.service.CourseService;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/search")
public String searchCourse(
        @RequestParam(required = false) String keyword,
        @RequestParam(defaultValue = "0") int page,
        Model model) {

    if (keyword == null || keyword.trim().isEmpty()) {
        return "redirect:/home";
    }

    model.addAttribute("courses", courseService.searchCourse(keyword));
    model.addAttribute("currentPage", 0);
    model.addAttribute("totalPages", 1);

    return "index";
}

    @GetMapping("/admin/courses/add")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("categories", categoryRepository.findAll());
        return "course/add";
    }

    @PostMapping("/admin/courses/save")
    public String saveCourse(@Valid Course course, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "course/add";
        }
        courseService.saveCourse(course);
        return "redirect:/home";
    }

    @GetMapping("/admin/courses/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        model.addAttribute("categories", categoryRepository.findAll());
        return "course/add";
    }

    @GetMapping("/admin/courses/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "redirect:/home";
    }

}
