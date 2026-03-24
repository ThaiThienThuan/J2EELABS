package thaithienthuan.lab02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import thaithienthuan.lab02.model.*;
import thaithienthuan.lab02.repository.*;
import thaithienthuan.lab02.service.CartService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/add/{courseId}")
    public String addToCart(@PathVariable Long courseId, HttpSession session) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) return "redirect:/home";

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();

        cartService.addToCart(cart, course);

        session.setAttribute("cart", cart);
        return "redirect:/cart/list";
    }

    @GetMapping("/list")
    public String viewCart(Model model, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();

        double total = cartService.calculateTotal(cart);
        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        return "cart/list"; // list.html
    }

    @GetMapping("/remove/{courseId}")
    public String removeFromCart(@PathVariable Long courseId, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        cartService.removeFromCart(cart, courseId);
        session.setAttribute("cart", cart);
        return "redirect:/cart/list";
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Principal principal) {
        Account student = accountRepository.findByUsername(principal.getName()).orElse(null);
        if (student == null) return "redirect:/login";

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart != null && !cart.isEmpty()) {
            cartService.checkout(cart, student);
        }

        session.removeAttribute("cart");
        return "cart/success"; // trang checkout thành công
    }
}