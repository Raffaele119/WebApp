package it.html.tutorial.library.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {
    public static void main(String[] args) {
        try {
            // URL del server
            URL url = new URL("http://localhost:8080/api/books");

            // Apri una connessione HTTP
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // Verifica il codice di risposta
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Errore nella richiesta: Codice HTTP " + responseCode);
            }

            // Leggi la risposta dal server
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            StringBuilder response = new StringBuilder();
            while ((output = br.readLine()) != null) {
                response.append(output);
            }

            // Stampa la risposta
            System.out.println("Risposta dal server:");
            System.out.println(response.toString());

            // Chiudi la connessione
            conn.disconnect();
        } catch (Exception e) {
            System.err.println("Errore durante la richiesta:");
            e.printStackTrace();
        }
    }
}