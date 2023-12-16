package bigproject_pro192_campusmanagement.DAO;

import bigproject_pro192_campusmanagement.DTO.Campus;
import bigproject_pro192_campusmanagement.DTO.Student;
import java.util.ArrayList;
import java.util.List;

public class CampusManagement {

    private List<Campus> campuses = new ArrayList<>();

    public int getSize() {
        return campuses.size();
    }

    public boolean addCampus(Campus c) {
        for (int i = 0; i < campuses.size(); i++) {
            if (findCampusById(c.getId()) != null) {
                return false;
            }
        }
        return campuses.add(c);
    }

    public Campus findCampusById(String id) {
        for (int i = 0; i < campuses.size(); i++) {
            if (campuses.get(i).getId().equals(id)) {
                return campuses.get(i);
            }
        }
        return null;
    }

    public boolean updateCampusById(Campus c) {
        for (int i = 0; i < campuses.size(); i++) {
            if (campuses.get(i).getId().equals(c.getId())) {
                campuses.set(i, c);
                return true;
            }

        }
        return false;
    }

    public boolean deleteCampusById(String id) {
        for (int i = 0; i < campuses.size(); i++) {
            if (campuses.get(i).getId().equals(id)) {
                campuses.remove(i);
                return true;
            }
            
        }
        return false;
    }

    public boolean addStudentToCampus(Student s, Campus c) {
        List<Student> students = c.getStudent();
        for (int i = 0; i < students.size(); i++) {
            Student currentStudentInCampus = students.get(i);
            if (s.getCode().equals(currentStudentInCampus.getCode())) {
                return false;
            }
        }
        return students.add(s);
    }

    public void deleteStudentFromCampus(String code) {
        for (int i = 0; i < campuses.size(); i++) {
            List<Student> studentsInCampus = campuses.get(i).getStudent();
            for (int j = 0; j < studentsInCampus.size(); j++) {
                if (studentsInCampus.get(i).getCode().equals(code)) {
                    studentsInCampus.remove(j);
                }
            }
        }
    }
}
