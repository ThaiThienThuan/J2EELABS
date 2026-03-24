package thaithienthuan.lab02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thaithienthuan.lab02.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
