import java.io.Serializable;
import java.util.List;

public class Grade implements Serializable {
    private Assignment assignment;
    private Student student;
    private Double grade;
    public Grade(){

    }
    public Grade(Assignment assignment, Student student, Double grade) {
        this.assignment = assignment;
        this.student = student;
        this.grade = grade;
    }

     public boolean insert(){
        GradeDAO gradeDAO = new GradeDAOImpl();
        var g = gradeDAO.insertGrade(this);
        return g != null;
    }
    public boolean delete(){
        GradeDAO gradeDAO = new GradeDAOImpl();
        return gradeDAO.deleteGrade(this);
    }
    public boolean update(){
        GradeDAO gradeDAO = new GradeDAOImpl();
        return gradeDAO.updateGrade(this);
    }
    public static List<Grade> getAll(){
        GradeDAO gradeDAO = new GradeDAOImpl();
        return gradeDAO.findAll();
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}

