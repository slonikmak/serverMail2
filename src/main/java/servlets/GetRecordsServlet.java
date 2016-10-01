package servlets;

import sessionService.SessionService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Anton on 01.10.2016.
 */
public class GetRecordsServlet extends HttpServlet {
    SessionService sessionService;

    public GetRecordsServlet(SessionService sessionService){
        this.sessionService = sessionService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("records. Session id = "+request.getParameter("sessionid"));
    }
}
