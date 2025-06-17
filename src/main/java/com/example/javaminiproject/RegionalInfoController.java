package com.example.javaminiproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Kivilak Chathuranga
 */


public class RegionalInfoController implements Initializable{

    private GridPane seaLifeGrid,CoralReefsGrid,SwimmingSpotGrid;
    @FXML
    private Button seaLife_btn,coralReef_btn,swimming_btn;
    @FXML
    private Pane pane;
    @FXML
    private ScrollPane backgroundcard;

    @FXML
    private Pane top_pane;

    @Override
    public void initialize(java.net.URL location,java.util. ResourceBundle resources) {
        TextField search_field = new TextField();
        search_field.setPromptText("Search");
        search_field.setLayoutX(373);
        search_field.setLayoutY(14);
        search_field.getStyleClass().add("serach-field");
        top_pane.getChildren().add(search_field);

        Button back_button = new Button("Back");
        back_button.setLayoutX(20);
        back_button.setLayoutY(14);
        back_button.getStyleClass().add("back-button");
        top_pane.getChildren().add(back_button);

        URL search_url = getClass().getResource("images/weather/search.png");

        try {
            DisplayImage(search_url.toString(), top_pane, 25, 25, 384, 22);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        seaLifeGrid = loadGrid("SeaLife");
        CoralReefsGrid = loadGrid("CoralReefs");
        SwimmingSpotGrid = loadGrid("SwimmingSpots");

        backgroundcard.setClip(null);
        backgroundcard.setContent(seaLifeGrid);
        backgroundcard.setStyle("-fx-background-color:transparent;");

        seaLife_btn.setOnAction(e-> {
            try {
                backgroundcard.setContent(seaLifeGrid);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        coralReef_btn.setOnAction(e-> {
            try {
                backgroundcard.setContent(CoralReefsGrid);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        swimming_btn.setOnAction(e-> {
            try {
                backgroundcard.setContent(SwimmingSpotGrid);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    private GridPane loadGrid(String tableName) {
        GridPane grid = createGrid();

        MySQLConnection mySQLConnection = new MySQLConnection();

        RegionalInfo[] regionalInfo = mySQLConnection.getRegionalInfo();

        for (int i = 0; i < regionalInfo.length; i++) {
            RegionalInfo info = regionalInfo[i];

            try {
                addCard(grid, i, info);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return grid;
    }

    @FXML
    private GridPane createGrid() {
        GridPane grid =new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.getStyleClass().add("gridpane");

//
        return grid;
    }
    @FXML
    private void addCard(GridPane grid, int index, RegionalInfo regionalInfo) throws IOException {
        //add the image to card
        String imageUrl = getClass().getResource(regionalInfo.getImage_url()).toString();
        Image image = new Image(imageUrl,true);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(150);
        imageView.setFitWidth(280);
        imageView.setPreserveRatio(false);
        imageView.setSmooth(true);
        imageView.getStyleClass().add("images");

        //add title to card
        Label labeltitle = new Label(regionalInfo.getName());
        labeltitle.getStyleClass().add("titleD");

        //add Short Description to card
        Label labelShort = new Label(regionalInfo.getSmall_description());
        labelShort.getStyleClass().add("shortD");

        Button MoreInfoBtn = new Button("More Info");
        MoreInfoBtn.setPrefWidth(100.0);
        MoreInfoBtn.getStyleClass().add("MoreInfobtn");

        MoreInfoBtn.setOnAction(e -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegionalMoreInfo.fxml"));
            Parent root = null;

            loader.setControllerFactory(param -> {
                RegionalMoreInfoController controller = new RegionalMoreInfoController();
                controller.regionalInfo = regionalInfo;
                return controller;
            });

            try {
                root = loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            SeaExplorer.scene.setRoot(root);

            /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("More about");
            alert.setHeaderText(title);
            alert.setContentText(fullDesc);
            alert.showAndWait();*/
        });

        VBox card = new VBox(imageView, labeltitle, labelShort, MoreInfoBtn);
        card.getStyleClass().add("card-profile");
        card.setClip(CardClipping(280,300));
        VBox.setMargin(labeltitle,new Insets(0.0,0.0,0.0,10.0) );
        VBox.setMargin(labelShort,new Insets(0.0,0.0,0.0,10.0));
        VBox.setMargin(MoreInfoBtn,new Insets(0.0,10.0,0.0,140.0));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(card);
        stackPane.getStyleClass().add("stack-pane");

        GridPane.setMargin(card,new Insets(10.0));

        int col = index % 3;
        int row = index / 3;
        grid.add(stackPane, col, row);
    }


    private Rectangle CardClipping(int width, int height) {
        Rectangle rectangle = new Rectangle(0, 0, width, height);
        rectangle.setArcHeight(70);
        rectangle.setArcWidth(70);

        return rectangle;
    }

    @FXML
    private void DisplayImage(String url, Pane pane, int width, int height, int layoutX, int layoutY) throws IOException {
        Image image = new Image(url, true);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        imageView.setPreserveRatio(true);
        imageView.setLayoutX(layoutX);
        imageView.setLayoutY(layoutY);
        pane.getChildren().add(imageView);
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

    @FXML
    private void onBackButtonClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent root = loader.load();
        SeaExplorer.scene.setRoot(root);
    }


}
