package bigproject_pro192_campusmanagement.DAO;

import bigproject_pro192_campusmanagement.DTO.Campus;
import bigproject_pro192_campusmanagement.DTO.Course;
import bigproject_pro192_campusmanagement.DTO.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentManagement {
    
    private List<Student> students = new ArrayList<>();
    
    public int getSize() {
        return students.size();
    }
    
    public boolean addStudent(Student s) {
        for (int i = 0; i < students.size(); i++) {
            if (findStudentByCode(s.getCode()) != null) {
                return false;
            }
        }
        return students.add(s);
    }
    
    public Student findStudentByCode(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getCode().equals(id)) {
                return students.get(i);
            }
        }
        return null;
    }
    
    public boolean deleteStudentByCode(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getCode().equals(id)) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public boolean updateStudentByCode(Student s) {   
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getCode().equals(s.getCode())) {
                students.set(i, s);
                 return true;
            }
           
        }
        return false;
    }
    
    public boolean addCourseToStudent(Course c, Student s) {
        List<Course> courses = s.getCourses();
        for (int i = 0; i < courses.size(); i++) {
            Course currentCourseInStudents = courses.get(i);
            if (c.getCode().equals(currentCourseInStudents.getCode())) {
                return false;
            }
        }
        return courses.add(c);
    }
    
    public void deleteCourseFromStudents(String code) {
        for (int i = 0; i < students.size(); i++) {
            List<Course> coursesInStudent = students.get(i).getCourses();
            for (int j = 0; j < coursesInStudent.size(); j++) {
                if (coursesInStudent.get(i).getCode().equals(code)) {
                    coursesInStudent.remove(j);
                }
            }
        }
    }
    
    public void deleteCampusFromStudents(String id) {
        for (int i = 0; i < students.size(); i++) {
            Campus campusInStudent = students.get(i).getCampus();
            if (campusInStudent.getId().equals(id)) {
                students.get(i).setCampus(null);
            }
        }
    }

    public void addCampusToStudent(Campus c, Student s) {
        s.setCampus(c);
    }
    
}
