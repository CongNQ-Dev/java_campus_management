package bigproject_pro192_campusmanagement.DTO;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String code;
    private String name;
    private String gender;
    private String address;
    private Campus campus;
    private List<Course> courses;

    public Student() {
        this.courses = new ArrayList<>();
    }

    public Student(String code, String name, String gender, String address) {
        this.code = code;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.courses = new ArrayList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }
    
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
