package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Main {

    public static void main(String[] args) {
        String Base_URL = "https://official-joke-api.appspot.com/random_joke";
        try {
            URL url = new URL(Base_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            conn.disconnect();

            JSONObject jsonResponse = new JSONObject(response.toString());

            String setup = jsonResponse.getString("setup");
            String punchline = jsonResponse.getString("punchline");
            String setup1 = translateTextEnRu(setup);
            System.out.println(setup1);
            String punchline1 = translateTextEnRu(punchline);
            System.out.println(punchline1 + "\n");

            String setup2 = translateTextRuEn(setup1);
            System.out.println(setup2);
            String punchline2 = translateTextRuEn(punchline1);
            System.out.println(punchline2);

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    private static String translateTextEnRu(String text) throws Exception {
        String TRANSLATE_API_URL = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=ru&dt=t&q=";

        String urlString = TRANSLATE_API_URL + URLEncoder.encode(text, "UTF-8");
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        conn.disconnect();
        return new JSONArray(response.toString()).getJSONArray(0).getJSONArray(0).getString(0);
    }
    private static String translateTextRuEn(String text) throws Exception {
        String TRANSLATE_API_URL = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=ru&tl=en&dt=t&q=";

        String urlString = TRANSLATE_API_URL + URLEncoder.encode(text, "UTF-8");
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        conn.disconnect();
        return new JSONArray(response.toString()).getJSONArray(0).getJSONArray(0).getString(0);
    }
}