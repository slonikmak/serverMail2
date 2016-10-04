package context;

/**
 * Created by Oceanos on 03.10.2016.
 */
public class Contex {
    private static String dbPath = "C:/Users/Oceanos/IdeaProjects/serverMail2/src/main/resources\\log_28_september.db";

    public static String getDBPath(){
        return dbPath;
    }

    public static void setDBPath(String path){
        dbPath = path;
    }


}
