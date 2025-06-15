package com.example.javaminiproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author Kivilak Chathuranga
 */


public class MapController implements Initializable {
    @FXML
    private Pane map_dashboard;

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        map_dashboard.setClip(null);
        final String[] search_location = new String[1];

        TextField search_field = new TextField();
        search_field.setPromptText("Search");
        search_field.setLayoutX(250);
        search_field.setLayoutY(15);
        search_field.getStyleClass().add("serach-field");
        map_dashboard.getChildren().add(search_field);

        Button search_button = new Button("Search");
        search_button.setLayoutX(570);
        search_button.setLayoutY(15);
        search_button.getStyleClass().add("search-button");
        map_dashboard.getChildren().add(search_button);

        StackPane stackPane = new StackPane();
        stackPane.getStyleClass().add("map-dashboard");
        WebView webView = getWebView("sri+lanka");
        stackPane.getChildren().add(webView);

        stackPane.setLayoutX(0);
        stackPane.setLayoutY(67);
        map_dashboard.getChildren().add(stackPane);

        search_button.setOnAction(event -> {
            search_location[0] = search_field.getText();
            stackPane.getChildren().clear();
            stackPane.getChildren().add(getWebView(search_location[0]));
        });
    }

    @NotNull
    private WebView getWebView(String location) {
        WebView webView = new WebView();
        webView.setPrefSize(1040, 701);
        WebEngine webEngine = webView.getEngine();
        String url = "https://www.google.com/maps/place/" + location + "/@7.8731,80.7718,7z/data=!3m1!4b1!4m6!3m5!1s0x3ae25c2f2f2f2f2f:0x3ae25c2f2f2f2f2f!8m2!3d7.8731!4d80.7718!16zL20vMDJtZzQ?entry=ttu";
        webEngine.load(url);
        webView.setLayoutX(0);
        webView.setLayoutY(0);
        return webView;
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
}
