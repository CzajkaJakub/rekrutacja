package pl.akai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {

    private final String urlString;
    private StringBuilder response;
    private HttpURLConnection connection;

    public Connection(String url) {
        this.urlString = url;
    }

    public void makeConnection() throws IOException {
        URL url = new URL(urlString);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        getResponseFromServer();
    }


    private void getResponseFromServer() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
    }

    public String getResponse() {
        return response.toString();
    }
}
