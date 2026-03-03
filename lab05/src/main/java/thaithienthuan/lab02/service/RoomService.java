package thaithienthuan.lab02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thaithienthuan.lab02.model.Room;
import thaithienthuan.lab02.repository.RoomRepository;
import java.util.List;

@Service
public class RoomService {
    @Autowired private RoomRepository roomRepository;

    public List<Room> getAllRooms() { return roomRepository.findAll(); }
    public void saveRoom(Room room) { roomRepository.save(room); }
    public Room getRoomById(Long id) { return roomRepository.findById(id).orElse(null); }
    public void deleteRoom(Long id) { roomRepository.deleteById(id); }
}