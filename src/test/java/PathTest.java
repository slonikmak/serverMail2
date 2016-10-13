import main.Main;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by anton on 05.10.16.
 */
public class PathTest {
    @Test
    public void testPath() throws URISyntaxException {
        System.out.printf(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        try {
            Files.createFile(Paths.get("C:\\Users\\Oceanos\\Desktop\\p.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
