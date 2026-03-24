package thaithienthuan.lab02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thaithienthuan.lab02.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}