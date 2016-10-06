package context;

/**
 * Created by Oceanos on 03.10.2016.
 */
public class Context {
    public static final String pathTo = "/public_html/log_base.db";

    public static final String dbName = "log_base.db";

    private static String dbPath = "src/main/resources/log_28_september.db";

    private static String publicHtmlPath;

    public static String getDBPath(){
        return dbPath;
    }

    public static void setDBPath(String path){
        dbPath = path;
    }

    public static void setPublicHtmlPath(String path){
        publicHtmlPath = path;
    }

    public static String getPublicHtmlPath(){
        return publicHtmlPath;
    }


}
