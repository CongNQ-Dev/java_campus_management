package bigproject_pro192_campusmanagement.DAO;

import bigproject_pro192_campusmanagement.DTO.Course;
import bigproject_pro192_campusmanagement.DTO.Student;
import java.util.ArrayList;
import java.util.List;

public class CourseManagement {

    private List<Course> courses = new ArrayList<>();

    public int getSize() {
        return courses.size();
    }

    public boolean addCourse(Course c) {
        for (int i = 0; i < courses.size(); i++) {
            if (findCourseByCode(c.getCode()) != null) {
                return false;
            }
        }
        return courses.add(c);
    }

    public Course findCourseByCode(String code) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCode().equals(code)) {
                return courses.get(i);
            }
        }
        return null;
    }

    public boolean updateCourseByCode(Course c) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCode().equals(c.getCode())) {
                courses.set(i, c);
                return true;
            }
            
        }
        return false;
    }

    public boolean deleteCourseByCode(String code) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCode().equals(code)) {
                courses.remove(i);
                return true;
            }
            
        }
        return false;
    }

    public boolean addStudentToCourse(Student s, Course c) {
        List<Student> students = c.getStudents();
        for (int i = 0; i < students.size(); i++) {
            Student currentStudentInCourse = students.get(i);
            if (s.getCode().equals(currentStudentInCourse.getCode())) {
                return false;
            }
        }
        return students.add(s);
    }

    public void deleteStudentFromCourses(String code) {
        for (int i = 0; i < courses.size(); i++) {
            List<Student> studentsInCourse = courses.get(i).getStudents();
            for (int j = 0; j < studentsInCourse.size(); j++) {
                if (studentsInCourse.get(i).getCode().equals(code)) {
                    studentsInCourse.remove(j);
                }
            }
        }
    }
}
