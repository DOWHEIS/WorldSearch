package bsu.edu.cs222.view;

import bsu.edu.cs222.controller.Controller;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.util.List;

public class CountryInfoPane {
    //building the single country viewer
    Controller controller = new Controller();
    FXUtility fxUtility = new FXUtility();

    public StackPane buildCountryInfoPane(String isoCode, World world) {
        FXButtons fxButtons = new FXButtons();
        int GO_BACK_BTN_SIZE = 15;
        FXCountryData fxCountryData = new FXCountryData();
        StackPane countryInfo = new StackPane();
        countryInfo.setStyle("-fx-background-color: rgb(37,35,36)");
        countryInfo.setMaxSize(450, 600);

        ListView<String> fullValueNames = fxCountryData.getFullNamesListView();

        ListView<String> countryData = fxCountryData.buildCountryData(isoCode);

        countryInfo.getChildren().addAll(fullValueNames, countryData);
        StackPane.setAlignment(fullValueNames, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(countryData, Pos.BOTTOM_RIGHT);

        TilePane displayPane = new TilePane();
        countryInfo.getChildren().add(displayPane);

        Button goBackButton = fxButtons.buildGoBackButtonRight(GO_BACK_BTN_SIZE, countryInfo);

        Label countryNameDisplay = buildCountryNameDisplay(world);

        displayPane.getChildren().addAll(fxUtility.getVerticalMargin(0), countryNameDisplay, goBackButton);
        displayPane.setAlignment(Pos.TOP_CENTER);
        displayPane.setOrientation(Orientation.VERTICAL);

        return countryInfo;
    }

    private Label buildCountryNameDisplay(World world) {
        Label countryNameDisplay = new Label();
        countryNameDisplay.getStyleClass().add("label_display");
        String name = controller.getNameFromMap(world.getSelectedCountry().getName());
        String capitalizedName = name.substring(0, 1).toUpperCase() + name.substring(1);
        countryNameDisplay.setText(capitalizedName);
        return countryNameDisplay;
    }

    public StackPane randomISOBuilder(World world) throws IOException {
        List<String> ISOList = controller.getCountryISOList();
        String randISO = controller.getRandomISOCode(ISOList);
        fxUtility.handleWorldZoom(randISO, world);
        return buildCountryInfoPane(randISO, world);
    }


}
