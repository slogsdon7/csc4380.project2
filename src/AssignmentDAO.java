import java.util.List;

public interface AssignmentDAO {
    List<Assignment> findAll();
    Assignment getByID(int id);
    Assignment createAssignment(Assignment assignment);
    boolean updateAssignment(Assignment assignment);
    boolean deleteAssignment(Assignment assignment);
}
