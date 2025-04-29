package com.example.javaminiproject;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import java.time.LocalDate;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    private final String location = "Sri Lanka";
    @FXML
    private AnchorPane dashboard;

    @FXML
    private final Button regional_info_button = new Button("Regional Info");

    @FXML
    private final Button weather_button = new Button("Weather");

    @FXML
    private final Button map_button = new Button("Map");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dashboard.setClip(null);
        regional_info_button.setClip(ButtonClipping(265, 312));
        weather_button.setClip(ButtonClipping(265, 312));
        map_button.setClip(ButtonClipping(570, 224));

        regional_info_button.setOnAction(e -> {
            try {
                onRegionalInfoButtonClicked();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        weather_button.setOnAction(e -> {
            try {
                onWeatherButtonClicked();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        map_button.setOnAction(e -> {
            try {
                onMapButtonClicked();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        DisplayButton(regional_info_button, "regional-info", "regional-info-button", 54, 143);
        DisplayButton(weather_button, "weather", "weather-button", 359, 143);
        DisplayButton(map_button, "map", "map-button", 54, 495);

        DisplayWeather();
    }

    @FXML
    private void DisplayWeather() { // weather display card
        FetchWeatherInfo fetchWeatherInfo = new FetchWeatherInfo();
        LocalDate date = LocalDate.now();

        Task<WeatherInfo> weatherTask = new Task<>() {
            @Override
            protected WeatherInfo call() {
                return fetchWeatherInfo.getCurrentWeatherInfo("Sri%20Lanka");
            }
        };

        weatherTask.setOnSucceeded(event -> {
            WeatherInfo weatherInfo = weatherTask.getValue();

            Pane pane = new Pane();
            pane.getStyleClass().add("live-weather");

            DisplayLabel("title-label", "Live Weather", pane, 20, 20);
            DisplayLabel("temp-label", weatherInfo.getTemp(), pane, 20, 170);
            DisplayLabel("celsius-label", "°C", pane, 150, 190);
            DisplayLabel("weather-label", weatherInfo.getMain(), pane, 20, 220);
            DisplayLabel("location-label", location, pane, 20, 250);
            DisplayLabel("day-label", date.getDayOfWeek().toString(), pane, 20, 280);

            String imageUrl = "https://openweathermap.org/img/wn/" + weatherInfo.getIconName() + "@4x.png";
            try {
                Image image = new Image(imageUrl, true);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(150);
                imageView.setFitWidth(150);
                imageView.setPreserveRatio(true);
                imageView.setLayoutX(70);
                imageView.setLayoutY(40);
                pane.getChildren().add(imageView);
            } catch (Exception e) {
                System.err.println("Error loading image: " + e.getMessage());
            }

            pane.setLayoutX(664);
            pane.setLayoutY(143);
            dashboard.getChildren().add(pane);
        });

        weatherTask.setOnFailed(event -> {
            System.err.println("Failed to fetch weather data: " + weatherTask.getException().getMessage());
        });

        new Thread(weatherTask).start();
    }

    @FXML
    private void DisplayLabel(String id, String text, Pane pane, int layoutX, int layoutY) { // create label and display
        Label label = new Label(text);
        label.setId(id);
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);

        pane.getChildren().add(label);
    }

    @FXML
    private void DisplayButton(Button button, String id, String className, int layoutX, int layoutY) { // display button
        //button.setId(id);
        button.getStyleClass().add(className);
        button.setLayoutX(layoutX);
        button.setLayoutY(layoutY);

        StackPane stackPane = new StackPane();
        stackPane.setId(id);
        stackPane.setLayoutX(layoutX);
        stackPane.setLayoutY(layoutY);
        stackPane.getChildren().add(button);
        stackPane.getStyleClass().add("stack-pane");

        dashboard.getChildren().add(stackPane);
    }

    @FXML
    private Rectangle ButtonClipping(int width, int height) { // Style buttons
        Rectangle rectangle = new Rectangle(0, 0, width, height);
        rectangle.setArcHeight(30);
        rectangle.setArcWidth(30);

        return rectangle;
    }

    @FXML
    private void onRegionalInfoButtonClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegionalInfo.fxml"));
        Parent root = loader.load();
        SeaExplorer.scene.setRoot(root);
    }

    @FXML
    private void onWeatherButtonClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Weather.fxml"));
        Parent root = loader.load();
        SeaExplorer.scene.setRoot(root);
    }

    @FXML
    private void onMapButtonClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Map.fxml"));
        Parent root = loader.load();
        SeaExplorer.scene.setRoot(root);
    }

    @FXML
    private void onRecommendationsButtonClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Recommendations.fxml"));
        Parent root = loader.load();
        SeaExplorer.scene.setRoot(root);
    }
}