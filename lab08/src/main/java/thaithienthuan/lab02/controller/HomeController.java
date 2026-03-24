package thaithienthuan.lab02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import thaithienthuan.lab02.model.Course;
import thaithienthuan.lab02.service.CategoryService;
import thaithienthuan.lab02.service.CourseService;

@Controller
public class HomeController {

@Autowired
private CourseService courseService;
@Autowired
private CategoryService categoryService;

@GetMapping({"/", "/home"})
public String home(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "default") String sort,
        @RequestParam(required = false, defaultValue = "0") Long categoryId,
        Model model){

    Page<Course> coursePage = courseService.getCoursesPage(categoryId, page, sort);
    model.addAttribute("courses", coursePage.getContent());
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", coursePage.getTotalPages());
    model.addAttribute("sort", sort);       
     model.addAttribute("categoryId", categoryId);
     model.addAttribute("categories", categoryService.getAllCategories()); 
    return "index";
}

}
