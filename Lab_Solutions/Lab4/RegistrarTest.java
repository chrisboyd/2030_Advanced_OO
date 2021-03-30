/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eecs2030.lab4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class RegistrarTest {

    private Course defCourse = new Course("EECS1001", "Research Directions in Computing"); // Must not have prereqes for tests
    private LocalDate defDate = LocalDate.now(); // Must be today or later
    private Student defStudent = new Student("123", "John Jacob Jingleheimer Smith",
            defDate, "none@none.no"); // Must not have completed any courses
    private CourseOffering defCourseOffering = new CourseOffering(defCourse, 5, 'F', 'A', 2019, defDate);

    /**
     * List of strings with student data for testing
     */
    private List<String> studentData;
    /**
     * List of strings with course data for testing
     */
    private List<String> courseData;

    // Course Indices
    int eecs2030 = 9;
    int eecs1001 = 0;
    int eecs1012 = 2;

    public RegistrarTest() {
        loadStudentData();
        loadCourseData();
    }

    /**
     * Test of enroll method, of class Registrar.
     */
    @Test
    public void testEnroll() {
        // Null args
        assertThrows(Exception.class, () -> {
            Registrar.enroll(null, null);
        });
        assertThrows(Exception.class, () -> {
            Registrar.enroll(null, defCourseOffering);
        });
        assertThrows(Exception.class, () -> {
            Registrar.enroll(defStudent, null);
        });

        //Test 1) the course is not open for enrollment
        final CourseOffering closedOffering = new CourseOffering(defCourse, 5, 'F', 'A', 2019, LocalDate.of(2000, 1, 1));
        assertThrows(Exception.class, () -> {
            Registrar.enroll(defStudent, closedOffering);
        });

        //Test 2) the student has not completed the prerequisite courses
        Course c = buildCourse(eecs2030);
        final CourseOffering courseOfferingwithPrereqs = new CourseOffering(c, 1, 'F', 'A', 2019, defDate);
        assertThrows(Exception.class, () -> {
            Registrar.enroll(defStudent, courseOfferingwithPrereqs);
        });

        // Test valid input without prereqs
        try {
            CourseOffering courseOfferingWithoutPrereq = new CourseOffering(defCourse, 10, 'F', 'B', 2000, defDate);
            Registrar.enroll(defStudent, courseOfferingWithoutPrereq);
        } catch (Exception ex) {
            Logger.getLogger(RegistrarTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Adding without prereqs failed! " + ex.getMessage());
        }

        // Test valid input with prereqs
        try {
            Student studentWithPrereq = new Student("123", "John Jacob Jingleheimer Smith",
                    defDate, "none@none.no");
            studentWithPrereq.completeCourse("EECS1022"); // Expected prereq for 2030
            Registrar.enroll(studentWithPrereq, courseOfferingwithPrereqs);
        } catch (Exception ex) {
            Logger.getLogger(RegistrarTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Adding with prereqs failed! " + ex.getMessage());
        }

        //Test 3) the course is full
        final Student studentWithPrereq = new Student("555", "John Jacob Jingleheimer Smith",
                defDate, "none@none.no");
        studentWithPrereq.completeCourse("EECS1022"); // Expected prereq for 2030
        assertThrows(Exception.class, () -> {
            Registrar.enroll(defStudent, courseOfferingwithPrereqs);
        });
    }

    /**
     * Test of getEnrolledStudents and GetNumEnrolled methods, of class
     * Registrar.
     */
    @Test
    public void testGetEnrolledStudentsAndGetNumEnrolled() {
        Registrar.enrollments.clear();
        try {
            // Create list of students
            List<Student> students = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                students.add(buildStudent(i));
            }

            // Create list of offerings
            List<CourseOffering> coursesOfferings = new ArrayList<>();
            coursesOfferings.add(new CourseOffering(buildCourse(eecs1001), 10, 'F', 'A', 2019, defDate));
            CourseOffering courseToCheck = new CourseOffering(buildCourse(eecs1001), 10, 'F', 'A', 2019, defDate);
            coursesOfferings.add(new CourseOffering(buildCourse(eecs1012), 10, 'F', 'A', 2019, defDate));

            // Register half the students in each course
            List<Student> expResult = new ArrayList<>();
            for (int i = 0; i < students.size(); i++) {
                if (i % 2 == 0) {
                    Registrar.enroll(students.get(i), coursesOfferings.get(0));
                    expResult.add(students.get(i));
                } else {
                    Registrar.enroll(students.get(i), coursesOfferings.get(1));
                }
            }

            // Check output
            List<Student> resultList = Registrar.getEnrolledStudents(courseToCheck);
            int resultCount = Registrar.getNumEnrolled(courseToCheck);
            assertEquals(expResult, resultList);
            assertEquals(expResult.size(), resultCount);
        } catch (Exception ex) {
            Logger.getLogger(RegistrarTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
    }

    /**
     * Test of getCourseByTitle method, of class Registrar.
     */
    @Test
    public void testGetCourseByTitle() {
        Registrar.courses.clear();

        // Check emtpy map
        String title = "Random String";
        Course expResult = null;
        Course result = Registrar.getCourseByTitle(title);
        assertEquals(expResult, result);

        // Check for non-existent item
        Course course = buildCourse(eecs1001);
        Registrar.courses.put(course.getCode(), course);
        course = buildCourse(eecs1012);
        Registrar.courses.put(course.getCode(), course);
        title = "Random String";
        expResult = null;
        result = Registrar.getCourseByTitle(title);
        assertEquals(expResult, result);

        // Check for item that exists
        course = buildCourse(eecs2030);
        Registrar.courses.put(course.getCode(), course);
        title = new String(course.getTitle()); //Call new to test == or .equals(..);
        expResult = buildCourse(eecs2030);
        result = Registrar.getCourseByTitle(title);
        assertEquals(expResult, result);
    }

    /**
     * Test of getStudentByName method, of class Registrar.
     */
    @Test
    public void testGetStudentByName() {
        Registrar.students.clear();

        // Check emtpy map
        String name = "Random String";
        Student expResult = null;
        Student result = Registrar.getStudentByName(name);
        assertEquals(expResult, result);

        // Check for non-existent item
        Student student = buildStudent(0);
        Registrar.students.put(student.getId(), student);
        student = buildStudent(1);
        Registrar.students.put(student.getId(), student);
        name = "Random String";
        expResult = null;
        result = Registrar.getStudentByName(name);
        assertEquals(expResult, result);

        // Check for item that exists
        student = buildStudent(5);
        Registrar.students.put(student.getId(), student);
        name = new String(student.getName()); //Call new to test == or .equals(..);
        expResult = buildStudent(5);
        result = Registrar.getStudentByName(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of addStudent method, of class Registrar.
     */
    @Test
    public void testAddStudent() {
        Registrar.students.clear();

        try {
            Student student;
            for (int i = 0; i < 5; i++) {
                student = buildStudent(i);
                Registrar.addStudent(student.getId(), student);
            }
            student = buildStudent(15);
            final Student expResult = buildStudent(15);
            Registrar.addStudent(student.getId(), student);
            if (Registrar.students.size() == 0) {
                fail("Student list still emtpy");
            } else {
                Student result = Registrar.students.get(student.getId());
                assertEquals(expResult, result);

                // Check for duplicates
                assertThrows(Exception.class, () -> {
                    Registrar.addStudent(expResult.getId(), expResult);
                });
            }
        } catch (Exception ex) {
            Logger.getLogger(RegistrarTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
    }

    /**
     * Test of removeStudent method, of class Registrar.
     */
    @Test
    public void testRemoveStudent() {
        Registrar.students.clear();

        // Test remove on empty list
        assertThrows(Exception.class, () -> {
            Registrar.removeStudent(null);
        });
        assertThrows(Exception.class, () -> {
            Registrar.removeStudent("Not there");
        });

        // Add some students
        Student student;
        for (int i = 0; i < 5; i++) {
            student = buildStudent(i);
            Registrar.students.put(student.getId(), student);
        }

        // Remove a student exptected to be there
        try {
            student = buildStudent(10);
            Registrar.students.put(student.getId(), student);
            int sizeBefore = Registrar.students.size();
            Registrar.removeStudent(student.getId());
            int sizeAfter = Registrar.students.size();
            assertNotEquals(sizeAfter, sizeBefore);
        } catch (Exception ex) {
            Logger.getLogger(RegistrarTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
    }

    /**
     * Test of getCourseOffering method, of class Registrar.
     */
    @Test
    public void testGetCourseOffering() {
        Registrar.offerings.clear();

        // Test Empty List
        String courseCode = "";
        char section = ' ';
        char term = ' ';
        int year = 0;
        CourseOffering expResult = null;
        CourseOffering result = Registrar.getCourseOffering(courseCode, section, term, year);
        assertEquals(expResult, result);

        // Populate list
        Registrar.offerings.add(new CourseOffering(buildCourse(eecs1012), 10, 'F', 'A', 2019, defDate));
        Registrar.offerings.add(new CourseOffering(buildCourse(eecs1012), 10, 'F', 'B', 2019, defDate));
        Registrar.offerings.add(new CourseOffering(buildCourse(eecs1012), 10, 'W', 'A', 2019, defDate)); // Test offering
        Registrar.offerings.add(new CourseOffering(buildCourse(eecs1012), 10, 'W', 'B', 2019, defDate));
        Registrar.offerings.add(new CourseOffering(buildCourse(eecs2030), 10, 'F', 'A', 2019, defDate));
        Registrar.offerings.add(new CourseOffering(buildCourse(eecs2030), 10, 'F', 'B', 2019, defDate));
        Registrar.offerings.add(new CourseOffering(buildCourse(eecs2030), 10, 'F', 'C', 2019, defDate));
        Registrar.offerings.add(new CourseOffering(buildCourse(eecs2030), 10, 'F', 'D', 2019, defDate));

        Course testCourse = buildCourse(eecs1012);

        // Test on non empty list with item present
        courseCode = new String(testCourse.getCode());
        section = 'A';
        term = 'W';
        year = 2019;
        expResult = new CourseOffering(buildCourse(eecs1012), 10, 'W', 'A', 2019, defDate);
        result = Registrar.getCourseOffering(courseCode, section, term, year);
        assertEquals(expResult, result);

        // Test Code differnt
        courseCode = "WRONG VALUE";
        section = 'A';
        term = 'W';
        year = 2019;
        expResult = null;
        result = Registrar.getCourseOffering(courseCode, section, term, year);
        assertEquals(expResult, result);

        // Test section differnt
        courseCode = new String(testCourse.getCode());
        section = 'Z';
        term = 'W';
        year = 2019;
        expResult = null;
        result = Registrar.getCourseOffering(courseCode, section, term, year);
        assertEquals(expResult, result);

        // Test term differnt
        courseCode = new String(testCourse.getCode());
        section = 'A';
        term = 'S';
        year = 2019;
        expResult = null;
        result = Registrar.getCourseOffering(courseCode, section, term, year);
        assertEquals(expResult, result);

        // Test year differnt
        courseCode = new String(testCourse.getCode());
        section = 'A';
        term = 'W';
        year = 2000;
        expResult = null;
        result = Registrar.getCourseOffering(courseCode, section, term, year);
        assertEquals(expResult, result);

        // Test Should find
        courseCode = new String(testCourse.getCode());
        section = 'A';
        term = 'W';
        year = 2019;
        expResult = new CourseOffering(testCourse, 10, 'W', 'A', 2019, defDate);
        result = Registrar.getCourseOffering(courseCode, section, term, year);
        assertEquals(expResult, result);
    }

    /**
     * Builds the student who matches {@code index} in the {@code studentData}
     *
     * @param index the index of the student to build
     * @return the student who matches the index
     */
    private Student buildStudent(int index) {
        Student result;
        String line = studentData.get(index);
        String[] s = line.split(",");
        String id = s[0].trim();
        String name = s[1].trim();
        int y = Integer.parseInt(s[2].trim());
        int m = Integer.parseInt(s[3].trim());
        int d = Integer.parseInt(s[4].trim());
        String email = s[5].trim();
        LocalDate joinDate = LocalDate.of(y, m, d);
        result = new Student(id, name, joinDate, email);
        if (s.length > 6) {
            String[] cc = s[6].split(";");
            for (String w : cc) {
                result.completeCourse(w.trim());
            }
        }
        return result;
    }

    /**
     * Builds a course that matches {@code index} in the {@code courseData}
     *
     * @param index the index of the course to build
     * @return the course matches the index
     */
    private Course buildCourse(int index) {
        String line = courseData.get(index);
        String[] info = line.split(",");
        Course result = new Course(info[0].trim(), info[1].trim());
        if (info.length > 2) {
            String[] preList = info[2].split(";");
            for (String p : preList) {
                result.addPrerequisite(p.trim());
            }
        }
        return result;
    }

    /**
     * Fill student data
     */
    private void loadStudentData() {
        studentData = new ArrayList<>();
        studentData.add("2131347, Aaron,2018,5,5, aaron@yorku.ca,EECS1020");
        studentData.add("2170150, Abel,2017,3,21, abel@yorku.ca,EECS1030");
        studentData.add("2162607, Abner,2012,12,9, abner@yorku.ca,EECS1030");
        studentData.add("2155469, Abraham,2016,4,9, abraham@yorku.ca,EECS1011; EECS1021; EECS2030");
        studentData.add("2133724, Adam,2017,7,5, adam@yorku.ca,EECS1012; EECS1022; EECS2030");
        studentData.add("2166209, Adelbert,2012,12,18, adelbert@yorku.ca,EECS1028");
        studentData.add("2137229, Adrian,2016,12,28, adrian@yorku.ca,EECS1030; EECS2031");
        studentData.add("2187226, Alan,2013,1,8, alan@yorku.ca,EECS1030");
        studentData.add("2189856, Albert,2017,9,16, albert@yorku.ca,EECS1012");
        studentData.add("2120957, Alexander,2017,3,14, alexander@yorku.ca,EECS1012; EECS1022");
        studentData.add("2135016, Alfred,2017,8,6, alfred@yorku.ca,EECS1520");
        studentData.add("2149680, Algernon,2018,7,16, algernon@yorku.ca,EECS1001");
        studentData.add("2153509, Alister,2016,3,25, alister@yorku.ca,EECS1012");
        studentData.add("2155909, Alonso,2014,11,1, alonso@yorku.ca,EECS1011; EECS1021");
        studentData.add("2160848, Alphonso,2018,6,7, alphonso@yorku.ca,EECS1012; EECS1022");
        studentData.add("2129254, Alva,2012,3,27, alva@yorku.ca,EECS1011");
        studentData.add("2141081, Alvin,2016,12,15, alvin@yorku.ca,");
        studentData.add("2182370, Ambrose,2015,7,24, ambrose@yorku.ca,");
        studentData.add("2162067, Amos,2013,7,1, amos@yorku.ca,");
        studentData.add("2122330, Andrew,2014,1,29, andrew@yorku.ca,EECS1020");
        studentData.add("2166442, Angus,2014,5,13, angus@yorku.ca,EECS1011");
        studentData.add("2187235, Anselm,2014,7,1, anselm@yorku.ca,EECS1012; EECS1022");
        studentData.add("2177009, Anthony,2016,1,21, anthony@yorku.ca,EECS1012");
        studentData.add("2172470, Archibald,2015,3,27, archibald@yorku.ca,EECS1001");
        studentData.add("2142553, Arnold,2012,2,25, arnold@yorku.ca,EECS1012");
        studentData.add("2170645, Arthur,2015,3,20, arthur@yorku.ca,EECS1011; EECS1021");
        studentData.add("2182692, Augustus,2015,5,22, augustus@yorku.ca,");
        studentData.add("2152981, Augustine,2016,3,14, augustine@yorku.ca,EECS1012");
        studentData.add("2166544, Austin,2016,8,2, austin@yorku.ca,EECS1012; EECS1022");
        studentData.add("2154099, Avery,2014,9,25, avery@yorku.ca,EECS1030");
        studentData.add("2130243, Baldwin,2014,2,19, baldwin@yorku.ca,EECS1030");
        studentData.add("2160795, Barrett,2014,1,1, barrett@yorku.ca,EECS1030");
        studentData.add("2158165, Bartholomew,2019,8,13, bartholomew@yorku.ca,");
        studentData.add("2177983, Basil,2018,1,9, basil@yorku.ca,EECS1012; EECS1022");
        studentData.add("2180683, Benedict,2016,12,22, benedict@yorku.ca,EECS1011; EECS1021");
        studentData.add("2185383, Benjamin,2012,6,21, benjamin@yorku.ca,EECS1030");
        studentData.add("2182636, Bennet,2015,1,13, bennet@yorku.ca,EECS1020");
        studentData.add("2123645, Bernard,2019,11,17, bernard@yorku.ca,EECS1011");
        studentData.add("2176543, Bert,2012,11,23, bert@yorku.ca,EECS1001");
        studentData.add("2129043, Berthold,2018,8,7, berthold@yorku.ca,EECS1012; EECS1022");
        studentData.add("2143221, Bertram,2013,3,28, bertram@yorku.ca,");
        studentData.add("2189078, Bill,2016,11,23, bill@yorku.ca,EECS1012; EECS1022");
        studentData.add("2130614, Blair,2018,3,22, blair@yorku.ca,EECS1012; EECS1022");
        studentData.add("2129611, Blake,2016,5,12, blake@yorku.ca,EECS1028");
        studentData.add("2186059, Boris,2015,2,11, boris@yorku.ca,");
        studentData.add("2159148, Bradford,2015,12,3, bradford@yorku.ca,EECS1011; EECS1021");
        studentData.add("2147899, Bradley,2015,4,26, bradley@yorku.ca,");
        studentData.add("2149559, Brady,2015,3,3, brady@yorku.ca,EECS1011");
        studentData.add("2170761, Brandon,2013,12,22, brandon@yorku.ca,EECS1028");
    }

    private void loadCourseData() {
        courseData = new ArrayList<>();
        courseData.add("EECS1001,Research Directions in Computing,");
        courseData.add("EECS1011,Computational Thinking,");
        courseData.add("EECS1012,Net-centric Introduction to Computing,");
        courseData.add("EECS1021,Object Oriented Programming,EECS1011");
        courseData.add("EECS1022,Programming for Mobile Computers,EECS1012");
        courseData.add("EECS1520,Computer Use: Fundamentals,");
        courseData.add("EECS2001,Intro. to the Theory of Computation,EECS1021; EECS1022; EECS1720; EECS1030; EECS1028");
        courseData.add("EECS2011,Fundamentals of Data Structures,EECS1030; EECS2030; EECS1028");
        courseData.add("EECS2021,Computer Organization,EECS1021; EECS1022; EECS1720; EECS1030");
        courseData.add("EECS2030,Advanced Object Oriented Programming,EECS1021; EECS1020; EECS1022; EECS1720");
        courseData.add("EECS2031,Software Tools,EECS1030; EECS2030");
        courseData.add("EECS2200,Electrical Circuits,");
        courseData.add("EECS3101,Design and Analysis of Algorithms,EECS2030; EECS1030; EECS2011");
        courseData.add("EECS3201,Digital Logic Design,EECS2030; EECS1030; EECS2021; EECS2200");
        courseData.add("EECS3213,Communication Networks,EECS2030; EECS1030");
        courseData.add("EECS3221,Operating System Fundamentals,EECS2030; EECS1030; EECS2021; EECS2031");
        courseData.add("EECS3421,Introduction to Database Systems,EECS2030; EECS1030");
        courseData.add("EECS3451,Signals and Systems,EECS2030; EECS1030; EECS2021");
        courseData.add("EECS3481,Applied Cryptography,EECS2030; EECS1030; EECS2011");
        courseData.add("EECS4115,Computational Complexity,EECS2030; EECS1030; EECS2001; EECS3101");
        courseData.add("EECS4312,Software Engineering Requirements,EECS2030; EECS1030; EECS3311");
        courseData.add("EECS4314,Advanced Software Engineering,EECS2030; EECS1030; EECS3311");
        courseData.add("MATH1090,Intro. to Logic for Computer Science,");
    }
}
