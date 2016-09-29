package main;

import accountService.AccountService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.IndexServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import servlets.WebSocketChatServlet;


/**
 * Created by Anton on 23.09.2016.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        //AccountService service = new AccountService();

        Server server = new Server(8080);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //contextHandler.addServlet(new ServletHolder(new SignUpServlet(service)),"/signup");
        //contextHandler.addServlet(new ServletHolder(new SignInServlet(service)),"/signin");
        //contextHandler.addServlet(new ServletHolder(new IndexServlet()), "/index");
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("public_html");
        contextHandler.addServlet(new ServletHolder(new WebSocketChatServlet()),"/chat");
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, contextHandler});
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started");
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
        //service.closeConn();

    }



}
