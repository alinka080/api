package org.example;

import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
            System.out.println("Введите текст");
            String text = scanner.nextLine();
            System.out.println("Выберите нужный вам переводчик: ");
            System.out.println("1 - «русский – английский»");
            System.out.println("2 - «английский – русский».");
            int command = scanner.nextInt();
            switch (command) {
                case 1:
                    String t1 = translateTextRuEn(text);
                    System.out.println(t1);
                    break;
                case 2:
                    String t2 = translateTextEnRu(text);
                    System.out.println(t2);
                    break;
                default:
                    System.out.println("Извините, такой команды пока нет.");
                    break;
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