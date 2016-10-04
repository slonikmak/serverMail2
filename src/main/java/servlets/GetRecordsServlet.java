package servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import model.Record;
import org.geojson.*;
import sessionService.SessionService;
import util.Utills;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 01.10.2016.
 */
public class GetRecordsServlet extends HttpServlet {
    SessionService sessionService;

    public GetRecordsServlet(SessionService sessionService){
        this.sessionService = sessionService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String type = request.getParameter("type");
        long sessionId = Long.parseLong(request.getParameter("sessionid"));



        try {
            response.getOutputStream().write(prepareGeoJsonString(sessionService.getRecords(type, sessionId)).getBytes("UTF-8"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus( HttpServletResponse.SC_OK );


        //response.getWriter().write("records. Session id = "+request.getParameter("sessionid"));
    }

    private String prepareGeoJsonString(List<Record> records) throws JsonProcessingException {
        String result = null;
        FeatureCollection featureCollection = new FeatureCollection();

        LineString lineString = new LineString();



        for (Record r : records) {
            String coords = r.getValue();
            //if (coords.equals("__,__")) continue;
            String[] coordsArray = coords.split("__,__");
            LngLatAlt lngLatAlt = new LngLatAlt(Utills.coordsConverter(Double.parseDouble(coordsArray[1])), Utills.coordsConverter(Double.parseDouble(coordsArray[0])));
            lineString.add(lngLatAlt);

        }


        Feature feature = new Feature();
        feature.setGeometry(lineString);
        feature.setProperty("name", "T - T");
        feature.setProperty("desc", "W");
        featureCollection.add(feature);



        try {
            Record startRecord = records.get(0);
            String[] startRecArray = startRecord.getValue().split("__,__");
            Record endRecprd = records.get(records.size()-1);
            String[] endRecArray = endRecprd.getValue().split("__,__");

            Feature startPoint = new Feature();
            Point point = new Point(new LngLatAlt(Utills.coordsConverter(Double.parseDouble(startRecArray[1])), Utills.coordsConverter(Double.parseDouble(startRecArray[0]))));
            startPoint.setGeometry(point);
            featureCollection.add(startPoint);

            Feature endPoint = new Feature();
            point = new Point(new LngLatAlt(Utills.coordsConverter(Double.parseDouble(endRecArray[1])), Utills.coordsConverter(Double.parseDouble(endRecArray[0]))));
            endPoint.setGeometry(point);
            featureCollection.add(endPoint);
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }

        result = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(featureCollection);



        return result;
    }
}
