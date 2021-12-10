package bsu.edu.cs222.view;

import bsu.edu.cs222.controller.Controller;
import javafx.scene.paint.Color;

import java.util.Map;

public class ColorWorld {
    public void colorWorld() {
        colorRegion(Color.web("#465A3D"), "North America");
        colorRegion(Color.web("#CBA42C"), "Latin America & Caribbean ");
        colorRegion(Color.web("#84342D"), "Europe & Central Asia");
        colorRegion(Color.web("#4E6172"), "East Asia & Pacific");
        colorRegion(Color.web("#4E4F72"), "South Asia");
        colorRegion(Color.web("#D57500"), "Middle East & North Africa");
        colorRegion(Color.web("#83929F"), "Sub-Saharan Africa ");
        colorNonWorldBankCountries();
    }

    private void colorNonWorldBankCountries() {
        CountryFX.GF.setColor(Color.web("#CBA42C"));
        CountryFX.SJ.setColor(Color.web("84342D"));
        CountryFX.AX.setColor(Color.web("84342D"));
        CountryFX.EH.setColor(Color.web("#83929F"));
    }

    private void colorRegion(Color regionColor, String region) {
        Controller controller = new Controller();
        Map<String, String> regionMap = controller.getCountryRegionMap();
        for (Map.Entry<String, String> entry : regionMap.entrySet()) {
            if (entry.getValue().equals(region)) {
                CountryFX.valueOf(entry.getKey()).setColor(regionColor);
            }
        }

    }
}
