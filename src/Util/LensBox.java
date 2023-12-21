package Util;

import java.util.ArrayList;
import java.util.List;

public class LensBox {

    private final List<Lens> lenses;

    public LensBox() {
        lenses = new ArrayList<>();
    }

    public void insertLens(Lens newLens) {
        int indexOfLens = getIndexOfLens(newLens.getLabel());
        if (indexOfLens != -1) {
            lenses.set(indexOfLens, newLens);
        } else {
            lenses.add(newLens);
        }
    }

    public void removeLens(String lensLabel) {
        int indexOfLens = getIndexOfLens(lensLabel);
        if (indexOfLens != -1) {
            lenses.remove(indexOfLens);
        }
    }

    private int getIndexOfLens(String lensLabel) {
        return lenses.stream().map(Lens::getLabel).toList().indexOf(lensLabel);
    }

    public long getFocusingPower() {
        long power = 0;
        for (int slot = 1 ; slot <= lenses.size(); slot++) {
            power += (long) slot * lenses.get(slot-1).getFocalLength();
        }
        return power;
    }
}
