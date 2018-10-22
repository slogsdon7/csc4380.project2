import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        try{
            ResultSet rs = conn.createStatement().executeQuery("Select * FROM student");
            while (rs.next()){
                Student student = new Student();
                student.setFirstName(rs.getString(1));
                student.setLastName(rs.getString(2));
                students.add(student);
            }
            conn.close();
            return students;
        }
        catch (SQLException e){
            return null;

        }
    }

    @Override
    public Student getByID(int id) {
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement stmnt = conn.prepareStatement("SELECT * FROM student WHERE id IS ?");
            stmnt.setInt(1, id);
            ResultSet rs = stmnt.executeQuery();
            Student student = new Student();
            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));
            student.setID(rs.getInt("id"));
            conn.close();
            return student;
        }
        catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Student insertStudent(Student student) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String insertString = "INSERT INTO student (first_name, last_name) values (?, ?)";
            var stmnt = conn.prepareStatement(insertString, PreparedStatement.RETURN_GENERATED_KEYS);
            stmnt.setString(1, student.getFirstName());
            stmnt.setString(2, student.getLastName());
            stmnt.execute();
            var rs = stmnt.getGeneratedKeys();
            rs.next();
            student.setID(rs.getInt(1));
            conn.close();
            return student;
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }

    }

    @Override
    public boolean updateStudent(Student student) {
        String SQL = "";
        Connection conn = ConnectionFactory.getConnection();
        try{
            var stmt =  conn.prepareStatement(SQL);
            return stmt.execute();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteStudent(Student student) {
        String SQL = "DELETE FROM student WHERE id=?";
        Connection conn = ConnectionFactory.getConnection();
        try{
            var stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, student.getId());
            return stmt.execute();
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        finally{
            ConnectionFactory.close(conn);
        }

        return false;
    }
}
