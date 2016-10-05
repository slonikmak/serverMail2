import main.Main;
import org.junit.Test;

import java.net.URISyntaxException;

/**
 * Created by anton on 05.10.16.
 */
public class PathTest {
    @Test
    public void testPath() throws URISyntaxException {
        System.out.printf(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
    }
}
