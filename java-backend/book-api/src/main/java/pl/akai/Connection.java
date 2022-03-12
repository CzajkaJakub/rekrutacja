package pl.akai;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Connection {

    private URL url;
    private ObjectMapper objectMapper;

    public void makeConnection(String urlString) throws IOException {
        url = new URL(urlString);
        objectMapper = new ObjectMapper();
    }

    public List<Book> getBooksFromServer() throws IOException {
        return objectMapper.readValue(url, new TypeReference<>(){});
    }
}
