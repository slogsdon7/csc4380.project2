import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class AssignmentDAOImpl implements AssignmentDAO{



    @Override
    public List<Assignment> findAll() {
        Connection conn = ConnectionFactory.getConnection();

        return null;
    }

    @Override
    public Assignment getByID(int id) {
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement stmnt = conn.prepareStatement("SELECT * FROM assignment WHERE id IS ?");
            stmnt.setInt(1, id);
            ResultSet rs = stmnt.executeQuery();
            rs.next();
            Assignment assignment = new Assignment();
            assignment.setName(rs.getString("name"));
            assignment.setWeight(rs.getDouble("weight"));
            assignment.setID(rs.getInt("id"));
            conn.close();
            return assignment;
        }
        catch (SQLException e) {
            return null;
        }
        finally{
            ConnectionFactory.close(conn);
        }
    }

    @Override
    public Assignment createAssignment(Assignment assignment) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            String insertString = "INSERT INTO assignment (name, weight) values (?,?)";
            PreparedStatement stmnt= conn.prepareStatement(insertString, PreparedStatement.RETURN_GENERATED_KEYS);
            stmnt.setString(1, assignment.getName());
            stmnt.setDouble(2, assignment.getWeight());
            stmnt.execute();
            ResultSet rs = stmnt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            assignment.setID(id);
            conn.close();
            return assignment;
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
            return assignment;
        }
        finally{
            ConnectionFactory.close(conn);
        }
    }

    @Override
    public boolean updateAssignment(Assignment assignment) {
        String SQL = "";
        Connection conn = ConnectionFactory.getConnection();
        try{
            var stmt =  conn.prepareStatement(SQL);
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
    public boolean deleteAssignment(Assignment assignment) {
        String SQL = "DELETE FROM assignment WHERE id=?";
        Connection conn = ConnectionFactory.getConnection();
        try{
            var stmt =  conn.prepareStatement(SQL);
            stmt.setInt(1, assignment.getID());
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
