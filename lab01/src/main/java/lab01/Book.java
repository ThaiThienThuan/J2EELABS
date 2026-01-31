
package lab01;

import java.util.Scanner;

public class Book {
    private int id;
    private String title;
    private String author;
    private double price;

    public Book() {
    }

    public Book(int id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", author=" + author + ", price=" + price + '}';
    }
    public void input() {
        Scanner x = new Scanner(System.in);

        System.out.print("id: ");
        this.id = Integer.parseInt(x.nextLine());

        System.out.print("title: ");
        this.title = x.nextLine();

        System.out.print("author: ");
        this.author = x.nextLine();

        System.out.print("price: ");
        this.price = x.nextDouble();
    }

    public void output() {
    String msg = """
        BOOK: id=%d, title=%s, author=%s, price=%f""".formatted(id, title, author, price);
    System.out.println(msg);
}

}

