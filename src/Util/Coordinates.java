package Util;

public class Coordinates {

    private int x;
    private int y;

    public boolean isAdjacent(Coordinates other) {
        if (Math.abs(x - other.getX()) > 1) {
            return false;
        }
        return (Math.abs(y - other.getY()) <= 1);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
