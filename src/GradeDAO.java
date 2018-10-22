import java.util.List;

public interface GradeDAO{
    List<Grade> findAll();
    List<Grade> findByAssignment(Assignment a);
    List<Grade> findByStudent(Student s);
    Double getAverageByStudent(Student s);
    Grade insertGrade(Grade grade);
    boolean updateGrade(Grade grade);
    boolean deleteGrade(Grade grade);
}
