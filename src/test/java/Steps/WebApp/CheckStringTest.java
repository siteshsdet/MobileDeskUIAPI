package Steps.WebApp;

import org.testng.annotations.Test;

public class CheckStringTest {

    @Test
    public void printReversedFirstCharsInLowercase() {
        String str = "Raju Kumar Vishwakarma";
        for (int i = str.length() - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch + 32);
            }
            System.out.print(ch);
        }
    }
}
