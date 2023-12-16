/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigproject_pro192_campusmanagement.DTO;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private String code;
    private String name;
    private int credit;
    private List<Student> students;

    public Course() {
        this.students = new ArrayList<>();
    }

    public Course(String code, String name, int credit) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.students = new ArrayList<>();

    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
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

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

}
