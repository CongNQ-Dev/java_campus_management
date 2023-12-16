/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigproject_pro192_campusmanagement;

import bigproject_pro192_campusmanagement.DAO.CampusManagement;
import bigproject_pro192_campusmanagement.DAO.CourseManagement;
import bigproject_pro192_campusmanagement.DAO.StudentManagement;
import bigproject_pro192_campusmanagement.DTO.Campus;
import bigproject_pro192_campusmanagement.DTO.Course;
import bigproject_pro192_campusmanagement.DTO.Student;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author cong.nguyen
 */
public class Tester {

    public static StudentManagement studentManagement = new StudentManagement();
    public static CampusManagement campusManagement = new CampusManagement();
    public static CourseManagement courseManagement = new CourseManagement();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int choice = 0;
        while (true) {
            System.out.println("====== SCHOOL MANAGEMENT =====");
            System.out.println("1. Student Management");
            System.out.println("2. Campus Management");
            System.out.println("3. Course Management");
            System.out.println("4. Exit program");
            System.out.println("==============================");

            try {
                System.out.println("Please choose: ");
                choice = new Scanner(System.in).nextInt();
                if (choice < 1 || choice > 4) {
                    throw new InputMismatchException();
                }
                switch (choice) {
                    case 1:
                        studentMgt();
                        break;
                    case 2:
                        campusMgt();
                        break;
                    case 3:
                        courseMgt();
                        break;
                    case 4:
                        System.out.println("Thank you!");
                        System.exit(0);
                }
            } catch (Exception e) {
                System.err.println("Only accept from 1 to 4");
            }

        }
    }

    public static void studentMgt() {
        int choice = 0;
        while (true) {
            System.out.println("====== STUDENT MANAGEMENT =====");
            System.out.println("1. Add a student");
            System.out.println("2. Update student by code");
            System.out.println("3. Delete student by code");
            System.out.println("4. Find student by code");
            System.out.println("5. Add course to student");
            System.out.println("6. Back to menu");
            System.out.println("==============================");

            try {
                System.out.println("Please choose: ");
                choice = new Scanner(System.in).nextInt();
                if (choice < 1 || choice > 6) {
                    throw new InputMismatchException();
                }
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        updateStudent();
                        break;
                    case 3:
                        deleteStudent();
                        break;
                    case 4:
                        findStudent();
                        break;
                    case 5:
                        addCourseToStudent();
                        break;
                    case 6:
                        return;

                }
            } catch (Exception e) {
                System.err.println("Only accept from 1 to 6");

            }

        }
    }

    public static void addCourseToStudent() {
        if (courseManagement.getSize() < 1) {
            System.err.println("There is must at least 1 course to add.");
            return;
        }
        if (studentManagement.getSize() < 1) {
            System.err.println("There is must at least 1 student to add.");
            return;
        }

        while (true) {
            System.out.println("Please input course id to add: ");
            String courseCode = new Scanner(System.in).nextLine();
            Course c = courseManagement.findCourseByCode(courseCode);
            if (c == null) {
                System.out.println("The course does not exist with id " + courseCode);
                continue;
            }
            Student s = null;
            while (true) {
                System.out.println("Please input student id to add: ");
                String studentCode = new Scanner(System.in).nextLine();
                s = studentManagement.findStudentByCode(studentCode);
                if (s == null) {
                    System.out.println("The student does not exist with id " + studentCode);
                    continue;
                }
                break;
            }

            while (true) {
                System.out.println("Do you really to add course " + courseCode
                        + " to student " + s.getCode() + "(y/n)? ");
                String choice = new Scanner(System.in).nextLine();
                if (choice.equalsIgnoreCase("n")) {
                    System.out.println("You chose not to add course to student.");
                    return;
                }
                if (studentManagement.addCourseToStudent(c, s)) {
                    System.out.println("Successfully to add a course to student.");

                } else {
                    System.out.println("Failed to add because this course is already existed.");
                }
                if (courseManagement.addStudentToCourse(s, c)) {
                    System.out.println("Successfully to add a student to course.");

                } else {
                    System.out.println("Failed to add because this student is already existed.");
                }
                return;
            }
        }
    }

    public static void addStudent() {
        while (true) {
            Student student = new Student();

            while (true) {
                System.out.println("Please input student code (Sxxx): ");
                String code = new Scanner(System.in).nextLine();
                if (code.isBlank()) {
                    System.err.println("Your input is Empty");
                } else if (!code.matches("^S[\\d]{3}$")) {
                    System.err.println("Please input code in format (Sxxx)!");
                } else {
                    student.setCode(code);
                    break;
                }
            }

            while (true) {
                System.out.println("Please input student name: ");
                String name = new Scanner(System.in).nextLine();
                if (name.isBlank()) {
                    System.err.println("Your input is Empty!");
                } else if (!name.matches("^[a-zA-Z\\s]{1,100}$")) {
                    System.err.println("Please input student name from 1 to 100 characters"
                            + " and must in alphabet!");
                } else {
                    student.setName(name);
                    break;
                }
            }
            while (true) {
                System.out.println("Please input student gender (M/F): ");
                String gender = new Scanner(System.in).nextLine();
                if (!(gender.equals("M") || gender.equals("F"))) {
                    System.err.println("Student gender must be male(M) or female(F).");
                } else {
                    student.setGender(gender);
                    break;
                }
            }

            while (true) {
                System.out.println("Please input student address: ");
                String address = new Scanner(System.in).nextLine();
                if (address.isBlank()) {
                    System.err.println("Your input is Empty!");
                } else if (!address.matches("^[a-zA-Z\\s]{3,100}$")) {
                    System.err.println("Please input student address from 3 to 100 characters"
                            + " and must in alphabet!");
                } else {
                    student.setAddress(address);
                    break;
                }
            }
            if (studentManagement.addStudent(student)) {
                System.out.println("Successfully added an student.");
            } else {
                System.out.println("The student with id " + student.getCode() + " is existed.");
            }

            System.out.println("Do you want to add more? (y/n)?");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                return;
            }
        }
    }

    public static void deleteStudent() {
        System.out.println("Please input student code: ");
        String code = new Scanner(System.in).nextLine();
        Student student = studentManagement.findStudentByCode(code);
        if (student == null) {
            System.out.println("The student with the provided code doesn't exist.");
            return;
        }
        while (true) {
            System.out.println("Do you want to delete this student? (y/n)?");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                return;
            }
            if (studentManagement.deleteStudentByCode(code)) {
                campusManagement.deleteStudentFromCampus(code);
                courseManagement.deleteStudentFromCourses(code);
                System.out.println("Successfully deleted student with id " + code);
                return;
            }
        }
    }

    public static void updateStudent() {
        System.out.println("Please input student code: ");
        String updateId = new Scanner(System.in).nextLine();
        Student updateStudent = studentManagement.findStudentByCode(updateId);
        if (updateStudent == null) {
            System.err.println("The student with the provided code doesn't exist.");
            return;
        }

        while (true) {
            System.out.println("Do you want to update name? (y/n): ");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                System.out.println("You chose not to update name.");
                break;
            }

            System.out.println("Please input student name: ");
            String name = new Scanner(System.in).nextLine();
            if (name.isBlank()) {
                System.err.println("Your input is Empty!");
            } else if (!name.matches("^[a-zA-Z\\s]{1,100}$")) {
                System.err.println("Please input student name from 1 to 100 characters"
                        + "and must in alphabet.");
            } else {
                updateStudent.setName(name);
                break;
            }
        }

        while (true) {
            System.out.println("Do you want to update student gender (y/n) ?");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                System.out.println("You chose not to update student gender.");
                break;
            } else {

                System.out.println("Please input student gender (M/F): ");
                String gender = new Scanner(System.in).nextLine();
                if (!(gender.equals("M") || gender.equals("F"))) {
                    System.err.println("Student gender must be male(M) or female(F).");
                } else {
                    updateStudent.setGender(gender);
                    break;

                }
            }
        }

        while (true) {
            System.out.println("Do you want to update address? (y/n): ");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                System.out.println("You chose not to update address.");
                break;
            }

            System.out.println("Please input student address: ");
            String address = new Scanner(System.in).nextLine();
            if (address.isBlank()) {
                System.err.println("Your input is Empty!");
            } else if (!address.matches("^[a-zA-Z\\s]{3,100}$")) {
                System.err.println("Please input student address from 3 to 100 characters"
                        + "and must in alphabet.");
            } else {
                updateStudent.setAddress(address);
                break;
            }
        }

        if (studentManagement.updateStudentByCode(updateStudent)) {
            System.out.println("Successfully updated the student with id"
                    + updateStudent.getCode());
        } else {
            System.err.println("The student with id " + updateStudent.getCode()
                    + " is not existed.");
        }
    }

    public static void findStudent() {
        System.out.println("Please input student Code: ");
        String code = new Scanner(System.in).nextLine();
        Student student = studentManagement.findStudentByCode(code);
        if (student == null) {
            System.err.println("The Student with the provided code doesn't exist.");
            return;
        }
        System.out.println("==========");
        System.out.println("The Student you want to find: ");
        System.out.println("Id: " + student.getCode());
        System.out.println("Name: " + student.getName());
        System.out.println("Gender: " + student.getGender());
        System.out.println("Address: " + student.getAddress());
        if (student.getCourses().size() > 0) {
            System.out.println("Student List: ");
            for (int i = 0; i < student.getCourses().size(); i++) {
                System.out.println("----------");
                Course studentCourse = student.getCourses().get(i);
                System.out.println("Course code: " + studentCourse.getCode());
                System.out.println("Course Name: " + studentCourse.getName());
                System.out.println("Course Credit: " + studentCourse.getCredit());
                System.out.println("----------");
            }
        }
        if (student.getCampus() != null) {
            System.out.println("Campus: ");
                System.out.println("----------");
                Campus studentCampus = student.getCampus();
                System.out.println("Campus code: " + studentCampus.getId());
                System.out.println("Campus Name: " + studentCampus.getName());
                System.out.println("Campus Address: " + studentCampus.getAddress());
                System.out.println("----------");
        }
        System.out.println("==========");
    }

    public static void courseMgt() {
        int choice = 0;
        while (true) {
            System.out.println("====== COURSES MANAGEMENT =====");
            System.out.println("1. Add a course");
            System.out.println("2. Update course by code");
            System.out.println("3. Delete course by code");
            System.out.println("4. Find course by code");
            System.out.println("5. Add student to course");
            System.out.println("6. Back to menu");
            System.out.println("==============================");
            try {
                System.out.println("Please choose: ");
                choice = new Scanner(System.in).nextInt();
                if (choice < 1 || choice > 6) {
                    throw new InputMismatchException();
                }
                switch (choice) {
                    case 1:
                        addCourse();
                        break;
                    case 2:
                        updateCourse();
                        break;
                    case 3:
                        deleteCourse();
                        break;
                    case 4:
                        findCourse();
                        break;
                    case 5:
                        addStudentToCourse();
                        break;
                    case 6:
                        return;

                }
            } catch (Exception e) {
                System.err.println("Only accept from 1 to 6");

            }
        }
    }

    public static void addStudentToCourse() {
        if (studentManagement.getSize() < 1) {
            System.err.println("There is must at least 1 student to add.");
            return;
        }
        if (courseManagement.getSize() < 1) {
            System.err.println("There is must at least 1 course to add.");
            return;
        }

        while (true) {
            System.out.println("Please input student id to add: ");
            String studentId = new Scanner(System.in).nextLine();
            Student s = studentManagement.findStudentByCode(studentId);
            if (s == null) {
                System.out.println("The student does not exist with id " + studentId);
                continue;
            }
            Course c = null;
            while (true) {
                System.out.println("Please input course id to add: ");
                String courseCode = new Scanner(System.in).nextLine();
                c = courseManagement.findCourseByCode(courseCode);
                if (c == null) {
                    System.out.println("The course does not exist with id " + courseCode);
                    continue;
                }
                break;
            }

            while (true) {
                System.out.println("Do you really to add student " + studentId
                        + " to course " + c.getCode() + "(y/n)? ");
                String choice = new Scanner(System.in).nextLine();
                if (choice.equalsIgnoreCase("n")) {
                    System.out.println("You chose not to add student to course.");
                    return;
                }
                if (courseManagement.addStudentToCourse(s, c)) {
                    System.out.println("Successfully to add a student to course.");
                } else {
                    System.out.println("Failed to add because this student is already existed.");
                }
                if (studentManagement.addCourseToStudent(c, s)) {
                    System.out.println("Successfully to add a course to student.");
                } else {
                    System.out.println("Failed to add because this course is already existed.");

                }
                return;
            }
        }
    }

    public static void addCourse() {
        while (true) {
            Course course = new Course();

            while (true) {
                System.out.println("Please input course code (Cxxx): ");
                String code = new Scanner(System.in).nextLine();
                if (code.isBlank()) {
                    System.err.println("Your input is Empty");
                } else if (!code.matches("^C[\\d]{2,}$")) {
                    System.err.println("Please input code in format (Cxxx)!");
                } else {
                    course.setCode(code);
                    break;
                }
            }

            while (true) {
                System.out.println("Please input course name: ");
                String name = new Scanner(System.in).nextLine();
                if (name.isBlank()) {
                    System.err.println("Your input is Empty!");
                } else if (!name.matches("^[a-zA-Z\\s]{1,100}$")) {
                    System.err.println("Please input student name from 1 to 100 characters"
                            + " and must in alphabet!");
                } else {
                    course.setName(name);
                    break;
                }
            }
            while (true) {
                System.out.println("Please input course credit: ");
                try {
                    int credit = new Scanner(System.in).nextInt();
                    if (credit < 1 || credit > Integer.MAX_VALUE) {
                        throw new InputMismatchException();
                    } else {
                        course.setCredit(credit);
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Price must be positive integer.");

                } catch (Exception e) {
                    System.err.println("Invalid Input!");
                }
            }

            if (courseManagement.addCourse(course)) {
                System.out.println("Successfully added a service with code " + course.getCode());
            } else {
                System.err.println("Failed to add a course");
            }
            System.out.println("Do you want to add more? (y/n)?");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                return;
            }
        }
    }

    public static void deleteCourse() {
        System.out.println("Please input course code: ");
        String code = new Scanner(System.in).nextLine();
        Course course = courseManagement.findCourseByCode(code);
        if (course == null) {
            System.err.println("The course with the provided code doesn't exist.");
            return;
        }
        while (true) {
            System.out.println("Do you want to delete this course? (y/n)?");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                return;
            }
            if (courseManagement.deleteCourseByCode(code)) {
                studentManagement.deleteCourseFromStudents(code);
                System.out.println("Successfully deleted course with code " + code);
                return;
            }
        }
    }

    public static void updateCourse() {
        System.out.println("Please input course code: ");
        String updateCode = new Scanner(System.in).nextLine();
        Course updateCourse = courseManagement.findCourseByCode(updateCode);
        if (updateCourse == null) {
            System.err.println("The course with the provided Code doesn't exist.");
            return;
        }

        while (true) {
            System.out.println("Do you want to update name? (y/n): ");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                System.out.println("You chose not to update name.");
                break;
            }

            System.out.println("Please input course name: ");
            String name = new Scanner(System.in).nextLine();
            if (name.isBlank()) {
                System.err.println("Your input is Empty!");
            } else if (!name.matches("^[a-zA-Z\\s]{1,100}$")) {
                System.err.println("Please input course name from 1 to 100 characters"
                        + "and must in alphabet.");
            } else {
                updateCourse.setName(name);
                break;
            }
        }

        while (true) {
            System.out.println("Do you want to update credit course? (y/n): ");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                System.out.println("You chose not to update credit course.");
                break;
            }
            System.out.println("Please input price's service: ");
            try {
                int updateCredit = new Scanner(System.in).nextInt();
                if (updateCredit < 1 || updateCredit > Integer.MAX_VALUE) {
                    throw new InputMismatchException();
                }
                updateCourse.setCredit(updateCredit);
                break;
            } catch (InputMismatchException e) {
                System.err.println("Course credit must be a positive integer!");
            } catch (Exception e) {
                System.err.println("Invalid Input!");
            }

        }

        if (courseManagement.updateCourseByCode(updateCourse)) {
            System.out.println("Successfully updated the course with code"
                    + updateCourse.getCode());
        } else {
            System.err.println("The course with code " + updateCourse.getCode()
                    + " is not existed.");
        }
    }

    public static void findCourse() {
        System.out.println("Please input Course Code: ");
        String code = new Scanner(System.in).nextLine();
        Course course = courseManagement.findCourseByCode(code);
        if (course == null) {
            System.err.println("The Course with the provided code doesn't exist.");
            return;
        }
        System.out.println("==========");
        System.out.println("The Course you want to find: ");
        System.out.println("Code: " + course.getCode());
        System.out.println("Name: " + course.getName());
        System.out.println("Credit: " + course.getCredit());
        if (course.getStudents().size() > 0) {
            System.out.println("Student List: ");
            for (int i = 0; i < course.getStudents().size(); i++) {
                System.out.println("----------");
                Student campusStudent = course.getStudents().get(i);
                System.out.println("Student Id: " + campusStudent.getCode());
                System.out.println("Student Name: " + campusStudent.getName());
                System.out.println("Student Gender: " + campusStudent.getGender());
                System.out.println("Student Address: " + campusStudent.getAddress());
                System.out.println("----------");
            }
        }
        System.out.println("==========");
    }

    public static void campusMgt() {
        int choice = 0;
        while (true) {
            System.out.println("====== CAMPUS MANAGEMENT =====");
            System.out.println("1. Add a campus");
            System.out.println("2. Update campus by code");
            System.out.println("3. Delete campus by code");
            System.out.println("4. Find campus by code");
            System.out.println("5. Add student to campus.");
            System.out.println("6. Back to menu");
            System.out.println("==============================");
            try {
                System.out.println("Please choose: ");
                choice = new Scanner(System.in).nextInt();
                if (choice < 1 || choice > 6) {
                    throw new InputMismatchException();
                }
                switch (choice) {
                    case 1:
                        addCampus();
                        break;
                    case 2:
                        updateCampus();
                        break;
                    case 3:
                        deleteCampus();
                        break;
                    case 4:
                        findCampus();
                        break;
                    case 5:
                        addStudentToCampus();
                        break;
                    case 6:
                        return;

                }
            } catch (Exception e) {
                System.err.println("Only accept from 1 to 5");

            }
        }

    }

    public static void addStudentToCampus() {
        if (studentManagement.getSize() < 1) {
            System.err.println("There is must at least 1 student to add.");
            return;
        }
        if (campusManagement.getSize() < 1) {
            System.err.println("There is must at least 1 campus to add.");
            return;
        }

        while (true) {
            System.out.println("Please input student id to add: ");
            String studentId = new Scanner(System.in).nextLine();
            Student s = studentManagement.findStudentByCode(studentId);
            if (s == null) {
                System.out.println("The student does not exist with id " + studentId);
                continue;
            }
            if (s.getCampus() != null) {
                while (true) {
                    System.out.println("This student already in a campus with id " + s.getCampus().getId());
                    System.out.println("Do you want to delete student from that campus? (y/n): ");
                    String choice = new Scanner(System.in).nextLine();
                    if (choice.equalsIgnoreCase("n")) {
                        System.out.println("You chose not to add campus to student.");
                        return;
                    }
                    s.setCampus(null);
                    campusManagement.deleteStudentFromCampus(s.getCode());
                    System.out.println("Successfully removed old campus from this student.");
                    break;
                }
            }
            Campus c = null;
            while (true) {
                System.out.println("Please input campus id to add: ");
                String campusId = new Scanner(System.in).nextLine();
                c = campusManagement.findCampusById(campusId);
                if (c == null) {
                    System.out.println("The campus does not exist with id " + campusId);
                    continue;
                }
                break;
            }

            while (true) {
                System.out.println("Do you really to add student " + studentId
                        + " to campus " + c.getId() + "(y/n)? ");
                String choice = new Scanner(System.in).nextLine();
                if (choice.equalsIgnoreCase("n")) {
                    System.out.println("You chose not to add student to campus.");
                    return;
                }
                if (campusManagement.addStudentToCampus(s, c)) {
                    studentManagement.addCampusToStudent(c, s);
                    System.out.println("Successfully to add a student to campus.");
                } else {
                    System.out.println("Failed to add because this student is already existed.");
                }
                return;
            }
        }
    }

    public static void addCampus() {
        while (true) {
            Campus campus = new Campus();

            while (true) {
                System.out.println("Please input campus id (Cxxx): ");
                String id = new Scanner(System.in).nextLine();
                if (id.isBlank()) {
                    System.err.println("Your input is Empty");
                } else if (!id.matches("^C[\\d]{2,}$")) {
                    System.err.println("Please input id in format (Cxxx)!");
                } else {
                    campus.setId(id);
                    break;
                }
            }

            while (true) {
                System.out.println("Please input campus name: ");
                String name = new Scanner(System.in).nextLine();
                if (name.isBlank()) {
                    System.err.println("Your input is Empty!");
                } else if (!name.matches("^[a-zA-Z\\s]{1,100}$")) {
                    System.err.println("Please input campus name from 1 to 100 characters"
                            + " and must in alphabet!");
                } else {
                    campus.setName(name);
                    break;
                }
            }

            while (true) {
                System.out.println("Please input campus address: ");
                String address = new Scanner(System.in).nextLine();
                if (address.isBlank()) {
                    System.err.println("Your input is Empty!");
                } else if (!address.matches("^[a-zA-Z\\s]{3,100}$")) {
                    System.err.println("Please input campus address from 3 to 100 characters"
                            + " and must in alphabet!");
                } else {
                    campus.setAddress(address);
                    break;
                }
            }
            if (campusManagement.addCampus(campus)) {
                System.out.println("Successfully added a campus.");
            } else {
                System.out.println("The campus with id " + campus.getId() + " is existed.");
            }

            System.out.println("Do you want to add more? (y/n)?");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                return;
            }
        }
    }

    public static void deleteCampus() {
        System.out.println("Please input campus id: ");
        String id = new Scanner(System.in).nextLine();
        Campus campus = campusManagement.findCampusById(id);
        if (campus == null) {
            System.out.println("The campus with the provided Id doesn't exist.");
            return;
        }
        while (true) {
            System.out.println("Do you want to delete this campus? (y/n)?");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                return;
            }
            if (campusManagement.deleteCampusById(id)) {
                studentManagement.deleteCampusFromStudents(id);
                System.out.println("Successfully deleted campus with id " + id);
                return;
            }
        }
    }

    public static void updateCampus() {
        System.out.println("Please input owner id: ");
        String updateId = new Scanner(System.in).nextLine();
        Campus updateCampus = campusManagement.findCampusById(updateId);
        if (updateCampus == null) {
            System.err.println("The campus with the provided Id doesn't exist.");
            return;
        }

        while (true) {
            System.out.println("Do you want to update name? (y/n): ");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                System.out.println("You chose not to update name.");
                break;
            }

            System.out.println("Please input campus name: ");
            String name = new Scanner(System.in).nextLine();
            if (name.isBlank()) {
                System.err.println("Your input is Empty!");
            } else if (!name.matches("^[a-zA-Z\\s]{1,100}$")) {
                System.err.println("Please input campus name from 1 to 100 characters"
                        + "and must in alphabet.");
            } else {
                updateCampus.setName(name);
                break;
            }
        }

        while (true) {
            System.out.println("Do you want to update address? (y/n): ");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                System.out.println("You chose not to update address.");
                break;
            }

            System.out.println("Please input campus address: ");
            String address = new Scanner(System.in).nextLine();
            if (address.isBlank()) {
                System.err.println("Your input is Empty!");
            } else if (!address.matches("^[a-zA-Z\\s]{3,100}$")) {
                System.err.println("Please input campus address from 3 to 100 characters"
                        + "and must in alphabet.");
            } else {
                updateCampus.setAddress(address);
                break;
            }
        }

        if (campusManagement.updateCampusById(updateCampus)) {
            System.out.println("Successfully updated the campus with id"
                    + updateCampus.getId());
        } else {
            System.out.println("The campus with id " + updateCampus.getId()
                    + " is not existed.");
        }
    }

    public static void findCampus() {
        System.out.println("Please input campus id: ");
        String id = new Scanner(System.in).nextLine();
        Campus campus = campusManagement.findCampusById(id);
        if (campus == null) {
            System.err.println("The campus with the provided Id doesn't exist.");
            return;
        }
        System.out.println("==========");
        System.out.println("The Campus you want to find: ");
        System.out.println("Id: " + campus.getId());
        System.out.println("Name: " + campus.getName());
        System.out.println("Address: " + campus.getAddress());
        if (campus.getStudent().size() > 0) {
            System.out.println("Student List: ");
            for (int i = 0; i < campus.getStudent().size(); i++) {
                System.out.println("----------");
                Student campusStudent = campus.getStudent().get(i);
                System.out.println("Student Id: " + campusStudent.getCode());
                System.out.println("Student Name: " + campusStudent.getName());
                System.out.println("Student Gender: " + campusStudent.getGender());
                System.out.println("Student Address: " + campusStudent.getAddress());
                System.out.println("----------");
            }
        }
        System.out.println("==========");
    }

}
