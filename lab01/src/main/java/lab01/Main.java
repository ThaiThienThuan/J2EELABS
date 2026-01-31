package lab01;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner x = new Scanner(System.in);

        String msg = """
            Chuong trinh quan ly sach 
                1. Them 1 cuon sach
                2. Xoa 1 cuon sach
                3. Thay doi sach
                4. Xuat thong tin
                5. Tim sach Lap trinh
                6. Lay sach toi da theo gia
                7. Tim kiem theo tac gia
                0. Thoat
                Chon chuc nang:""";

        int chon = 0;

        do {
            System.out.print(msg);
            chon = x.nextInt();
            switch (chon) {
                case 1 -> {
                    Book newBook = new Book();
                    newBook.input();
                    listBook.add(newBook);
                }
                case 2 -> {
                    System.out.print("Nhap vao ma sach can xoa: ");
                    int bookId = x.nextInt();

                    Book find = listBook.stream().filter(p -> p.getId() == bookId).findFirst().orElseThrow();
                    listBook.remove(find);
                    System.out.print("Đã xóa sách thành công");
                }
                case 3 -> {
                    System.out.print("Nhập vào ma sách cần điều chỉnh:");
                    int bookid = x.nextInt();
                    Book find = listBook.stream().filter(p -> p.getId() == bookid).findFirst().orElseThrow();
                    System.out.println("--- Nhập thông tin mới cho sách ---");
                    find.input();
                }
                case 4 -> {
                    System.out.println("\n--- danh sach ---");
                    listBook.forEach(p -> p.output());
                }
                case 5 -> {
                    List<Book> list5 = listBook.stream()
                            .filter(u -> u.getTitle().toLowerCase().contains("lap trinh"))
                            .toList();
                    list5.forEach(Book::output);
                }
                case 6 -> {
                    System.out.println("\n--- Sach co gia cao nhat ---");
                    listBook.stream()
                            .sorted(Comparator.comparingDouble(Book::getPrice).reversed())
                            .limit(1)
                            .forEach(Book::output);
                }
                case 7 -> {
                    x.nextLine(); // Clear buffer
                    System.out.print("Nhap ten tac gia can tim: ");
                    String tacGiaTim = x.nextLine().toLowerCase();

                    Set<Book> foundSet = listBook.stream()
                            .filter(b -> b.getAuthor().toLowerCase().contains(tacGiaTim))
                            .collect(Collectors.toSet());

                    if (foundSet.isEmpty()) {
                        System.out.println("Khong tim thay tac gia nay.");
                    } else {
                        foundSet.forEach(Book::output);
                    }
                }
            }
        } while (chon != 0);
    }
}