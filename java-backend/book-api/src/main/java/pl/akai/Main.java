package pl.akai;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class Main {
    /*
        Twoim zadaniem jest napisanie prostego programu do pobierania i transformowania danych
        udostępnianych przez API. Dokumentacje API możesz znależć pod poniższym linkiem:
        https://akai-recruitment.herokuapp.com/documentation.html

        Całe API zawiera jeden endpoint: https://akai-recruitment.herokuapp.com/book
        Endpoint ten zwraca liste książek zawierajacch informację takie jak:
        - id
        - tytuł
        - autor
        - ocena

        Twoim zadaniem jest:
        1. Stworzenie odpowiedniej klasy do przechowywania informacji o książce
        2. Sparsowanie danych udostępnianych przez endpoint. Aby ułatwić to zadanie,
           do projektu są dołaczone 3 najpopularniejsze biblioteki do parsowania JSONów
           do obiektów Javy - Gson, Org.Json, Jackson. Możesz wykorzystać dowolną z nich
        3. Po sparsowaniu JSONu do obiektów Javy, uzupełnij program o funkcję wypisującą 3 autorów z
           najwyższą średnią ocen. Na przykład, gdy osoba X jest autorem książki A z oceną 9 i B z oceną 8,
           to powinna zostać wyświetlona informacja: X - 8.5

       Projekt został utworzony przy użyciu najnowszej Javy 17,
       jednakże nic nie stoi na przeszkodzie użycia innej wersji jeśli chcesz
     */

    private static final Map<String, Author> authors = new HashMap<>();

    public static void main(String[] args) {
        try {
            Connection connection = new Connection();
            connection.makeConnection("https://akai-recruitment.herokuapp.com/book");
            List<Book> bookList = connection.getBooksFromServer();
            createBooksAndAuthors(bookList);
        } catch (Exception ignored) {}


        // TODO TU wpisujemy ile najlepszych pisarzy mamy wypisac
        int x = 6;
        printBestsAuthors(x);

    }

    private static void createBooksAndAuthors(List<Book> bookList) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 0; i < bookList.size(); i++) {
            String jsonBook = objectMapper.writeValueAsString(bookList.get(i));
            Book book = objectMapper.readValue(jsonBook, Book.class);
            assignBookToAuthor(book);
        }
    }

    private static void assignBookToAuthor(Book book) {
        authors.merge(book.getAuthor(), new Author(book.getAuthor(), book), (oldAuthor, newAutor) -> new Author(book.getAuthor(), oldAuthor.getBooks(), book));
    }


    private static void printBestsAuthors(int x) {
        List<Author> allAuthors = new ArrayList<>(authors.values());
        Collections.sort(allAuthors);
        for (int i = 0; i < x; i++) {
            System.out.println(allAuthors.get(i));
        }
    }
}