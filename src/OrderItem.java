import java.util.ArrayList;
import java.util.List;

public class OrderItem {
    private int mangaId;
    private String title;
    private String author;
    private String genre;
    private double price;
    private int quantity;

    public OrderItem(int mangaId, String title, String author, String genre, double price, int quantity) {
        this.mangaId = mangaId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;
    }

    // Getter and setter methods for the OrderItem properties
    public int getMangaId() {
        return mangaId;
    }

    public void setMangaId(int mangaId) {
        this.mangaId = mangaId;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
