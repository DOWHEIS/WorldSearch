package bsu.edu.cs222.view;

import bsu.edu.cs222.controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FXCountryData {
    //Building listviews for country data
    public ListView<String> buildCountryData(String isoCode) {
        List<String> countryInfo = new ArrayList<>();

        List<String> countryBaseData = getBaseCountryData(isoCode);
        List<String> countryIndicatorData = getIndicatorCountryData(isoCode);

        if (Objects.equals(countryBaseData.get(0), "No Data Reported")) {
            for (int i = 0; i < 3; i++) {
                countryBaseData.add("No Data Reported");
            }
            for (int i = 0; i < 11; i++) {
                countryIndicatorData.add("No Data Reported");
            }
        }

        countryInfo.addAll(countryBaseData);
        countryInfo.addAll(countryIndicatorData);

        return buildCountryListView(countryInfo);
    }

    private static List<String> getBaseCountryData(String isoCode) {
        Controller controller = new Controller();

        List<String> baseURL = controller.getFullBaseURL(isoCode);
        List<String> baseJSON = controller.getJSON(baseURL);

        return controller.getCountryBaseListFX(baseJSON);
    }

    private static List<String> getIndicatorCountryData(String isoCode) {
        Controller controller = new Controller();

        List<String> indicatorURLS = controller.getFullIndicatorURLs(isoCode);
        List<String> indicatorJSON = controller.getJSON(indicatorURLS);

        return controller.getCountryIndicatorListFX(indicatorJSON);
    }

    public ListView<String> getFullNamesListView() {
        List<String> fullNames = new ArrayList<>(Arrays.asList(" Name ", " Region ", " Income Level ", " Capital City ", " GDP ", " Population ", " Population Growth ", " Population Density ", " Death Rate per 1k ", " Unemployment Rate ", " Poverty Rate ($1.90/day) ", " Homicides Per 100k People ", " School Enrollment Rate ", " Average Income ", " Life Expectancy ", " Land Area (sq.km)"));
        ListView<String> fullValueNames = buildCountryListView(fullNames);
        fullValueNames.setMaxSize(250, 500);
        return fullValueNames;
    }

    private ListView<String> buildCountryListView(List<String> countryInfo) {
        ObservableList<String> cData = FXCollections.observableArrayList(countryInfo);
        ListView<String> listView = new ListView<>(cData);
        listView.getStyleClass().add("list-view");
        listView.setMaxSize(230, 500);
        return listView;
    }
}
