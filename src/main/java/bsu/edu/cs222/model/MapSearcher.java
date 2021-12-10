package bsu.edu.cs222.model;

import java.util.Map;

public class MapSearcher {
    public String getISOCode(Map<String, String> ISOMap, String search) {
        String isoCode;
        if (ISOMap.containsKey(search)) {
            isoCode = ISOMap.get(search);
        } else {
            return "noVal";
        }
        return isoCode;
    }

    public String getName(Map<String, String> ISOMap, String isoCode) {
        String name = "No Value in Database";
        for (Map.Entry<String, String> entry : ISOMap.entrySet()) {
            if (entry.getValue().equals(isoCode)) {
                name = entry.getKey();
            }
        }
        return name;
    }
}
