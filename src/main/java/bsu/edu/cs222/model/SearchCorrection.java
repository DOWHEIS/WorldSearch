package bsu.edu.cs222.model;


import org.apache.commons.text.similarity.LevenshteinDistance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SearchCorrection {

    private ArrayList<String> loadCountries(String filename) throws IOException {
        ArrayList<String> countryList = new ArrayList<>();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(filename));
        while ((line = br.readLine()) != null) {
            if (line.length() > 2 && line.length() < 100)
                countryList.add(line.toLowerCase(Locale.ROOT));
        }
        return countryList;
    }

    public List<String> countrySuggester(String search) throws IOException {
        String countryFile = "src/main/resources/countryDictionary.txt";

        HashMap<String, Integer> newList = new HashMap<>();
        ArrayList<String> countryList;
        int distance;
        countryList = (loadCountries(countryFile));
        for (String s : countryList) {
            LevenshteinDistance compare = new LevenshteinDistance();
            distance = compare.apply(s, search);
            if (distance >= 1 && distance < 1000) {
                newList.put(s, distance);
            } else if (distance == 0) {
                List<String> country = new ArrayList<>();
                country.add(search);
                return country;
            }

        }
        if (!newList.isEmpty()) {
            List<Map.Entry<String, Integer>> entries = new ArrayList<>(newList.entrySet());
            entries.sort(Map.Entry.comparingByValue());
            Map<String, Integer> orderedMap = orderMap(entries);
            return getTopResults(orderedMap);
        } else {
            List<String> noVal = new ArrayList<>();
            noVal.add("noVal");
            return noVal;
        }

    }

    private Map<String, Integer> orderMap(List<Map.Entry<String, Integer>> entries) {
        Map<String, Integer> orderedMap = new LinkedHashMap<>();
        for (int i = 0; i < 3; i++) {
            Map.Entry<String, Integer> entry = entries.get(i);
            orderedMap.put(entry.getKey(), entry.getValue());
        }
        return orderedMap;
    }

    private List<String> getTopResults(Map<String, Integer> orderedMap) {
        List<String> topResults = new ArrayList<>();
        for (Map.Entry<String, Integer> result : orderedMap.entrySet()) {
            topResults.add(result.getKey().toLowerCase(Locale.ROOT));
        }
        return topResults;
    }

}

