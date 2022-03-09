package pl.akai;

import org.json.JSONArray;
import java.util.ArrayList;

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

    private static final ArrayList<Author> authors = new ArrayList<>();

    public static void main(String[] args) {


        try {
            Connection connection = new Connection("https://akai-recruitment.herokuapp.com/book");
            connection.makeConnection();
            pareJsonObject(connection.getResponse());
        }catch (Exception ignored){}

        // TODO TU wpisujemy ile najlepszych pisarzy mamy wypisac
        int x = 9;
        printBestsAuthors(x);
        }

    private static void pareJsonObject(String response) {
        JSONArray array = new JSONArray(response);
        for (int i = 0; i < array.length(); i++) {
            String id = array.getJSONObject(i).getString("id");
            String title = array.getJSONObject(i).getString("title");
            String author = array.getJSONObject(i).getString("author");
            Double rating = array.getJSONObject(i).getDouble("rating");
            createBooksAndAuthors(id, title, author, rating);
        }
    }

    private static void createBooksAndAuthors(String id, String title, String author, Double rating) {
            // tworzenie nowej ksiazki
            Book book = new Book(id, title, author, rating);

            //sprawdzenie czy w liscie znajduje sie juz autor jesli nie to stworz nowego jesli tak przypisz mu nowa ksiazke
            int exist = 0;
            for(Author x: authors){
                if(x.getName().equals(author)){
                    exist = 1;
                    x.addBook(book);
                    break;
                }
            }

            if(exist == 0){
                Author newAuthor = new Author(author);
                newAuthor.addBook(book);
                authors.add(newAuthor);
            }
        }

    private static void printBestsAuthors(int x) {
        
        double maxRate = 0;
        Author bestAuthor = null;
        ArrayList<Author> bestsAuthors = new ArrayList<>();
        
        for(int i = 0; i < x; i ++){

            // wypisanie wszystkich autorow
            for (Author author: authors) {
                if(author.getRating() >= maxRate && !bestsAuthors.contains(author)){
                    maxRate = author.getRating();
                    bestAuthor = author;
                }
            }
            bestsAuthors.add(bestAuthor);
            maxRate = 0;
            bestAuthor = null;
        }

        for (Author author: bestsAuthors) {
            if(author != null){System.out.println(author);}
        }
    }
}


