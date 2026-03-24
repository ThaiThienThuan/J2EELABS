package thaithienthuan.lab02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thaithienthuan.lab02.model.*;
import thaithienthuan.lab02.repository.*;
import java.time.LocalDate;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    // Thêm course vào cart
    public void addToCart(List<CartItem> cart, Course course) {
        if (cart == null)
            return;
        boolean found = false;
        for (CartItem item : cart) {
            if (item.getId().equals(course.getId())) {
                item.setQuantity(item.getQuantity() + 1);
                found = true;
                break;
            }
        }

        if (!found) {
            CartItem item = new CartItem();
            item.setId(course.getId());
            item.setName(course.getName());
            item.setImage(course.getImage());
            item.setPrice(course.getPrice());
            item.setQuantity(1);
            cart.add(item);
        }
    }

    // Tính tổng tiền
    public double calculateTotal(List<CartItem> cart) {
        if (cart == null)
            return 0;
        return cart.stream()
                .mapToDouble(ci -> ci.getPrice() * ci.getQuantity())
                .sum();
    }

    // Xóa course khỏi cart
    public void removeFromCart(List<CartItem> cart, Long courseId) {
        if (cart != null) {
            cart.removeIf(item -> item.getId().equals(courseId));
        }
    }

    // Checkout: tạo Order + OrderDetail + xóa cart
    public Order checkout(List<CartItem> cartItems, Account account) {
        if (cartItems == null || cartItems.isEmpty())
            return null;

        Order order = new Order();
        order.setAccount(account);
        order.setOrderDate(LocalDate.now());
        order.setPaid(true);

        double totalAmount = calculateTotal(cartItems);
        order.setTotalAmount(totalAmount);

        order = orderRepository.save(order);

        for (CartItem ci : cartItems) {
            Course course = courseRepository.findById(ci.getId()).orElse(null);
            if (course != null) {
                OrderDetail detail = new OrderDetail();
                detail.setOrder(order);
                detail.setCourse(course); 
                detail.setQuantity(ci.getQuantity());
                detail.setPrice(ci.getPrice());
                orderDetailRepository.save(detail);
            }
        }

        cartItems.clear();
        return order;
    }
}