package thaithienthuan.lab02.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import thaithienthuan.lab02.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {}