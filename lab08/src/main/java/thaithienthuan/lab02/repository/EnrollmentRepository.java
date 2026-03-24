package thaithienthuan.lab02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thaithienthuan.lab02.model.Enrollment;
import thaithienthuan.lab02.model.Account;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

List<Enrollment> findByStudent(Account student);


}
