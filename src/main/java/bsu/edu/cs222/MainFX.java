package bsu.edu.cs222;

import bsu.edu.cs222.view.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class MainFX extends Application {
    private World world;
    private final FXButtons fxButtons = new FXButtons();
    private final FXUtility fxUtility = new FXUtility();

    StackPane worldPane = null;


    public MainFX() {
    }

    @Override
    public void init() {
        world = WorldBuilder.create()
                .zoomEnabled(true)
                .selectionEnabled(true)
                .build();
        worldPane = new StackPane(world);
    }

    @Override
    public void start(Stage stage) {

        world.setMousePressHandler(this::countryMousePressHandler);
        fxButtons.setButtonTooltips();
        fxButtons.setButtonActions(worldPane, world);

        fxButtons.buildButtonMenu(worldPane);

        fxUtility.startBackgroundMusic(worldPane);

        ColorWorld colorWorld = new ColorWorld();
        colorWorld.colorWorld();

        handleSceneStart(stage);
    }

    public void countryMousePressHandler(MouseEvent event) {
        CountryPath countryPath = (CountryPath) event.getSource();
        CountryInfoPane countryInfoPane = new CountryInfoPane();
        Locale locale = countryPath.getLocale();
        fxUtility.handleWorldZoom(locale.getCountry(), world);

        StackPane countryInfo;

        countryInfo = countryInfoPane.buildCountryInfoPane(locale.getCountry(), world);
        worldPane.getChildren().add(countryInfo);
        countryInfo.setTranslateX((worldPane.getWidth() + 500));
        fxUtility.openPane(countryInfo);
        StackPane.setAlignment(countryInfo, Pos.CENTER_RIGHT);
    }

    private void addStyles(Scene scene) {
        scene.getStylesheets().add(Objects.requireNonNull(MainFX.class.getClassLoader().getResource("bsu.edu.cs222/custom-styles.css")).toExternalForm());
        world.getStyleClass().add("world");

        worldPane.setBackground(new Background(new BackgroundFill(world.getBackgroundColor(), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void handleSceneStart(Stage stage) {
        Scene scene = new Scene(worldPane);

        addStyles(scene);

        stage.setTitle("World Map");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
