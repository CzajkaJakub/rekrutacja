package pl.akai;

public class Book {
    private final String id;
    private final String title;
    private final String author;
    private final Double rating;

    public Book(String id, String title, String author, Double rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.rating = rating;
    }


    public Double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", rating=" + rating +
                '}';
    }
}
