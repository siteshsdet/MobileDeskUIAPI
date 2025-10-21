package Steps.WebApp;

import org.testng.annotations.Test;

public class StringTest {

    @Test
    public void checkTest(){
        String name = "Raju";
        System.out.println(name.getClass().getSimpleName());
        System.out.println(name.concat("abcd"));
    }
}
