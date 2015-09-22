/**
 * Created by brad on 9/21/15.
 */

import java.sql.*;

import org.h2.tools.DeleteDbFiles;

public class H2Sample {

    static Connection conn;
    static Statement statement;

    public static void createDB() throws ClassNotFoundException, SQLException {
        dropDB();

        createConnection();

        addTestDataToDB();
    }



    /*

            SQL Methods below. Not Ginger Related. Actually, none of this file is Ginger related.
            Just an example as to how to use SQL so you can avoid state in your Ginger Model.

     */

    public static String fetchById(String id) {
        ResultSet rs;
        String json = "{";
        try{
            rs = H2Sample.statement.executeQuery("select * from test where id = " + id);
            while (rs.next()) {
                json+="\"id\":\""+rs.getString("id")+"\"";
                json+="\"name\":\""+rs.getString("name")+"\"";
                json+=",\"completed\":"+rs.getBoolean("completed");
            }
            json+="}";
            return json;
        } catch (Exception e) {
            return "{\"error\": \"Fetch By ID Failed.\"}";
        }
    }

    public static String fetchAll() {
        ResultSet rs;
        String json = "[";
        try{
            rs = H2Sample.statement.executeQuery("select * from test");
            while (rs.next()) {
                json+="{";
                json+="\"id\":\""+rs.getString("id")+"\"";
                json+="\"name\":\""+rs.getString("name")+"\"";
                json+=",\"completed\":"+rs.getBoolean("completed");
                json+="},";
            }
            json = json.substring(0, json.length()-1);
            json+="]";
            return json;
        } catch (Exception e) {
            return "{\"error\": \"Fetch All Failed.\"}";
        }
    }

    public static String save(String name, boolean completed) {
        try {
            String query = "insert into test (name, completed) values('"+name+"', "+completed+")";
            System.out.println(query);
            H2Sample.statement.execute(query);
            return "{\"save\": true}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"Save Failed.\"}";
        }
    }

    public static String deleteById(String id) {
        try{
            H2Sample.statement.execute("delete from test where id = " + id);
            return "{\"delete\": true}";
        } catch (Exception e) {
            return "{\"error\": \"Fetch By ID Failed.\"}";
        }
    }







    private static void closeConnection() throws SQLException {
        statement.close();
        conn.close();
    }

    private static void addTestDataToDB() throws SQLException {
        statement.execute("create table test(id int auto_increment, name varchar(255), completed boolean)");
        statement.execute("insert into test values(1, 'Work Out', false)");
    }

    private static void createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:h2:~/test");
        statement = conn.createStatement();
    }

    private static void dropDB() {
        DeleteDbFiles.execute("~", "test", true);
    }


}
