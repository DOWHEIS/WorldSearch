package bsu.edu.cs222.view;

import bsu.edu.cs222.controller.Controller;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.util.List;

public class FXButtons {
    //utilities for buttons
    FXUtility fxUtility = new FXUtility();
    int MENU_BTN_SIZE = 25;
    Button searchButton = createButtons("bsu.edu.cs222/searchIcon.png", MENU_BTN_SIZE);
    Button comparisonButton = createButtons("bsu.edu.cs222/comparison.png", MENU_BTN_SIZE);
    Button randomButton = createButtons("bsu.edu.cs222/random.png", MENU_BTN_SIZE);
    Button battleButton = createButtons("bsu.edu.cs222/swords.png", MENU_BTN_SIZE);

    public Button createButtons(String path, int size) {
        Button button = new Button();
        button.getStyleClass().add("button");
        Image img = new Image(path);
        ImageView view = new ImageView(img);
        view.setFitHeight(size);
        view.setFitWidth(size);
        button.setGraphic(view);
        return button;
    }

    public void setButtonTooltips() {
        searchButton.setTooltip(new Tooltip("Search"));
        comparisonButton.setTooltip(new Tooltip("Compare Countries"));
        randomButton.setTooltip(new Tooltip("Random Country"));
        battleButton.setTooltip(new Tooltip("Battle Mode"));
    }

    public void setButtonActions(StackPane worldPane, World world) {
        setSearchAction(worldPane, world);
        setComparisonAction(worldPane);
        setRandomAction(worldPane, world);
        setBattleAction(worldPane, world);
    }

    private void setSearchAction(StackPane worldPane, World world) {
        SearchMenu searchMenu = new SearchMenu();
        searchButton.setOnAction(event -> {
            StackPane search = searchMenu.buildSearchMenu(worldPane, world);
            worldPane.getChildren().add(search);
            search.setTranslateX(-(worldPane.getWidth() - 800));
            fxUtility.openPane(search);
            StackPane.setAlignment(search, Pos.CENTER_LEFT);
        });
    }

    private void setComparisonAction(StackPane worldPane) {
        ComparisonPane comparisonPane = new ComparisonPane();
        comparisonButton.setOnAction(event -> {
            StackPane comparisonMenu;

            comparisonMenu = comparisonPane.buildComparisonInput(worldPane);

            worldPane.getChildren().add(comparisonMenu);
            comparisonMenu.setTranslateX(-(worldPane.getWidth() - 800));
            fxUtility.openPane(comparisonMenu);
            StackPane.setAlignment(comparisonMenu, Pos.CENTER_LEFT);
        });
    }

    private void setRandomAction(StackPane worldPane, World world) {
        CountryInfoPane countryInfoPane = new CountryInfoPane();
        randomButton.setOnAction(event -> {
            StackPane countryInfo = null;
            try {
                countryInfo = countryInfoPane.randomISOBuilder(world);
            } catch (IOException e) {
                System.err.println("No Associated ISO");
            }

            worldPane.getChildren().add(countryInfo);
            assert countryInfo != null;
            countryInfo.setTranslateX((worldPane.getWidth() + 500));
            fxUtility.openPane(countryInfo);
            StackPane.setAlignment(countryInfo, Pos.CENTER_RIGHT);
        });

    }

    private void setBattleAction(StackPane worldPane, World world) {
        Controller controller = new Controller();
        BattleModePane battleModePane = new BattleModePane();
        battleButton.setOnAction(event -> {
            List<String> newISOList = controller.getCountryISOList();
            battleModePane.handleBattleModeVisual(newISOList, world, worldPane);
        });
    }

    private void buildButtonPane(StackPane menuPane) {
        TilePane buttonPane = new TilePane();
        buttonPane.setMaxSize(100, 420);
        buttonPane.getChildren().addAll(searchButton, fxUtility.getVerticalMargin(10), comparisonButton, fxUtility.getVerticalMargin(10), randomButton, fxUtility.getVerticalMargin(10), battleButton, fxUtility.getVerticalMargin(10));
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setOrientation(Orientation.VERTICAL);

        menuPane.getChildren().add(buttonPane);
    }

    public void buildButtonMenu(StackPane worldPane) {
        StackPane menuPane = new StackPane();
        menuPane.setMaxHeight(420);
        menuPane.setMaxWidth(75);
        menuPane.getStyleClass().add("sidebar");

        buildButtonPane(menuPane);

        worldPane.getChildren().add(menuPane);
        StackPane.setAlignment(menuPane, Pos.CENTER_LEFT);
    }

    public Button buildGoBackButtonRight(int GO_BACK_BTN_SIZE, StackPane stackPane) {
        Button goBackButton = createButtons("bsu.edu.cs222/goBackRight.png", GO_BACK_BTN_SIZE);
        goBackButton.setOnAction(event -> fxUtility.closePaneRight(stackPane));
        return goBackButton;
    }

    public Button buildGoBackButtonLeft(int GO_BACK_BTN_SIZE, StackPane stackPane) {
        Button goBackButton = createButtons("bsu.edu.cs222/goBack.png", GO_BACK_BTN_SIZE);
        goBackButton.setOnAction(event -> fxUtility.closePaneLeft(stackPane));
        return goBackButton;
    }
}
