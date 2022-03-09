package pl.akai;

import java.util.ArrayList;

public class Author {
    private double rating;
    private int amountOfBooks;
    private final String name;
    private final ArrayList<Book> books;

    public Author(String name) {
        this.name = name;
        books = new ArrayList<>();
        amountOfBooks = 0;
        rating = 0;
    }

    public void addBook(Book book){
        books.add(book);
        amountOfBooks ++;
    }

    public void setRating(){
        double rate = 0;
        for (Book book: books) {
            rate += book.getRating();
        }
       rating = rate/amountOfBooks;
    }

    public double getRating(){
        setRating();
        return rating;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "rating=" + String.format("%.2f",getRating()) +
                ", amountOfBooks=" + amountOfBooks +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
