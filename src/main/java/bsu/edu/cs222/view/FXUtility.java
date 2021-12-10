package bsu.edu.cs222.view;

import bsu.edu.cs222.controller.Controller;
import javafx.animation.TranslateTransition;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FXUtility {
    //Utility Functions for FX

    public void openPane(StackPane pane) {
        TranslateTransition openNav = new TranslateTransition(new Duration(350), pane);
        openNav.setToX(pane.getWidth());

        if (pane.getTranslateX() != 0) {
            openNav.play();
        }
    }

    public void closePaneLeft(StackPane pane) {
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), pane);
        closeNav.setToX(-(pane.getWidth()));
        closeNav.play();
    }

    public void closePaneRight(StackPane pane) {
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), pane);
        closeNav.setToX(pane.getWidth());
        closeNav.play();
    }

    public BorderPane getVerticalMargin(double margin) {
        BorderPane marginPane = new BorderPane();
        marginPane.setMinHeight(margin);
        return marginPane;
    }

    public TextField buildCustomTextField(String promptText) {
        TextField customTextField = new TextField();
        customTextField.setMinSize(50, 33);
        customTextField.setPromptText(promptText);
        return customTextField;
    }

    public void handleWorldZoom(String iso, World world) {
        world.resetZoom();
        world.setSelectedCountry(CountryFX.valueOf(iso));
        world.zoomToCountry(CountryFX.valueOf(iso));
    }

    public String searchCorrection(String search) {
        Controller controller = new Controller();
        List<String> searchCorrections = new ArrayList<>();
        try {
            searchCorrections = controller.checkMisspelling(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchCorrections.get(0);
    }

    public void startBackgroundMusic(StackPane worldPane) {
        String path = "src/main/resources/bsu.edu.cs222/cs222_theIslandMix.m4a";
        Media backgroundMusic = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(backgroundMusic);
        MediaView mediaView = new MediaView(mediaPlayer);
        worldPane.getChildren().add(mediaView);
        mediaPlayer.setAutoPlay(true);
    }

}
