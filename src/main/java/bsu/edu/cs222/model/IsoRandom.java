package bsu.edu.cs222.model;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class IsoRandom {
    public String getRandomISOCode(List<String> isoList) {
        int randomInt = ThreadLocalRandom.current().nextInt(0, isoList.size());
        return isoList.get(randomInt);
    }
}
