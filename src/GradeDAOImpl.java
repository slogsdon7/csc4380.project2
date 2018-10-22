import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;


public class GradeDAOImpl implements GradeDAO {


    @Override
    public List<Grade> findAll() {
        Connection conn = ConnectionFactory.getConnection();
        StudentDAO studentDAO = new StudentDAOImpl();
        AssignmentDAO assignmentDAO = new AssignmentDAOImpl();
        List<Grade> grades = new ArrayList<Grade>();
        try{
            String query = "SELECT *\n" +
                    "FROM grade\n" +
                    "       LEFT JOIN assignment a on grade.assignment_id = a.id\n" +
                    "       LEFT JOIN student s on grade.student_id = s.id\n" +
                    "ORDER BY s.id;";
            ResultSet rs = conn.createStatement().executeQuery(query);
            while (rs.next()){
                Grade grade = new Grade();
                var student = studentDAO.getByID(rs.getInt("student_id"));
                grade.setStudent(student);
                var assignment = assignmentDAO.getByID(rs.getInt("assignment_id"));
                grade.setAssignment(assignment);
                grade.setGrade(rs.getDouble("grade"));
                grades.add(grade);
            }
            return grades;
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }

    }
    @Override
    public Double getAverageByStudent(Student s){
        String query = "SELECT student_id, grade, avg(grade) as avg FROM grade WHERE student_id IS ? GROUP BY student_id";
        Connection conn = ConnectionFactory.getConnection();
        Double average;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, s.getId());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            average = rs.getDouble("avg");

        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            average = -1.0;
        }
        return average;
    }
    @Override
    public List<Grade> findByAssignment(Assignment a) {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "SELECT * from Grade WHERE assignment_id=?";
        return null;
    }

    @Override
    public List<Grade> findByStudent(Student s) {
        List<Grade> grades = new ArrayList<>();
        AssignmentDAO assignmentDAO = new AssignmentDAOImpl();
        String sql = "SELECT * from Grade WHERE student_id=?";
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, s.getId() );
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                var assignment = assignmentDAO.getByID(rs.getInt("assignment_id"));
                var grade = new Grade(assignment, s, rs.getDouble("grade"));
                grades.add(grade);
            }
        }
        catch(SQLException e){
            grades = null;

        }
        finally {
            ConnectionFactory.close(conn);
        }
        return grades;
    }

    @Override
    public Grade insertGrade(Grade grade) {
        Connection conn = ConnectionFactory.getConnection();
        try{
            String insertString = "INSERT INTO grade (student_id, assignment_id, grade) values (?,?,?)";
            PreparedStatement stmnt = conn.prepareStatement(insertString, PreparedStatement.RETURN_GENERATED_KEYS);
            stmnt.setInt(1, grade.getStudent().getId());
            stmnt.setInt(2, grade.getAssignment().getID());
            stmnt.setDouble(3, grade.getGrade());
            stmnt.execute();
            var rs = stmnt.getGeneratedKeys();
            rs.next();
            conn.close();
            return grade;
        }
        catch (SQLException e){
            return null;
        }

    }

    @Override
    public boolean updateGrade(Grade grade) {
        String SQL = "UPDATE grade SET grade =? WHERE student_id = ? AND assignment_id = ?";
        Connection conn = ConnectionFactory.getConnection();
        try{
            var stmt =  conn.prepareStatement(SQL);
            stmt.setDouble(1, grade.getGrade());
            stmt.setInt(2, grade.getStudent().getId());
            stmt.setInt(3, grade.getAssignment().getID());
            return stmt.execute();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        finally{
            ConnectionFactory.close(conn);
        }
        return false;
    }

    @Override
    public boolean deleteGrade(Grade grade) {
        String SQL = "DELETE FROM grade WHERE student_id = ? AND assignment_id = ?";
        Connection conn = ConnectionFactory.getConnection();
        try{
          var stmt =  conn.prepareStatement(SQL);
          stmt.setInt(1,grade.getStudent().getId());
          stmt.setInt(2, grade.getAssignment().getID());
          return stmt.execute();

        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        finally{
            ConnectionFactory.close(conn);
        }
        return false;
    }
}
