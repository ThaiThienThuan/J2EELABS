package thaithienthuan.lab02.repository;
import thaithienthuan.lab02.model.Account;
import java.util.Optional;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    //@Query("SELECT a FROM Account a WHERE a.login_name = :login_name ")
    Optional<Account> findByLoginName(String login_name);
}
