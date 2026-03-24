package thaithienthuan.lab02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thaithienthuan.lab02.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}