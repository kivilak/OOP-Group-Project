package com.example.javaminiproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * @author Kivilak Chathuranga
 */


public class RegionalInfoController {
    public class RegionalInfo {
        // Attributes
        private int id;
        private String name;
        private String description;
        private String image_url;
        private String location;
        private String type;
        private float rating;

        // Default Constructor
        public RegionalInfo() {
            // You can initialize default values here if needed
        }

        // Getter Methods
        public int getID() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getImage_url() {
            return image_url;
        }

        public String getLocation() {
            return location;
        }

        public String getType() {
            return type;
        }

        public float getRating() {
            return rating;
        }

        // Setter Methods (Optional: If you want to update the values later)
        public void setID(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setRating(float rating) {
            this.rating = rating;
        }
    }


    @FXML
    private void onHomeButtonClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
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
