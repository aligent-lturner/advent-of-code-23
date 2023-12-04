package Util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TranslateDigitsTest {

    @Test
    void execute() {
        String[] testStrings = {"gkoneight4fivekvktltnine", "k2jhsdgijyewg9jh"};
        String[] expected = { "18459", "29" };

        for (int i = 0; i < testStrings.length; i++) {
            String translated = TranslateDigits.execute(testStrings[i]);
            Assertions.assertEquals(translated, expected[i]);
        }
    }
}