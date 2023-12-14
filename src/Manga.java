public class Manga {
    private int mangaId;
    private String title;
    private double price;
    private int quantity;
    private String genre;
    private String author;

    public Manga(int mangaId, String title, String author, String genre, double price, int quantity) {
        this.mangaId = mangaId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;
    }

    public int getMangaId() {
        return mangaId;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }
}
