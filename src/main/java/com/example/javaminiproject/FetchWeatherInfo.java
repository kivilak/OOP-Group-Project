package com.example.javaminiproject;

/**
 * @author Kivilak Chathuranga
 */

import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class FetchWeatherInfo {
    private final String API_KEY;
    private final String URL = "https://api.openweathermap.org/data/2.5/weather?q=";

    public FetchWeatherInfo() {
        Dotenv dotenv = Dotenv.load();
        API_KEY = dotenv.get("API_KEY");
    }

    public WeatherInfo getWeatherInfo(String location) {
        WeatherInfo weatherInfo = new WeatherInfo();
        String urlString = URL + location + "&appid=" + API_KEY + "&units=metric";

        System.out.println(urlString);

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {  // 200 OK
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder content = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();

                    JSONObject weatherData = new JSONObject(content.toString());
                    //System.out.println(weatherData.toString(2));

                    JSONObject main = weatherData.getJSONObject("main");

                    double temp = main.getDouble("temp");
                    double tempMin = main.getDouble("temp_min");
                    double tempMax = main.getDouble("temp_max");
                    double feelsLike = main.getDouble("feels_like");
                    int humidity = main.getInt("humidity");
                    double pressure = main.getDouble("pressure");

                    JSONObject wind = weatherData.getJSONObject("wind");

                    double windSpeed = wind.getDouble("speed");


                    JSONArray weather = weatherData.getJSONArray("weather");

                    String mainDes = weather.getJSONObject(0).getString("main");
                    String description = weather.getJSONObject(0).getString("description");
                    String icon = weather.getJSONObject(0).getString("icon");

                    weatherInfo.setWeather(temp, tempMin, tempMax, feelsLike, humidity, pressure, windSpeed, mainDes, description, icon);

                } else {
                    System.out.println("GET request not worked, Response Code: " + responseCode);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return weatherInfo;
    }

    public String getWeather() {

        return "";
    }
}
