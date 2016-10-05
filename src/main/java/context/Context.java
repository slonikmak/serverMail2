package context;

/**
 * Created by Oceanos on 03.10.2016.
 */
public class Context {
    public static final String pathTo = "/public_html/log_base.db";

    private static String dbPath = "src/main/resources/log_28_september.db";

    public static String getDBPath(){
        return dbPath;
    }

    public static void setDBPath(String path){
        dbPath = path;
    }


}
