package servlets;

import accountService.AccountService;
import db.DBException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Anton on 23.09.2016.
 */
public class SignUpServlet extends HttpServlet{
    AccountService accountService;

    public SignUpServlet(AccountService service){
        this.accountService = service;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        try {
            accountService.add(login, pass);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
