import java.util.List;

public interface StudentDAO{
    List<Student> findAll();
    Student getByID(int id);
    Student insertStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(Student student);

}
