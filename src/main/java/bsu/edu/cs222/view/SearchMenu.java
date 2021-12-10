package bsu.edu.cs222.view;

import bsu.edu.cs222.controller.Controller;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import java.util.Map;

public class SearchMenu {
    Controller controller = new Controller();
    FXButtons fxButtons = new FXButtons();

    public StackPane buildSearchMenu(StackPane worldPane, World world) {
        CountryInfoPane countryInfoPane = new CountryInfoPane();
        FXUtility fxUtility = new FXUtility();
        int GO_BACK_BTN_SIZE = 15;

        StackPane searchMenu = new StackPane();
        searchMenu.getStyleClass().add("search-menu");
        searchMenu.setMaxWidth(200);

        TilePane inputPane = new TilePane();
        searchMenu.getChildren().add(inputPane);

        TextField search = fxUtility.buildCustomTextField("Search");

        search.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                Map<String, String> ISOMap = controller.getCountryISOMap();
                String isoCode = controller.getISOCode(ISOMap, fxUtility.searchCorrection(search.getText()));
                world.setSelectedCountry(CountryFX.valueOf(isoCode));
                world.zoomToCountry(CountryFX.valueOf(isoCode));
                StackPane countryInfo = countryInfoPane.buildCountryInfoPane(isoCode, world);
                worldPane.getChildren().add(countryInfo);
                countryInfo.setTranslateX((worldPane.getWidth() + 500));
                fxUtility.openPane(countryInfo);
                StackPane.setAlignment(countryInfo, Pos.CENTER_RIGHT);
                fxUtility.closePaneLeft(searchMenu);
            }
        });

        Button goBackButton = fxButtons.buildGoBackButtonLeft(GO_BACK_BTN_SIZE, searchMenu);

        inputPane.getChildren().addAll(fxUtility.getVerticalMargin(3), goBackButton, search);
        inputPane.setAlignment(Pos.TOP_CENTER);
        inputPane.setOrientation(Orientation.VERTICAL);

        return searchMenu;
    }


}
