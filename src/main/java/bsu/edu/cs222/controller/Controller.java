package bsu.edu.cs222.controller;

import bsu.edu.cs222.model.*;
import bsu.edu.cs222.view.Menu;
import bsu.edu.cs222.view.UserInput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controller {
    private final Menu menu = new Menu();
    private final GetDataFromJSON getDataFromJSON = new GetDataFromJSON();
    private final InputModifier inputModifier = new InputModifier();
    private final MapSearcher searcher = new MapSearcher();
    private final ReadFromCountryLink readFromCountryLink = new ReadFromCountryLink();
    private final SearchCorrection searchCorrection = new SearchCorrection();
    private final Errors errors = new Errors();
    private final IsoRandom isoRandom = new IsoRandom();
    private final UserInput userInput = new UserInput();
    private final BattleMode battleMode = new BattleMode();

    final String file = "src/main/resources/allCountryBasic.json";

    public Map<String, String> getCountryISOMap(){
        List<String> isoJSON = handleJson();
        return getDataFromJSON.mapISO2Codes(isoJSON);
    }

    public List<String> getCountryISOList()  {
        List<String> isoJSON = handleJson();
        return getDataFromJSON.listISO2Codes(isoJSON);
    }

    private List<String> handleJson(){
        String json;
        List<String> isoJSON = new ArrayList<>();
        try{
            json = new String(Files.readAllBytes(Paths.get(file)));
            isoJSON.add(json);

        } catch (IOException e){
            System.err.println("File reading failed, file: " + file);
            System.exit(4);
        }
        return isoJSON;
    }

    public void displayTitle() {
        menu.createTitleMenu();
    }

    public String getUserSelection() {
        return menu.selection();
    }

    public String getUserSearch() throws IOException {
        return userInput.getCountry();
    }

    public String getISOCode(Map<String, String> ISOMap, String search) {
        return searcher.getISOCode(ISOMap, search);
    }

    public List<String> getFullBaseURL(String encodedSearchString) {
        return inputModifier.createURL(encodedSearchString);
    }

    public List<String> getFullIndicatorURLs(String encodedSearchString) {
        return inputModifier.createIndicatorURLS(encodedSearchString);
    }

    public List<String> getJSON(List<String> urls) {
        return readFromCountryLink.asyncHttp(urls);
    }

    public List<String> getCountryBaseList(List<String> returnedJSON)  {
        return getDataFromJSON.countryBaseData(returnedJSON);
    }

    public List<String> getCountryBaseListFX(List<String> returnedJSON)  {
        return getDataFromJSON.countryBaseDataFX(returnedJSON);
    }

    public List<String> getCountryIndicatorList(List<String> returnedJSON)  {
        return getDataFromJSON.countryIndicators(returnedJSON);
    }

    public List<String> getCountryIndicatorListFX(List<String> returnedJSON)  {
        return getDataFromJSON.countryIndicatorsFX(returnedJSON);
    }

    public void displayData(List<String> baseData, List<String> indicatorData) {
        menu.createSingleOutput(baseData, indicatorData);
    }

    public void displayComparisonData(List<String> baseData, List<String> indicatorData, List<String> baseDataTwo, List<String> indicatorDataTwo)
    {
        menu.createComparisonOutput(baseData,indicatorData,baseDataTwo,indicatorDataTwo);
    }

    public String rePrompt() {
        return menu.rePrompt();
    }

    public Boolean checkNoInput(String search) {
        return errors.checkNoInput(search);
    }

    public List<String> checkMisspelling(String search) throws IOException {
        return searchCorrection.countrySuggester(search);
    }

    public String getUserMisspellingChoice(List<String> countrySuggestions) {
        return menu.getUserDidYouMean(countrySuggestions);
    }

    public Map<String, String> getCountryRegionMap() {
        List<String> regionJSON = handleJson();
        return getDataFromJSON.mapRegions(regionJSON);
    }

    public String getNameFromMap(String iso) {
        Map<String,String> isoMap = getCountryISOMap();
        return searcher.getName(isoMap, iso);
    }

    public List<String> getBattleModeWinner(List<String> isoCodes) {
        return battleMode.getTopFour(isoCodes);
    }

    public void displayBattleModeNames(List<String> topFourNames) {
        menu.displayBattleModeNames(topFourNames);
    }

    public void displayBattleModeBracket(List<String> topFourISO) {
        menu.displayBattleModeBracket(topFourISO);
    }

    public String getRandomISOCode(List<String> isoList) {
        return isoRandom.getRandomISOCode(isoList);
    }

}
