package Util;

import java.util.List;

public class GetPolygonArea {

    public static int execute(List<Coordinates> coordinatesList) {
        // use shoelace algorithm to find area of polygon
        // list must be ordered
        // sum of determinants will be twice the area
        int numVertices = coordinatesList.size();

        int area = 0;

        int x1, x2, y1, y2;

        for (int i = 1; i <= numVertices; i++) {
            x1 = coordinatesList.get(i-1).getX();
            y1 = coordinatesList.get(i-1).getY();

            if (i == numVertices) {
                x2 = coordinatesList.get(0).getX();
                y2 = coordinatesList.get(0).getY();
            } else {
                x2 = coordinatesList.get(i).getX();
                y2 = coordinatesList.get(i).getY();
            }
            area += getDeterminant(x1, x2, y1, y2);
        }
        return Math.abs(area/2);
    }

    private static int getDeterminant(int a, int b, int c, int d) {
        return a*d - b*c;
    }
}
