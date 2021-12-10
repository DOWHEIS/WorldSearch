import bsu.edu.cs222.model.CountryFull;
import bsu.edu.cs222.model.GetDataFromJSON;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class GetDataFromJSONTest {
    GetDataFromJSON getDataFromJSON = new GetDataFromJSON();

    @Test
    public void jsonMapperTestGDP()  {
        List<String> jsonData = new ArrayList<>();
        jsonData.add("[{\"page\": 1,\"pages\": 1,\"per_page\": 50,\"total\": 1,\"sourceid\": \"2\",\"sourcename\": \"World Development Indicators\",\"lastupdated\": \"2021-09-15\"},[{\"indicator\": {\"id\": \"VC.IHR.PSRC.P5\",\"value\": \"Intentional homicides (per 100,000 people)\"},\"country\": {\"id\": \"FR\",\"value\": \"France\"},\"countryiso3code\": \"FRA\",\"date\": \"2018\",\"value\": 1.1986365025,\"unit\": \"\",\"obs_status\": \"\",\"decimal\": 0}]]");
        jsonData.add("[{\"page\":1,\"pages\":1,\"per_page\":50,\"total\":1,\"sourceid\":\"2\",\"sourcename\":\"World Development Indicators\",\"lastupdated\":\"2021-09-15\"},[{\"indicator\":{\"id\":\"NY.GDP.MKTP.CD\",\"value\":\"GDP (current US$)\"},\"country\":{\"id\":\"FR\",\"value\":\"France\"},\"countryiso3code\":\"FRA\",\"date\":\"2020\",\"value\":2603004395901.95,\"unit\":\"\",\"obs_status\":\"\",\"decimal\":0}]]");
        List<CountryFull> countryFull = getDataFromJSON.jsonMapper(jsonData);
        String GDP = countryFull.get(1).country.get(0).value;
        Assertions.assertEquals("2603004395901.95", GDP);
    }

    @Test
    public void mapIso2CodesTest() throws IOException {
        List<String> jsonData = new ArrayList<>();
        String file = "src/test/resources/allCountryBasic.json";
        String json = new String(Files.readAllBytes(Paths.get(file)));
        jsonData.add(json);

        Map<String, String> map = getDataFromJSON.mapISO2Codes(jsonData);
        Assertions.assertEquals("FR", map.get("france"));
    }

    @Test public void listISO2CodesFirstIndex() throws IOException {
        List<String> jsonData = new ArrayList<>();
        String file = "src/test/resources/allCountryBasic.json";
        String json = new String(Files.readAllBytes(Paths.get(file)));
        jsonData.add(json);

        List<String> list = getDataFromJSON.listISO2Codes(jsonData);
        Assertions.assertEquals("AW", list.get(0));
    }

    @Test public void listISO2CodesSecondIndex() throws IOException {
        List<String> jsonData = new ArrayList<>();
        String file = "src/test/resources/allCountryBasic.json";
        String json = new String(Files.readAllBytes(Paths.get(file)));
        jsonData.add(json);

        List<String> list = getDataFromJSON.listISO2Codes(jsonData);
        Assertions.assertEquals("ZH", list.get(1));
    }

    @Test
    public void testCountryIndicatorsHomicide()  {
        List<String> jsonData = new ArrayList<>();
        jsonData.add("[{\"page\": 1,\"pages\": 1,\"per_page\": 50,\"total\": 1,\"sourceid\": \"2\",\"sourcename\": \"World Development Indicators\",\"lastupdated\": \"2021-09-15\"},[{\"indicator\": {\"id\": \"VC.IHR.PSRC.P5\",\"value\": \"Intentional homicides (per 100,000 people)\"},\"country\": {\"id\": \"FR\",\"value\": \"France\"},\"countryiso3code\": \"FRA\",\"date\": \"2018\",\"value\": 1.1986365025,\"unit\": \"\",\"obs_status\": \"\",\"decimal\": 0}]]");
        jsonData.add("[{\"page\":1,\"pages\":1,\"per_page\":50,\"total\":1,\"sourceid\":\"2\",\"sourcename\":\"World Development Indicators\",\"lastupdated\":\"2021-09-15\"},[{\"indicator\":{\"id\":\"NY.GDP.MKTP.CD\",\"value\":\"GDP (current US$)\"},\"country\":{\"id\":\"FR\",\"value\":\"France\"},\"countryiso3code\":\"FRA\",\"date\":\"2020\",\"value\":2603004395901.95,\"unit\":\"\",\"obs_status\":\"\",\"decimal\":0}]]");
        List<String> newList = getDataFromJSON.countryIndicators(jsonData);
        Assertions.assertEquals("1.1986365025" , newList.get(0));
    }

    @Test
    public void testCountryIndicatorsPopulation() {
        List<String> jsonData = new ArrayList<>();
        jsonData.add("[{\"page\":1,\"pages\":1,\"per_page\":50,\"total\":1,\"sourceid\":\"2\",\"sourcename\":\"World Development Indicators\",\"lastupdated\":\"2021-10-28\"},[{\"indicator\":{\"id\":\"SP.POP.TOTL\",\"value\":\"Population, total\"},\"country\":{\"id\":\"CA\",\"value\":\"Canada\"},\"countryiso3code\":\"CAN\",\"date\":\"2020\",\"value\":38005238,\"unit\":\"\",\"obs_status\":\"\",\"decimal\":0}]]");
        List<String> newList = getDataFromJSON.countryIndicators(jsonData);
        Assertions.assertEquals("38005238", newList.get(0));
    }

    @Test
    public void testCountryIndicatorsUnemploymentRate() {
        List<String> jsonData = new ArrayList<>();
        jsonData.add("[{\"page\":1,\"pages\":1,\"per_page\":50,\"total\":1,\"sourceid\":\"2\",\"sourcename\":\"World Development Indicators\",\"lastupdated\":\"2021-10-28\"},[{\"indicator\":{\"id\":\"SL.UEM.TOTL.ZS\",\"value\":\"Unemployment, total (% of total labor force) (modeled ILO estimate)\"},\"country\":{\"id\":\"CA\",\"value\":\"Canada\"},\"countryiso3code\":\"CAN\",\"date\":\"2020\",\"value\":9.48,\"unit\":\"\",\"obs_status\":\"\",\"decimal\":1}]]");
        List<String> newList = getDataFromJSON.countryIndicators(jsonData);
        Assertions.assertEquals("9.48", newList.get(0));
    }

    @Test
    public void testCountryIndicatorsSchoolEnrollment()  {
        List<String> jsonData = new ArrayList<>();
        jsonData.add("[{\"page\":1,\"pages\":1,\"per_page\":50,\"total\":1,\"sourceid\":\"2\",\"sourcename\":\"World Development Indicators\",\"lastupdated\":\"2021-10-28\"},[{\"indicator\":{\"id\":\"SE.PRM.TENR\",\"value\":\"Adjusted net enrollment rate, primary (% of primary school age children)\"},\"country\":{\"id\":\"CA\",\"value\":\"Canada\"},\"countryiso3code\":\"CAN\",\"date\":\"2016\",\"value\":99.95587,\"unit\":\"\",\"obs_status\":\"\",\"decimal\":0}]]");
        List<String> newList = getDataFromJSON.countryIndicators(jsonData);
        Assertions.assertEquals("99.95587", newList.get(0));
    }

    @Test
    public void testCountryIndicatorsNetNationalIncome()  {
        List<String> jsonData = new ArrayList<>();
        jsonData.add("[{\"page\":1,\"pages\":1,\"per_page\":50,\"total\":1,\"sourceid\":\"2\",\"sourcename\":\"World Development Indicators\",\"lastupdated\":\"2021-10-28\"},[{\"indicator\":{\"id\":\"NY.ADJ.NNTY.PC.CD\",\"value\":\"Adjusted net national income per capita (current US$)\"},\"country\":{\"id\":\"CA\",\"value\":\"Canada\"},\"countryiso3code\":\"CAN\",\"date\":\"2019\",\"value\":37829.4827350539,\"unit\":\"\",\"obs_status\":\"\",\"decimal\":0}]]");
        List<String> newList = getDataFromJSON.countryIndicators(jsonData);
        Assertions.assertEquals("37829.4827350539", newList.get(0));
    }

    @Test
    public void testCountryIndicatorsPovertyHeadCount()  {
        List<String> jsonData = new ArrayList<>();
        jsonData.add("[{\"page\":1,\"pages\":1,\"per_page\":50,\"total\":1,\"sourceid\":\"2\",\"sourcename\":\"World Development Indicators\",\"lastupdated\":\"2021-11-23\"},[{\"indicator\":{\"id\":\"SI.POV.DDAY\",\"value\":\"Poverty headcount ratio at $1.90 a day (2011 PPP) (% of population)\"},\"country\":{\"id\":\"BR\",\"value\":\"Brazil\"},\"countryiso3code\":\"BRA\",\"date\":\"2019\",\"value\":4.6,\"unit\":\"\",\"obs_status\":\"\",\"decimal\":1}]]");
        List<String> newList = getDataFromJSON.countryIndicators(jsonData);
        Assertions.assertEquals("4.6", newList.get(0));
    }

    @Test
    public void testCountryIndicatorsIntentionalHomicidesPer100kPpl()  {
        List<String> jsonData = new ArrayList<>();
        jsonData.add("[{\"page\":1,\"pages\":1,\"per_page\":50,\"total\":1,\"sourceid\":\"2\",\"sourcename\":\"World Development Indicators\",\"lastupdated\":\"2021-11-23\"},[{\"indicator\":{\"id\":\"VC.IHR.PSRC.P5\",\"value\":\"Intentional homicides (per 100,000 people)\"},\"country\":{\"id\":\"BR\",\"value\":\"Brazil\"},\"countryiso3code\":\"BRA\",\"date\":\"2018\",\"value\":27.382530291,\"unit\":\"\",\"obs_status\":\"\",\"decimal\":0}]]");
        List<String> newList = getDataFromJSON.countryIndicators(jsonData);
        Assertions.assertEquals("27.382530291", newList.get(0));
    }

    @Test
    public void testCountryIndicatorsAdjustedNetEnrollmentRate()  {
        List<String> jsonData = new ArrayList<>();
        jsonData.add("[{\"page\":1,\"pages\":1,\"per_page\":50,\"total\":1,\"sourceid\":\"2\",\"sourcename\":\"World Development Indicators\",\"lastupdated\":\"2021-11-23\"},[{\"indicator\":{\"id\":\"SE.PRM.TENR\",\"value\":\"Adjusted net enrollment rate, primary (% of primary school age children)\"},\"country\":{\"id\":\"BR\",\"value\":\"Brazil\"},\"countryiso3code\":\"BRA\",\"date\":\"2017\",\"value\":97.55117,\"unit\":\"\",\"obs_status\":\"\",\"decimal\":0}]]");
        List<String> newList = getDataFromJSON.countryIndicators(jsonData);
        Assertions.assertEquals("97.55117", newList.get(0));
    }

    @Test
    public void testCountryIndicatorsAdjustedNetIncomeInflation() {
        List<String> jsonData = new ArrayList<>();
        jsonData.add("[{\"page\":1,\"pages\":1,\"per_page\":50,\"total\":1,\"sourceid\":\"2\",\"sourcename\":\"World Development Indicators\",\"lastupdated\":\"2021-11-23\"},[{\"indicator\":{\"id\":\"NY.ADJ.NNTY.PC.CD\",\"value\":\"Adjusted net national income per capita (current US$)\"},\"country\":{\"id\":\"BR\",\"value\":\"Brazil\"},\"countryiso3code\":\"BRA\",\"date\":\"2019\",\"value\":7628.82844968616,\"unit\":\"\",\"obs_status\":\"\",\"decimal\":0}]]");
        List<String> newList = getDataFromJSON.countryIndicators(jsonData);
        Assertions.assertEquals("7628.82844968616", newList.get(0));
    }

    @Test
    public void testCountryBaseDataIncomeLevel() throws IOException {
        List<String> jsonData = new ArrayList<>();
        String file = "src/test/resources/allCountryBasic.json";
        String json = new String(Files.readAllBytes(Paths.get(file)));
        jsonData.add(json);
        List<String> newList = getDataFromJSON.countryBaseData(jsonData);
        Assertions.assertEquals("High income", newList.get(2));
    }

    @Test
    public void testCountryBaseDataName() throws IOException{
        List<String> jsonData = new ArrayList<>();
        String file = "src/test/resources/allCountryBasic.json";
        String json = new String(Files.readAllBytes(Paths.get(file)));
        jsonData.add(json);
        List<String> newList = getDataFromJSON.countryBaseData(jsonData);
        Assertions.assertEquals("Aruba", newList.get(0));
    }

    @Test
    public void testCountryBaseDataRegion() throws IOException{
        List<String> jsonData = new ArrayList<>();
        String file = "src/test/resources/allCountryBasic.json";
        String json = new String(Files.readAllBytes(Paths.get(file)));
        jsonData.add(json);
        List<String> newList = getDataFromJSON.countryBaseData(jsonData);
        Assertions.assertEquals("Latin America & Caribbean ", newList.get(1));
    }

    @Test
    public void testCountryBaseDataCapital() throws IOException{
        List<String> jsonData = new ArrayList<>();
        String file = "src/test/resources/allCountryBasic.json";
        String json = new String(Files.readAllBytes(Paths.get(file)));
        jsonData.add(json);
        List<String> newList = getDataFromJSON.countryBaseData(jsonData);
        Assertions.assertEquals("Oranjestad", newList.get(3));
    }

    @Test
    public void testCountryIndicatorFxPopulation() {
        List<String> jsonData = new ArrayList<>();
        jsonData.add("[{\"page\":1,\"pages\":1,\"per_page\":50,\"total\":1,\"sourceid\":\"2\",\"sourcename\":\"World Development Indicators\",\"lastupdated\":\"2021-10-28\"},[{\"indicator\":{\"id\":\"SP.POP.TOTL\",\"value\":\"Population, total\"},\"country\":{\"id\":\"CA\",\"value\":\"Canada\"},\"countryiso3code\":\"CAN\",\"date\":\"2020\",\"value\":38005238,\"unit\":\"\",\"obs_status\":\"\",\"decimal\":0}]]");
        List<String> newList = getDataFromJSON.countryIndicatorsFX(jsonData);
        Assertions.assertEquals("$38,005,238.00 (2020)", newList.get(0));
    }

    @Test
    public void testCountryIndicatorFXHomicide() {
        List<String> jsonData = new ArrayList<>();
        jsonData.add("[{\"page\": 1,\"pages\": 1,\"per_page\": 50,\"total\": 1,\"sourceid\": \"2\",\"sourcename\": \"World Development Indicators\",\"lastupdated\": \"2021-09-15\"},[{\"indicator\": {\"id\": \"VC.IHR.PSRC.P5\",\"value\": \"Intentional homicides (per 100,000 people)\"},\"country\": {\"id\": \"FR\",\"value\": \"France\"},\"countryiso3code\": \"FRA\",\"date\": \"2018\",\"value\": 1.1986365025,\"unit\": \"\",\"obs_status\": \"\",\"decimal\": 0}]]");
        jsonData.add("[{\"page\":1,\"pages\":1,\"per_page\":50,\"total\":1,\"sourceid\":\"2\",\"sourcename\":\"World Development Indicators\",\"lastupdated\":\"2021-09-15\"},[{\"indicator\":{\"id\":\"NY.GDP.MKTP.CD\",\"value\":\"GDP (current US$)\"},\"country\":{\"id\":\"FR\",\"value\":\"France\"},\"countryiso3code\":\"FRA\",\"date\":\"2020\",\"value\":2603004395901.95,\"unit\":\"\",\"obs_status\":\"\",\"decimal\":0}]]");
        List<String> newList = getDataFromJSON.countryIndicatorsFX(jsonData);
        Assertions.assertEquals("$1.20 (2018)", newList.get(0));
    }
    @Test
    public void testCountryIndicatorFXUnemploymentRate() {
        List<String> jsonData = new ArrayList<>();
        jsonData.add("[{\"page\":1,\"pages\":1,\"per_page\":50,\"total\":1,\"sourceid\":\"2\",\"sourcename\":\"World Development Indicators\",\"lastupdated\":\"2021-10-28\"},[{\"indicator\":{\"id\":\"SL.UEM.TOTL.ZS\",\"value\":\"Unemployment, total (% of total labor force) (modeled ILO estimate)\"},\"country\":{\"id\":\"CA\",\"value\":\"Canada\"},\"countryiso3code\":\"CAN\",\"date\":\"2020\",\"value\":9.48,\"unit\":\"\",\"obs_status\":\"\",\"decimal\":1}]]");
        List<String> newList = getDataFromJSON.countryIndicatorsFX(jsonData);
        Assertions.assertEquals("$9.48 (2020)", newList.get(0));
    }

    @Test
    public void testCountryIndicatorFXSchoolEnrollment() {
        List<String> jsonData = new ArrayList<>();
        jsonData.add("[{\"page\":1,\"pages\":1,\"per_page\":50,\"total\":1,\"sourceid\":\"2\",\"sourcename\":\"World Development Indicators\",\"lastupdated\":\"2021-10-28\"},[{\"indicator\":{\"id\":\"SE.PRM.TENR\",\"value\":\"Adjusted net enrollment rate, primary (% of primary school age children)\"},\"country\":{\"id\":\"CA\",\"value\":\"Canada\"},\"countryiso3code\":\"CAN\",\"date\":\"2016\",\"value\":99.95587,\"unit\":\"\",\"obs_status\":\"\",\"decimal\":0}]]");
        List<String> newList = getDataFromJSON.countryIndicatorsFX(jsonData);
        Assertions.assertEquals("$99.96 (2016)", newList.get(0));
    }

    @Test
    public void testCountryIndicatorFXNetNationalIncome() {
        List<String> jsonData = new ArrayList<>();
        jsonData.add("[{\"page\":1,\"pages\":1,\"per_page\":50,\"total\":1,\"sourceid\":\"2\",\"sourcename\":\"World Development Indicators\",\"lastupdated\":\"2021-10-28\"},[{\"indicator\":{\"id\":\"NY.ADJ.NNTY.PC.CD\",\"value\":\"Adjusted net national income per capita (current US$)\"},\"country\":{\"id\":\"CA\",\"value\":\"Canada\"},\"countryiso3code\":\"CAN\",\"date\":\"2019\",\"value\":37829.4827350539,\"unit\":\"\",\"obs_status\":\"\",\"decimal\":0}]]");
        List<String> newList = getDataFromJSON.countryIndicatorsFX(jsonData);
        Assertions.assertEquals("$37,829.48 (2019)", newList.get(0));
    }

    @Test
    public void testCountryBaseDataFXIncomeLevel() throws IOException {
        List<String> jsonData = new ArrayList<>();
        String file = "src/test/resources/allCountryBasic.json";
        String json = new String(Files.readAllBytes(Paths.get(file)));
        jsonData.add(json);
        List<String> newList = getDataFromJSON.countryBaseDataFX(jsonData);
        Assertions.assertEquals("High income", newList.get(2));
    }

    @Test
    public void testCountryBaseDataFXName() throws IOException{
        List<String> jsonData = new ArrayList<>();
        String file = "src/test/resources/allCountryBasic.json";
        String json = new String(Files.readAllBytes(Paths.get(file)));
        jsonData.add(json);
        List<String> newList = getDataFromJSON.countryBaseDataFX(jsonData);
        Assertions.assertEquals("Aruba", newList.get(0));
    }

    @Test
    public void testCountryBaseDataFXRegion() throws IOException{
        List<String> jsonData = new ArrayList<>();
        String file = "src/test/resources/allCountryBasic.json";
        String json = new String(Files.readAllBytes(Paths.get(file)));
        jsonData.add(json);
        List<String> newList = getDataFromJSON.countryBaseDataFX(jsonData);
        Assertions.assertEquals("Latin America & Caribbean ", newList.get(1));
    }

    @Test
    public void testCountryBaseDataFXCapital() throws IOException{
        List<String> jsonData = new ArrayList<>();
        String file = "src/test/resources/allCountryBasic.json";
        String json = new String(Files.readAllBytes(Paths.get(file)));
        jsonData.add(json);
        List<String> newList = getDataFromJSON.countryBaseDataFX(jsonData);
        Assertions.assertEquals("Oranjestad", newList.get(3));
    }

    @Test
    public void testMapRegions() throws IOException{
        List<String> jsonData = new ArrayList<>();
        String file = "src/test/resources/allCountryBasic.json";
        String json = new String(Files.readAllBytes(Paths.get(file)));
        jsonData.add(json);

        Map<String, String> map = getDataFromJSON.mapRegions(jsonData);
        Assertions.assertEquals("Latin America & Caribbean ", map.get("BR"));
    }
}
