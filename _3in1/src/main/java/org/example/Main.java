package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите нужное вам слово: ");
            System.out.println("картинка - фотка собаки");
            System.out.println("факт - факт о котиках");
            System.out.println("шутка - анекдот");
            System.out.println("выход - выход из программы\n");
            final String word = scanner.nextLine();

            if(word.equals("картинка")){
                String Base_URL = "https://dog.ceo/api/breeds/image/random";
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

                    String message = jsonResponse.getString("message");
                    System.out.println(message + "\n");

                } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage() + "\n");
                }

            }else if(word.equals("факт")){
                String Base_URL1 = "https://catfact.ninja/fact";
                try {
                    URL url = new URL(Base_URL1);
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

                    String fact = jsonResponse.getString("fact");
                    String fact1 = translateText(fact);
                    System.out.println(fact1 + "\n");

                } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage() + "\n");
                }

            }else if(word.equals("шутка")){
                String Base_URL2 = "https://official-joke-api.appspot.com/random_joke";
                String Base_URL22 = "https://official-joke-api.appspot.com/jokes/programming/random";

                try {
                    URL url = new URL(Base_URL2);
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
                    String setup1 = translateText(setup);
                    System.out.println(setup1);
                    String punchline1 = translateText(punchline);
                    System.out.println(punchline1 + "\n");

                } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage() + "\n");
                }
            }
            if(word.equals("выход")){
                System.out.println("Выход из программы.");
                break;
            }
        }
    }
    private static String translateText(String text) throws Exception {
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
}