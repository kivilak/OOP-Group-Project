package com.example.javaminiproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * @author Kivilak Chathuranga
 */


public class RecommendationsController {
    @FXML
    private ListView<String> HotelList;

    @FXML
    private ListView<String> PlacesList;

    @FXML
    private TextField SearchBar;

    @FXML
    private Button SearchBtn;

    @FXML
    private void onHomeButtonClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent root = loader.load();
        SeaExplorer.scene.setRoot(root);
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
}
