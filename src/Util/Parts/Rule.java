package Util.Parts;

public class Rule {

    private final char valueToCheck;
    private final char operation;
    private final int testValue;
    private final String outcome;

    public Rule(char valueToCheck, char operation, int testValue, String outcome) {
        this.valueToCheck = valueToCheck;
        this.operation = operation;
        this.testValue = testValue;
        this.outcome = outcome;
    }

    public String testPart(Part part) {
        int value = part.getValueForChar(valueToCheck);
        if (value == -1) {
            System.out.println("Invalid test value " + valueToCheck);
        }
        return switch (operation) {
            case '>' -> (value > testValue) ? outcome : null;
            case '<' -> (value < testValue) ? outcome : null;
            default -> null;
        };
    }

}
