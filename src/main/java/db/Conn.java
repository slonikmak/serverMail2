package db;

import java.sql.*;

/**
 * Created by Anton on 23.09.2016.
 */
public class Conn {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void setConn() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        conn = null;
        //Class.forName("org.sqlite.JDBC");
        //conn = DriverManager.getConnection("jdbc:sqlite:baise.s3db");
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:h2:./test","test", "test");
        System.out.println(conn.getAutoCommit());

        System.out.println("База Подключена!");
    }
    public static void CreateDB() throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();
        //statmt.execute("DROP TABLE USERS");
        statmt.execute("CREATE TABLE IF NOT EXISTS USERS (id INTEGER AUTO_INCREMENT PRIMARY KEY, login varchar(20), password varchar(20));");

        System.out.println("Таблица создана или уже существует.");
    }

    // --------Заполнение таблицы--------
    public static void WriteDB() throws SQLException
    {
        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Petya', 125453); ");
        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Vasya', 321789); ");
        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Masha', 456123); ");

        System.out.println("Таблица заполнена");
    }

    // -------- Вывод таблицы--------
    public static void ReadDB() throws ClassNotFoundException, SQLException
    {
        resSet = statmt.executeQuery("SELECT * FROM users");

        while(resSet.next())
        {
            int id = resSet.getInt("id");
            String  name = resSet.getString("name");
            String  phone = resSet.getString("phone");
            System.out.println( "ID = " + id );
            System.out.println( "name = " + name );
            System.out.println( "phone = " + phone );
            System.out.println();
        }

        System.out.println("Таблица выведена");
    }

    // --------Закрытие--------
    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        //resSet.close();

        System.out.println("Соединения закрыты");
    }
}
