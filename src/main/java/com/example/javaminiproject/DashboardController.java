package com.example.javaminiproject;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Pane dashboard;

    @FXML
    private Button regional_info_button;

    @FXML
    private Button weather_button;

    @FXML
    private Button map_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        regional_info_button.setClip(ButtonClipping(0, 0, 265, 312));
        weather_button.setClip(ButtonClipping(0, 0, 265, 312));
        map_button.setClip(ButtonClipping(0, 0, 570, 224));
    }

    @FXML
    private Rectangle ButtonClipping(int x, int y, int width, int height) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
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