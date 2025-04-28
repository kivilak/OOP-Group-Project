package com.example.javaminiproject;

/**
 * @author Kivilak Chathuranga
 */


public class WeatherInfo {
    private double temp;
    private double tempMin;
    private double tempMax;
    private double feelsLike;
    private int humidity;
    private double pressure;
    private double windSpeed;
    private String main;
    private String description;
    private String iconName;

    public void setWeather(double temp, double tempMin, double tempMax, double feelsLike, int humidity, double pressure, double windSpeed, String main, String description, String iconName) {
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.main = main;
        this.description = description;
        this.iconName = iconName;
    }

    public String getTemp() {
        return String.valueOf(temp);
    }

    public String getTempMin() {
        return String.valueOf(tempMin);
    }

    public String getTempMax() {
        return String.valueOf(tempMax);
    }

    public String getFeelsLike() {
        return String.valueOf(feelsLike);
    }

    public String getHumidity() {
        return String.valueOf(humidity);
    }

    public String getPressure() {
        return String.valueOf(pressure);
    }

    public String getWindSpeed() {
        return String.valueOf(windSpeed);
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIconName() {
        return iconName;
    }
}