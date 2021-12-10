package bsu.edu.cs222.view;

import bsu.edu.cs222.controller.Controller;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.util.Map;

public class ComparisonPane {
    //building the comparison viewer
    FXUtility fxUtility = new FXUtility();

    private StackPane buildComparisonMenu(String isoOne, String isoTwo) throws IOException {
        FXButtons fxButtons = new FXButtons();

        int GO_BACK_BTN_SIZE = 15;
        FXCountryData fxCountryData = new FXCountryData();

        StackPane countries = new StackPane();
        countries.setMaxSize(690, 600);
        countries.setStyle("-fx-background-color: rgb(37,35,36)");

        ListView<String> fullValueNames = fxCountryData.getFullNamesListView();

        ListView<String> countryOneData = fxCountryData.buildCountryData(isoOne);

        ListView<String> countryTwoData = fxCountryData.buildCountryData(isoTwo);

        countries.getChildren().addAll(fullValueNames, countryOneData, countryTwoData);

        StackPane.setAlignment(fullValueNames, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(countryOneData, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(countryTwoData, Pos.BOTTOM_RIGHT);

        TilePane displayPane = new TilePane();
        countries.getChildren().add(displayPane);

        Button goBackButton = fxButtons.buildGoBackButtonRight(GO_BACK_BTN_SIZE, countries);

        displayPane.getChildren().addAll(fxUtility.getVerticalMargin(0), goBackButton);
        displayPane.setAlignment(Pos.TOP_CENTER);
        displayPane.setOrientation(Orientation.VERTICAL);

        return countries;
    }

    public StackPane buildComparisonInput(StackPane worldPane) {
        FXButtons fxButtons = new FXButtons();

        int GO_BACK_BTN_SIZE = 15;
        StackPane searchComparisonMenu = new StackPane();
        searchComparisonMenu.getStyleClass().add("search-menu");
        searchComparisonMenu.setMaxWidth(200);

        TilePane inputPane = new TilePane();
        searchComparisonMenu.getChildren().add(inputPane);

        TextField countrySearchOne = fxUtility.buildCustomTextField("First Country");

        TextField countrySearchTwo = fxUtility.buildCustomTextField("Second Country");

        countrySearchTwo.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                countrySearchTwo.setDisable(!countrySearchTwo.isDisable());
                handleComparisonSearch(countrySearchOne.getText(), countrySearchTwo.getText(), worldPane);
                fxUtility.closePaneLeft(searchComparisonMenu);
            }
        });

        Button goBackButton = fxButtons.buildGoBackButtonLeft(GO_BACK_BTN_SIZE, searchComparisonMenu);

        inputPane.getChildren().addAll(fxUtility.getVerticalMargin(3), goBackButton, countrySearchOne, fxUtility.getVerticalMargin(10), countrySearchTwo);
        inputPane.setAlignment(Pos.TOP_CENTER);
        inputPane.setOrientation(Orientation.VERTICAL);

        return searchComparisonMenu;
    }

    private void handleComparisonSearch(String searchOne, String searchTwo, StackPane worldPane) {
        Controller controller = new Controller();
        try {
            Map<String, String> ISOMap = controller.getCountryISOMap();

            String isoCodeOne = controller.getISOCode(ISOMap, fxUtility.searchCorrection(searchOne));
            String isoCodeTwo = controller.getISOCode(ISOMap, fxUtility.searchCorrection(searchTwo));
            StackPane countryInfo = buildComparisonMenu(isoCodeOne, isoCodeTwo);

            worldPane.getChildren().add(countryInfo);
            countryInfo.setTranslateX((worldPane.getWidth() + 500));
            fxUtility.openPane(countryInfo);
            StackPane.setAlignment(countryInfo, Pos.CENTER_RIGHT);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
