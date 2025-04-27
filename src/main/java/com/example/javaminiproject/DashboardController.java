package com.example.javaminiproject;

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
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
    private void DisplayWeather() {
        Pane pane = new Pane();
        pane.getStyleClass().add("live-weather");

        Label titleLabel = new Label("Live Weather");
        Label tempLabel = new Label("32");
        Label celsiusLabel = new Label("°C");
        Label weatherLabel = new Label("Sunny");
        Label locationLabel = new Label("Sri Lanka");
        Label dayLabel = new Label("Monday");

        DisplayLabel("title-label", titleLabel, pane,20, 20);
        DisplayLabel("temp-label", tempLabel, pane,20, 80);
        DisplayLabel("celsius-label", celsiusLabel, pane,80, 100);
        DisplayLabel("weather-label", weatherLabel, pane,20, 130);
        DisplayLabel("location-label", locationLabel, pane,20, 180);
        DisplayLabel("day-label", dayLabel, pane,20, 210);

        try {
            // Use getClass().getResource() to properly locate the resource
            URL imageUrl = getClass().getResource("images/dashboard/sunny.png");
            if (imageUrl == null) {
                System.err.println("Cannot find image at path: images/dashboard/sunny.png");
                // You could use a placeholder image instead
            } else {
                Image image = new Image(imageUrl.toExternalForm());
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(70);
                imageView.setFitWidth(70);
                imageView.setPreserveRatio(true);

                imageView.setLayoutX(130);
                imageView.setLayoutY(80);

                pane.getChildren().add(imageView);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading image: " + e.getMessage());
        }

        //pane.getChildren().add(imageView);
        //pane.getChildren().addAll(titleLabel, tempLabel, locationLabel, dayLabel);
        pane.setLayoutX(664);
        pane.setLayoutY(143);
        dashboard.getChildren().add(pane);
    }

    @FXML
    private void DisplayLabel(String id, Label label, Pane pane, int layoutX, int layoutY) {
        label.setId(id);
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);

        pane.getChildren().add(label);
    }

    @FXML
    private void DisplayButton(Button button, String id, String className, int layoutX, int layoutY) {
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
    private Rectangle ButtonClipping(int width, int height) {
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