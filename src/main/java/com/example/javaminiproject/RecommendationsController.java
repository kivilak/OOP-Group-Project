package com.example.javaminiproject;

import javafx.fxml.Initializable;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Kivilak Chathuranga
 */


public class RecommendationsController implements Initializable {
    @FXML
    private ListView<String> HotelList;

    @FXML
    private ListView<String> PlacesList;

    @FXML
    private TextField SearchBar;

    @FXML
    private Button SearchBtn;

    @FXML
    private Button BackBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BackBtn.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                Parent root = loader.load();
                SeaExplorer.scene.setRoot(root);
            } catch (IOException e) {
                System.err.println("Error loading Dashboard: " + e.getMessage());
            }
        });
    }

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
