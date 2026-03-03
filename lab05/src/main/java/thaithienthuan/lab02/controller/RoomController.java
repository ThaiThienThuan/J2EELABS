package thaithienthuan.lab02.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import thaithienthuan.lab02.model.Room;
import thaithienthuan.lab02.repository.CategoryRepository;
import thaithienthuan.lab02.service.RoomService;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private CategoryRepository categoryRepository;

    // 1. Hiển thị danh sách phòng
    @GetMapping
    public String listRooms(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "room/list";
    }

    // 2. Hiển thị form thêm phòng mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("categories", categoryRepository.findAll());
        return "room/add";
    }

    // 3. Lưu phòng (xử lý cả thêm mới và cập nhật)
    @PostMapping("/save")
    public String saveRoom(@Valid Room room, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "room/add";
        }
        roomService.saveRoom(room);
        return "redirect:/rooms";
    }

    // 4. Hiển thị form sửa phòng
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Room room = roomService.getRoomById(id);
        if (room != null) {
            model.addAttribute("room", room);
            model.addAttribute("categories", categoryRepository.findAll());
            return "room/add"; // Dùng chung form add để sửa
        }
        return "redirect:/rooms";
    }

    // 5. Xóa phòng
    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable("id") Long id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }
}