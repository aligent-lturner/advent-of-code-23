package Util.Parts;

public class Part {

    private int x;
    private int m;
    private int a;
    private int s;


    public int getValueForChar(char ch) {
        return switch (ch) {
            case 'x' -> x;
            case 'm' -> m;
            case 'a' -> a;
            case 's' -> s;
            default -> -1;
        };
    }

    public void setValueForChar(char ch, int value) {
        switch (ch) {
            case 'x' -> x = value;
            case 'm' -> m = value;
            case 'a' -> a = value;
            case 's' -> s = value;
        }
    }

    public int getRating() {
        return x + m + a + s;
    }
}
