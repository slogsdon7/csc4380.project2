import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionFactory {
    String db = "jdbc:sqlite:db.sqlite";
    public ConnectionFactory(){

    }
    public static void createTables(){
        String SQL="create table if not exists assignment\n" +
                "(\n" +
                "  name   text    not null,\n" +
                "  weight REAL    not null,\n" +
                "  id     INTEGER not null\n" +
                "    primary key\n" +
                "  autoincrement\n" +
                ");\n" +
                "\n" +
                "create table if not exists student\n" +
                "(\n" +
                "  first_name text    not null,\n" +
                "  last_name  int     not null,\n" +
                "  id         INTEGER not null\n" +
                "    primary key\n" +
                "  autoincrement\n" +
                ");\n" +
                "\n" +
                "create table if not exists grade\n" +
                "(\n" +
                "  student_id    int\n" +
                "    constraint grade_student_id_fk\n" +
                "    references student,\n" +
                "  grade         real not null,\n" +
                "  assignment_id int  not null\n" +
                "    constraint grade_assignment_id_fk\n" +
                "    references assignment\n" +
                ");\n";
        Connection conn = getConnection();
        try {
            conn.prepareStatement(SQL).execute();
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        finally {
            close(conn);
        }
    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:db.sqlite");
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return connection;
    }
    public static void close(Connection conn){
        try{
            conn.close();
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }

}
