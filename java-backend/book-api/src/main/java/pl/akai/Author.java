package pl.akai;


import java.util.ArrayList;
import java.util.List;

public class Author implements Comparable<Author> {
    private double rating;
    private final int amountOfBooks;
    private final String name;
    private final List<Book> books;

    public Author(String name, Book book) {
        books = new ArrayList<>();
        books.add(book);
        this.name = name;
        amountOfBooks = 1;
        calculateRanking();
    }

    public Author(String name, List<Book> oldBooks, Book newBook) {
        books = new ArrayList<>();
        books.addAll(oldBooks);
        books.add(newBook);
        this.name = name;
        amountOfBooks = books.size();
        calculateRanking();
    }

    public void calculateRanking(){
        double rate = 0;
        for (Book book: books) {
            rate += book.getRating();
        }
       rating = rate/amountOfBooks;
    }

    public double getRating(){
        return rating;
    }

    @Override
    public String toString() {
        return "Author{" +
                "rating=" + String.format("%.2f",getRating()) +
                ", amountOfBooks=" + books.size() +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public int compareTo(Author o) {
        return Double.compare(o.getRating(), this.getRating());
    }
}
