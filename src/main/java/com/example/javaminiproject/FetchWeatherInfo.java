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
    private final String CURRENT_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private final String WEEK_URL = "api.openweathermap.org/data/2.5/forecast/daily?q={city name}&cnt={cnt}&appid={API key}";
    private final String UNITS = "&units=metric";
    private final String APPID = "&appid=";

    public FetchWeatherInfo() {
        Dotenv dotenv = Dotenv.load();
        API_KEY = dotenv.get("API_KEY");
    }

    public WeatherInfo getCurrentWeatherInfo(String location) { // get live weather info
        WeatherInfo weatherInfo = new WeatherInfo();
        String urlString = CURRENT_URL + location + APPID + API_KEY + UNITS;

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = null;
            connection = (HttpURLConnection) url.openConnection(); // open connection
            connection.setRequestMethod("GET"); // set request method
            connection.connect(); // connect to the API

            int responseCode = connection.getResponseCode(); // get API response

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                JSONObject weatherData = new JSONObject(content.toString()); // fetch the API data
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

        return weatherInfo;
    }

    public WeatherInfo[] getWeekWeather(String location) { // get weekly weather info
        WeatherInfo[] weatherInfos = new WeatherInfo[7];
        String urlString = WEEK_URL + location + APPID + API_KEY + UNITS;

        return weatherInfos;
    }
}
