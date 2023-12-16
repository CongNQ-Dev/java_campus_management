package bigproject_pro192_campusmanagement.DTO;

import java.util.ArrayList;
import java.util.List;

public class Campus {

    private String id;
    private String name;
    private String address;
    private List<Student> students;

    public Campus() {
        this.students = new ArrayList<>();
    }

    public Campus(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.students = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Student> getStudent() {
        return students;
    }

    public void setStudent(List<Student> students) {
        this.students = students;
    }

}
