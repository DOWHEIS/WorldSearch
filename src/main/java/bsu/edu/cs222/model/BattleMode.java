package bsu.edu.cs222.model;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BattleMode {
    public List<String> getTopFour(List<String> isoCodes) {
        IsoRandom isoRandom = new IsoRandom();

        String isoOne = isoRandom.getRandomISOCode(isoCodes);
        String isoTwo = isoRandom.getRandomISOCode(isoCodes);

        int randomInt = ThreadLocalRandom.current().nextInt(0, 50);
        if(isoCodes.size() == 4){
            return isoCodes;
        }
        else if (randomInt % 2 == 0) {
            isoCodes.remove(isoOne);
        } else {
            isoCodes.remove(isoTwo);
        }
        getTopFour(isoCodes);
        return isoCodes;
    }



}
