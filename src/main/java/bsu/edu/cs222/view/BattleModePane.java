package bsu.edu.cs222.view;

import bsu.edu.cs222.controller.Controller;
import bsu.edu.cs222.model.IsoRandom;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BattleModePane {
    FXButtons fxButtons = new FXButtons();
    FXUtility fxUtility = new FXUtility();

    public void handleBattleModeVisual(List<String> newISOList, World world, StackPane worldPane) {

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(.01), ev -> {
            world.setHoverEnabled(false);
            IsoRandom isoRandom = new IsoRandom();
            String isoOne = isoRandom.getRandomISOCode(newISOList);
            String isoTwo = isoRandom.getRandomISOCode(newISOList);
            int randomInt = ThreadLocalRandom.current().nextInt(0, 50);
            if (randomInt % 2 == 0) {
                newISOList.remove(isoOne);
                handleKnockOutZoom(isoOne, world);
            } else {
                newISOList.remove(isoTwo);
                handleKnockOutZoom(isoTwo, world);
            }

            handleBattleModeVisual(newISOList, world, worldPane);

        }));
        if (newISOList.size() == 1) {
            timeline.stop();
            System.out.println(newISOList.get(0));
            world.setSelectedCountry(CountryFX.valueOf(newISOList.get(0)));
            world.zoomToCountry(CountryFX.valueOf(newISOList.get(0)));
            buildWinnerDisplay(newISOList, worldPane, world);
        } else {
            timeline.play();
        }
    }

    private void handleKnockOutZoom(String iso, World world) {
        world.zoomToCountry(CountryFX.valueOf(iso));
    }

    private void buildWinnerDisplay(List<String> isoWinner, StackPane worldPane, World world) {
        FXUtility fxUtility = new FXUtility();

        StackPane winnerDisplay = buildBattleWinner(isoWinner.get(0), world);
        winnerDisplay.setStyle("-fx-background-color: rgb(37,35,36)");
        winnerDisplay.setTranslateX((worldPane.getWidth() + 200));
        worldPane.getChildren().add(winnerDisplay);
        fxUtility.openPane(winnerDisplay);
        StackPane.setAlignment(winnerDisplay, Pos.CENTER_RIGHT);
    }

    private StackPane buildBattleWinner(String winner, World world) {
        int GO_BACK_BTN_SIZE = 15;
        Controller controller = new Controller();
        StackPane battleMenu = new StackPane();
        battleMenu.setMaxHeight(200);
        battleMenu.setMaxWidth(350);
        Label winnerDisplay = new Label();
        winnerDisplay.getStyleClass().add("label_display");

        String name;

        name = controller.getNameFromMap(winner);

        String capitalizedName = name.substring(0, 1).toUpperCase() + name.substring(1);

        winnerDisplay.setText("The Great Country of \n" + capitalizedName + "\nis the champion of the world!");

        TilePane battlePane = new TilePane();
        battleMenu.getChildren().add(battlePane);

        Button goBackButton = fxButtons.buildGoBackButtonRight(GO_BACK_BTN_SIZE, battleMenu);
        goBackButton.setOnAction(event -> {
            world.setHoverEnabled(true);
            fxUtility.closePaneRight(battleMenu);
            world.resetZoom();
        });


        battlePane.getChildren().addAll(goBackButton, winnerDisplay);
        battlePane.setAlignment(Pos.TOP_CENTER);
        battlePane.setOrientation(Orientation.VERTICAL);


        return battleMenu;
    }

}
