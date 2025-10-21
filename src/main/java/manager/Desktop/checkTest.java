package manager.Desktop;

import configurations.Util.PropertyConfig;
import org.testng.annotations.Test;

import java.io.IOException;

public class checkTest {
    private Process wordProcess;

    public boolean openWord(String filePath) {
        try {
            wordProcess = Runtime.getRuntime().exec(
                    "cmd /c start winword \"" + filePath + "\""
            );
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean closeWord() {
        try {
            Runtime.getRuntime().exec("taskkill /IM WINWORD.EXE /F");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Test
    public void openTest(){
        openWord(PropertyConfig.WORD_FILE_PATH.toString());
    }
}
