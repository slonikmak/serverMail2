import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.geojson.*;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Created by Oceanos on 03.10.2016.
 */
public class GeoJsonTest {
    @Test
    public void geoJsonTest() throws FileNotFoundException {
        /*try {
            GeoJsonObject object = new ObjectMapper().readValue(new FileInputStream("././public_html/newData.json"), FeatureCollection.class);
            System.out.println(object);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        FeatureCollection featureCollection = new FeatureCollection();

        LineString lineString = new LineString(new LngLatAlt(31.188539167, 59.580409667), new LngLatAlt(31.1886713,59.580443723));
        Point point = new Point(new LngLatAlt(31.188539167, 59.580409667));
        Feature feature = new Feature();
        feature.setGeometry(lineString);
        feature.setProperty("name", "T - T");
        feature.setProperty("desc", "W");
        featureCollection.add(feature);
        Feature feature1 = new Feature();
        feature1.setGeometry(point);
        featureCollection.add(feature1);
        try {
            String json= new ObjectMapper().writeValueAsString(featureCollection);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
