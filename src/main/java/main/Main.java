package main;

import context.Context;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;
import sessionService.SessionService;
import util.Utills;

import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Created by Anton on 23.09.2016.
 */
public class Main {


    public static void main(String[] args) throws Exception {


        if (args.length>0){
            System.out.println("Path to working base: "+args[0]);
            Context.setDBPath(args[0]);
        }


        System.out.println("Path to download base: "+Paths.get(Context.pathTo));

        Utills.copyDb(Context.getDBPath(), Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParent()+Context.pathTo);

        //AccountService service = new AccountService();

        Server server = new Server(8080);

        SessionService sessionService = new SessionService();

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //contextHandler.addServlet(new ServletHolder(new SignUpServlet(service)),"/signup");
        //contextHandler.addServlet(new ServletHolder(new SignInServlet(service)),"/signin");
        //contextHandler.addServlet(new ServletHolder(new IndexServlet()), "/index");
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase(Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParent()+"/public_html");
        contextHandler.addServlet(new ServletHolder(new WebSocketChatServlet()),"/chat");
        contextHandler.addServlet(new ServletHolder(new GetSessionServlet(sessionService)), "/sessions");
        contextHandler.addServlet(new ServletHolder(new GetRecordsServlet(sessionService)), "/records");
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
