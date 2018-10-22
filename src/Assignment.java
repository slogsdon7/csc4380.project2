import java.io.Serializable;
import java.util.List;

public class Assignment implements Serializable {
    private Double weight;
    private String name;
    private Integer id;

    public Integer getID(){
        return id;
    }
    public Assignment(){

    }
    public Assignment(String name, double weight) {
        this.weight = weight;
        this.name = name;
    }
    public boolean insert(){
        AssignmentDAO assignmentDAO = new AssignmentDAOImpl();
        var a = assignmentDAO.createAssignment(this);
        id = a.getID();
        return true;
    }
    public boolean delete(){
        AssignmentDAO assignmentDAO = new AssignmentDAOImpl();
        return assignmentDAO.deleteAssignment(this);
    }
    public boolean update(){
        AssignmentDAO assignmentDAO = new AssignmentDAOImpl();
        return assignmentDAO.deleteAssignment(this);
    }
    public static Assignment create(String name){
        var a = new Assignment(name,1.0);
        a.insert();
        return a;

    }
    public static List<Assignment> getAll(){
        AssignmentDAO assignmentDAO = new AssignmentDAOImpl();
        return assignmentDAO.findAll();
    }

    public void setID(Integer id) {
        this.id = id;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

