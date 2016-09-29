package servlets;

import accountService.AccountService;
import db.DBException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Anton on 23.09.2016.
 */
public class SignInServlet extends HttpServlet{
    private AccountService accountService;

    public SignInServlet(AccountService service){
        this.accountService = service;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");

        try {
            if (accountService.isRegistred(login)){
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println("Authorized: "+login);
            } else {
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().println("Unauthorized");
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
