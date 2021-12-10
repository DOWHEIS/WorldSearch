package bsu.edu.cs222;

import bsu.edu.cs222.controller.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();

        List<String> ISOList;
        ISOList = controller.getCountryISOList();
        controller.displayTitle();
        while (true) {
            String isoCode;
            String selection = controller.getUserSelection();
            switch (selection) {
                case "Single Country" -> {
                    while (true) {
                        isoCode = handleSearch();

                        if (Objects.equals(isoCode, "none")) {
                            break;
                        }
                        handleSingleCountryDisplay(isoCode);

                        String userResponse = controller.rePrompt();
                        if (Objects.equals(userResponse, "Exit")) {
                            break;
                        }
                    }

                }
                case "Country Comparison" -> {
                    while (true) {
                        isoCode = handleSearch();

                        if (Objects.equals(isoCode, "none")) {
                            break;
                        }

                        List<String> baseData = getBaseCountryData(isoCode);
                        List<String> indicatorData = getIndicatorCountryData(isoCode);

                        String isoCodeTwo;
                        isoCodeTwo = handleSearch();

                        if (Objects.equals(isoCodeTwo, "none")) {
                            break;
                        }

                        List<String> baseDataTwo = getBaseCountryData(isoCodeTwo);
                        List<String> indicatorDataTwo = getIndicatorCountryData(isoCodeTwo);

                        controller.displayComparisonData(baseData, indicatorData, baseDataTwo, indicatorDataTwo);

                        String userResponse = controller.rePrompt();
                        if (Objects.equals(userResponse, "Exit")) {
                            break;
                        }
                    }

                }

                case "Random Country" -> {
                    while (true) {
                        isoCode = controller.getRandomISOCode(ISOList);

                        handleSingleCountryDisplay(isoCode);

                        String userResponse = controller.rePrompt();
                        if (Objects.equals(userResponse, "Exit")) {
                            break;
                        }
                    }

                }

                case "Random Country Comparison" -> {
                    while (true) {
                        isoCode = controller.getRandomISOCode(ISOList);

                        List<String> baseData = getBaseCountryData(isoCode);
                        List<String> indicatorData = getIndicatorCountryData(isoCode);

                        String isoCodeTwo;
                        isoCodeTwo = controller.getRandomISOCode(ISOList);


                        List<String> baseDataTwo = getBaseCountryData(isoCodeTwo);
                        List<String> indicatorDataTwo = getIndicatorCountryData(isoCodeTwo);

                        controller.displayComparisonData(baseData, indicatorData, baseDataTwo, indicatorDataTwo);

                        String userResponse = controller.rePrompt();
                        if (Objects.equals(userResponse, "Exit")) {
                            break;
                        }
                    }
                }

                case "Battle Mode" -> {
                    List<String> topFourISO = controller.getBattleModeWinner(controller.getCountryISOList());
                    List<String> topFourNames = new ArrayList<>();
                    List<String> baseData = new ArrayList<>();
                    for (int i = 0; i < 4; i++) {
                        String iso = topFourISO.get(i);
                        baseData.add(getBaseCountryData(iso).get(0));
                        String name = baseData.get(i);
                        topFourNames.add(name);
                    }
                    controller.displayBattleModeBracket(topFourISO);
                    controller.displayBattleModeNames(topFourNames);
                }

                case "quit" -> System.exit(0);
            }
        }
    }

    private static void handleSingleCountryDisplay(String isoCode) {
        Controller controller = new Controller();

        List<String> baseData = getBaseCountryData(isoCode);
        List<String> indicatorData = getIndicatorCountryData(isoCode);

        controller.displayData(baseData, indicatorData);
    }

    private static String getISOCode(List<String> correctedSearchList, String search) {
        Controller controller = new Controller();
        Map<String, String> ISOMap;
        ISOMap = controller.getCountryISOMap();

        if (correctedSearchList.size() == 1) {
            return controller.getISOCode(ISOMap, search);
        } else {
            String correctedSearch = controller.getUserMisspellingChoice(correctedSearchList);
            if (Objects.equals(correctedSearch, "none")) {
                return "none";
            } else {
                return controller.getISOCode(ISOMap, correctedSearch);
            }
        }
    }

    private static String handleSearch() throws IOException {
        Controller controller = new Controller();

        String search = controller.getUserSearch();

        List<String> correctedSearchList = controller.checkMisspelling(search);

        return getISOCode(correctedSearchList, search);
    }

    private static List<String> getBaseCountryData(String isoCode) {
        Controller controller = new Controller();
        List<String> baseURL = controller.getFullBaseURL(isoCode);
        List<String> baseJSON = controller.getJSON(baseURL);

        return controller.getCountryBaseList(baseJSON);
    }

    private static List<String> getIndicatorCountryData(String isoCode) {
        Controller controller = new Controller();

        List<String> indicatorURLS = controller.getFullIndicatorURLs(isoCode);
        List<String> indicatorJSON = controller.getJSON(indicatorURLS);

        return controller.getCountryIndicatorList(indicatorJSON);
    }
}

