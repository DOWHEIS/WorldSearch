package bsu.edu.cs222.model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

public class GetDataFromJSON {
    private static final int TOTAL_COUNTRIES = 218;
    private static final String BAD_ISO = "[{\"message\":[{\"id\":\"120\",\"key\":\"Invalid value\",\"value\":\"The provided parameter value is not valid\"}]}]";


    public List<CountryFull> jsonMapper(List<String> returnedJSON) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<CountryFull> countryFullList = new ArrayList<>();
        try{
            for (String object : returnedJSON) {
                countryFullList.add(objectMapper.readValue(object, CountryFull.class));
            }
        } catch (IOException e){
            System.err.println("Object Mapping Failed");
            System.exit(5);
        }

        return countryFullList;
    }

    public List<String> countryBaseData(List<String> returnedJSON) {
        List<String> countryData = new ArrayList<>();
        List<CountryFull> countryFull = jsonMapper(returnedJSON);
        for (int i = 0; i < returnedJSON.size(); i++) {
            countryData.add(countryFull.get(i).country.get(0).name);
            countryData.add(countryFull.get(i).country.get(0).region.value);
            countryData.add(countryFull.get(i).country.get(0).incomeLevel.value);
            if (Objects.equals(countryFull.get(i).country.get(0).capitalCity, "")) {
                countryData.add("noVal");
                countryData.add("");
            } else {
                countryData.add(countryFull.get(i).country.get(0).capitalCity);
            }
        }


        return countryData;
    }

    public List<String> countryBaseDataFX(List<String> returnedJSON) {
        List<String> countryData = new ArrayList<>();
        List<CountryFull> countryFull = jsonMapper(returnedJSON);
        if (returnedJSON.get(0).equals(BAD_ISO)) {
            countryData.add("No Data Reported");
        } else {
            for (int i = 0; i < returnedJSON.size(); i++) {
                countryData.add(countryFull.get(i).country.get(0).name);
                countryData.add(countryFull.get(i).country.get(0).region.value);
                countryData.add(countryFull.get(i).country.get(0).incomeLevel.value);
                if (Objects.equals(countryFull.get(i).country.get(0).capitalCity, "")) {
                    countryData.add("No Value Reported");
                } else {
                    countryData.add(countryFull.get(i).country.get(0).capitalCity);
                }
            }
        }

        return countryData;
    }

    public List<String> countryIndicators(List<String> returnedJSON) {
        List<String> countryData = new ArrayList<>();
        List<CountryFull> countryFull = jsonMapper(returnedJSON);
        for (CountryFull country : countryFull) {
            if (country.pagination.page == 0) {
                countryData.add("noVal");
                countryData.add("");
            } else {
                countryData.add(country.country.get(0).value);
                countryData.add(country.country.get(0).date);
            }
        }
        return countryData;
    }

    private DecimalFormat setupDecimalFormat() {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setGroupingUsed(true);
        df.setGroupingSize(3);
        return df;
    }

    public List<String> countryIndicatorsFX(List<String> returnedJSON) {
        List<String> countryDataFX = new ArrayList<>();
        List<CountryFull> countryFull = jsonMapper(returnedJSON);

        DecimalFormat df = setupDecimalFormat();

        if (returnedJSON.get(0).equals(BAD_ISO)) {
            countryDataFX.add("No Data Reported");
        } else {
            for (int i = 0; i < countryFull.size(); i++) {
                if (countryFull.get(i).pagination.page == 0) {
                    countryDataFX.add("No Value Reported");
                } else if (i == 0 || i == 9){
                    countryDataFX.add("$" + df.format(new BigDecimal(countryFull.get(i).country.get(0).value)) + " (" + countryFull.get(i).country.get(0).date + ")");
                } else if (i == 2 || i == 5 || i == 6 || i == 8){
                    countryDataFX.add(df.format(new BigDecimal(countryFull.get(i).country.get(0).value)) + "% (" + countryFull.get(i).country.get(0).date + ")");
                } else {
                    countryDataFX.add(df.format(new BigDecimal(countryFull.get(i).country.get(0).value)) + " (" + countryFull.get(i).country.get(0).date + ")");
                }
            }
        }

        return countryDataFX;
    }

    public Map<String, String> mapISO2Codes(List<String> returnedJSON) {
        List<CountryFull> countryFull = jsonMapper(returnedJSON);
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < TOTAL_COUNTRIES; i++) {
            String name = countryFull.get(0).country.get(i).name.toLowerCase(Locale.ROOT);
            String iso2Code = countryFull.get(0).country.get(i).iso2Code;
            map.put(name, iso2Code);
        }
        return map;
    }

    public List<String> listISO2Codes(List<String> returnedJSON)  {
        List<CountryFull> countryFull = jsonMapper(returnedJSON);
        List<String> iso2Codes = new ArrayList<>();
        for (int i = 0; i < TOTAL_COUNTRIES; i++) {
            String iso2Code = countryFull.get(0).country.get(i).iso2Code;
            iso2Codes.add(iso2Code);
        }
        return iso2Codes;
    }

    public Map<String, String> mapRegions(List<String> returnedJSON)  {
        List<CountryFull> countryFull = jsonMapper(returnedJSON);
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < TOTAL_COUNTRIES; i++) {
            String iso = countryFull.get(0).country.get(i).iso2Code;
            String region = countryFull.get(0).country.get(i).region.value;
            map.put(iso, region);
        }
        return map;
    }

}
