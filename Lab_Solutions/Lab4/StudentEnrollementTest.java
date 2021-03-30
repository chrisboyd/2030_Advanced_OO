/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eecs2030.lab4;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class StudentEnrollementTest {

    private LocalDate date = LocalDate.now();
    private Student student = new Student("11", "Jack", date, "none@no.no");
    private CourseOffering courseOffering = new CourseOffering(new Course("EECS2030", "AOOP"), 2, 'F', 'E', 2019, date);

    public StudentEnrollementTest() {
    }

    /**
     * Test of getStudent method, of class StudentEnrollement.
     */
    @Test
    public void testGetStudent() {
        Student jane = new Student("12", "Jane", date, "jane@no.no");
        StudentEnrollement instance = new StudentEnrollement(jane, courseOffering, date);
        Student expResult = new Student("12", "Jane", date, "jane@no.no");;
        Student result = instance.getStudent();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCourseOffering method, of class StudentEnrollement.
     */
    @Test
    public void testGetCourseOffering() {
        CourseOffering eecs2030 = new CourseOffering(new Course("EECS2030", "AOOP"), 2, 'F', 'E', 2019, date);
        StudentEnrollement instance = new StudentEnrollement(student, eecs2030, date);
        CourseOffering expResult = new CourseOffering(new Course("EECS2030", "AOOP"), 2, 'F', 'E', 2019, date);
        CourseOffering result = instance.getCourseOffering();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEnrollDate method, of class StudentEnrollement.
     */
    @Test
    public void testGetEnrollDate() {
        LocalDate ld = LocalDate.of(2018, 1, 1);
        StudentEnrollement instance = new StudentEnrollement(student, courseOffering, ld);
        LocalDate expResult = LocalDate.of(2018, 1, 1);;
        LocalDate result = instance.getEnrollDate();
        assertEquals(expResult, result);
    }

}
