package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import model.Session;

import sessionService.SessionService;



import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Oceanos on 30.09.2016.
 */
public class GetSessionServlet extends HttpServlet{
    SessionService sessionService;

    public GetSessionServlet(SessionService sessionService){
        this.sessionService = sessionService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            ArrayNode arrayNode = objectMapper.createArrayNode();
            List<Session> sessions  = sessionService.getSessionsList();
            for (Session s :
                    sessions) {
                s.setRecordsCount(sessionService.getRecordsCount(s.getId()));
                arrayNode.addPOJO(s);
            }
            //System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode));
            response.getOutputStream().write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode).getBytes("UTF-8"));
            response.setContentType("application/json; charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setStatus( HttpServletResponse.SC_OK );
            //response.setContentType("application/json");
            //response.setStatus(HttpServletResponse.SC_OK);
            //response.getWriter().write(String.valueOf(sessionService.getSessionsList()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
