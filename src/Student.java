import java.io.Serializable;
import java.util.List;

/**
 *
 */
public class Student implements Serializable {
    private String firstName;
    private String lastName;
    private Integer id;



    public Student() {

    }

    public Student(String firstName, String lastName, Integer id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }
    public Student(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;

    }
    public static List<Student> getAll(){
        StudentDAO studentDAO = new StudentDAOImpl();
        return studentDAO.findAll();
    }
    public Double calculateAverage(){
        GradeDAO gradeDAO = new GradeDAOImpl();
        return gradeDAO.getAverageByStudent(this);
    }

    public List<Grade> getGrades(){
        GradeDAO gradeDAO = new GradeDAOImpl();
        return gradeDAO.findByStudent(this);
    }
    public boolean insert(){
        StudentDAO studentDAO = new StudentDAOImpl();
        id = studentDAO.insertStudent(this).id;
        return true;
    }
    public boolean delete(){
        StudentDAO studentDAO = new StudentDAOImpl();
        return studentDAO.deleteStudent(this);
    }
    public boolean update(){
        StudentDAO studentDAO = new StudentDAOImpl();
        return studentDAO.updateStudent(this);
    }
    public Student create(String firstName, String lastName){
        StudentDAO studentDAO = new StudentDAOImpl();
        return studentDAO.insertStudent(new Student(firstName,lastName));
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setID(int id){
        this.id = id;
    }
}



