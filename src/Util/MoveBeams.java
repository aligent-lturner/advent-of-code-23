package Util;

import java.util.*;

public class MoveBeams {

    public static List<Beam> execute(
            List<Beam> beams,
            Map<Coordinates, Character> symbolMap,
            Set<Coordinates> energizedSet
    ) {
        beams.forEach(beam -> energizedSet.add(beam.getCurrentPosition()));
        List<Beam> finalBeams = new ArrayList<>(beams);
        beams.forEach(beam -> finalBeams.addAll(applySymbol(beam, symbolMap.get(beam.getCurrentPosition()))));
        beams = finalBeams;
        int maxX = getMaxX(symbolMap.keySet());
        int maxY = getMaxY(symbolMap.keySet());
        beams.forEach(Beam::move);
        beams = beams.stream().
                filter(
                        beam -> beam.getCurrentPosition().getX() >= 1 &&
                                beam.getCurrentPosition().getY() >= 1 &&
                                beam.getCurrentPosition().getX() <= maxX &&
                                beam.getCurrentPosition().getY() <= maxY
                ).toList();

        return beams;
    }

    private static List<Beam> applySymbol(Beam beam, char symbol) {
        List<Beam> newBeams = new ArrayList<>();
        Beam newBeam = beam.applySymbol(symbol);
        if (newBeam != null) {
            newBeams.add(newBeam);
        }
        return newBeams;
    }

    private static int getMaxX(Set<Coordinates> coordinatesSet) {
        int max = 0;
        for (Coordinates coordinates: coordinatesSet) {
            max = Math.max(max, coordinates.getX());
        }
        return max;
    }

    private static int getMaxY(Set<Coordinates> coordinatesSet) {
        int max = 0;
        for (Coordinates coordinates: coordinatesSet) {
            max = Math.max(max, coordinates.getX());
        }
        return max;
    }
}
