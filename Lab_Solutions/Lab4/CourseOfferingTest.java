/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eecs2030.lab4;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class CourseOfferingTest {

    Course course = new Course("EECS2030", "Advanced Object Oriented Programming");
    LocalDate date = LocalDate.now();

    public CourseOfferingTest() {
    }

    /**
     * Test of Constructor, of class CourseOffering. Course(course, int
     * capacity, char term, char section, int year, LocalDate openUntil)
     */
    @Test
    public void testConstructor() {
        // Null Course
        assertThrows(IllegalArgumentException.class, () -> {
            new CourseOffering(null, 5, 'F', 'A', 2019, date);
        });

        // Invalid term
        assertThrows(IllegalArgumentException.class, () -> {
            new CourseOffering(null, 5, 'A', 'A', 2019, date);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new CourseOffering(null, 5, 'B', 'A', 2019, date);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new CourseOffering(null, 5, 'Z', 'A', 2019, date);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new CourseOffering(null, 5, '9', 'A', 2019, date);
        });

        // Test no exception
        new CourseOffering(course, 5, 'F', 'A', 2019, date);
        new CourseOffering(course, 5, 'W', 'A', 2019, date);
        new CourseOffering(course, 5, 'S', 'A', 2019, date);
    }

    /**
     * Test of getCapacity method, of class CourseOffering.
     */
    @Test
    public void testGetCapacity() {
        CourseOffering instance = new CourseOffering(course, 5, 'F', 'A', 2019, date);
        int expResult = 5;
        int result = instance.getCapacity();
        assertEquals(expResult, result);

        instance = new CourseOffering(course, 0, 'F', 'A', 2019, date);
        expResult = 0;
        result = instance.getCapacity();
        assertEquals(expResult, result);

        instance = new CourseOffering(course, 100, 'F', 'A', 2019, date);
        expResult = 100;
        result = instance.getCapacity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCourse method, of class CourseOffering.
     */
    @Test
    public void testGetCourse() {
        Course eecs2030 = new Course("EECS2030", "Advanced Object Oriented Programming");

        CourseOffering instance = new CourseOffering(eecs2030, 100, 'F', 'A', 2019, date);
        Course expResult = new Course("EECS2030", "Advanced Object Oriented Programming");
        Course result = instance.getCourse();
        assertEquals(expResult, result);

        Course unExpResult = new Course("EECS2200", "Electrical Circuits");
        result = instance.getCourse();
        assertNotEquals(unExpResult, result);
    }

    /**
     * Test of getTerm method, of class CourseOffering.
     */
    @Test
    public void testGetTerm() {
        CourseOffering instance = new CourseOffering(course, 5, 'F', 'A', 2019, date);
        char expResult = 'F';
        char result = instance.getTerm();
        assertEquals(expResult, result);

        instance = new CourseOffering(course, 5, 'W', 'A', 2019, date);
        expResult = 'W';
        result = instance.getTerm();
        assertEquals(expResult, result);

        instance = new CourseOffering(course, 5, 'S', 'A', 2019, date);
        expResult = 'S';
        result = instance.getTerm();
        assertEquals(expResult, result);

    }

    /**
     * Test of getSection method, of class CourseOffering.
     */
    @Test
    public void testGetSection() {
        CourseOffering instance = new CourseOffering(course, 5, 'F', 'A', 2019, date);
        char expResult = 'A';
        char result = instance.getSection();
        assertEquals(expResult, result);

        instance = new CourseOffering(course, 5, 'F', 'Z', 2019, date);
        expResult = 'Z';
        result = instance.getSection();
        assertEquals(expResult, result);

        instance = new CourseOffering(course, 5, 'F', 'E', 2019, date);
        expResult = 'E';
        result = instance.getSection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getYear method, of class CourseOffering.
     */
    @Test
    public void testGetYear() {
        CourseOffering instance = new CourseOffering(course, 5, 'F', 'E', 2000, date);
        int expResult = 2000;
        int result = instance.getYear();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOpenUntil method, of class CourseOffering.
     */
    @Test
    public void testGetOpenUntil() {
        CourseOffering instance = new CourseOffering(course, 5, 'F', 'E', 2019, LocalDate.now());
        LocalDate expResult = LocalDate.now();
        LocalDate result = instance.getOpenUntil();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class CourseOffering.
     */
    @Test
    public void testHashCode() {
        // Equal to itself
        Course eecs2030 = new Course("EECS2030", "Advanced Object Oriented Programming");
        CourseOffering instance = new CourseOffering(eecs2030, 5, 'F', 'E', 2019, date);
        int expResult = instance.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

        // Equal to Object with same state
        Course otherCourse = new Course("EECS2030", "Advanced Object Oriented Programming");
        CourseOffering otherOffering = new CourseOffering(otherCourse, 5, 'F', 'E', 2019, date);
        expResult = otherOffering.hashCode();
        assertEquals(expResult, result);

        // Not Equal to diff course
        otherCourse = new Course("EECS2200", "Electrical Circuits");
        otherOffering = new CourseOffering(otherCourse, 5, 'F', 'E', 2019, date);
        expResult = otherOffering.hashCode();
        assertNotEquals(expResult, result);

        // Not Equal to diff section
        otherOffering = new CourseOffering(eecs2030, 5, 'F', 'A', 2019, date);
        expResult = otherOffering.hashCode();
        assertNotEquals(expResult, result);

        // Not Equal to diff term
        otherOffering = new CourseOffering(eecs2030, 5, 'W', 'E', 2019, date);
        expResult = otherOffering.hashCode();
        assertNotEquals(expResult, result);

        // Not Equal to diff year
        otherOffering = new CourseOffering(eecs2030, 5, 'F', 'E', 2020, date);
        expResult = otherOffering.hashCode();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of equals method, of class CourseOffering.
     */
    @Test
    public void testEquals() {
        Course eecs2030 = new Course("EECS2030", "Advanced Object Oriented Programming");

        // Test Null
        Object obj = null;
        CourseOffering instance = new CourseOffering(eecs2030, 5, 'F', 'E', 2019, date);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

        // Test itself
        expResult = true;
        result = instance.equals(instance);
        assertEquals(expResult, result);

        // Test differnt class
        obj = "A String";
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        // Test same course state but different object
        Course otherCourse = new Course("EECS2030", "Advanced Object Oriented Programming");
        obj = new CourseOffering(otherCourse, 5, 'F', 'E', 2019, date);
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        // Not Equal to diff course
        otherCourse = new Course("EECS2200", "Electrical Circuits");
        obj = new CourseOffering(otherCourse, 5, 'F', 'E', 2019, date);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        // Not Equal to diff section
        obj = new CourseOffering(eecs2030, 5, 'F', 'A', 2019, date);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        // Not Equal to diff term
        obj = new CourseOffering(eecs2030, 5, 'W', 'E', 2019, date);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        // Not Equal to diff year
        obj = new CourseOffering(eecs2030, 5, 'F', 'E', 2020, date);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class CourseOffering.
     */
    @Test
    public void testToString() {
        char section = 'M';
        char term = 'F';
        int year = 2019;
        CourseOffering instance = new CourseOffering(course, 5, term, section, year, date);
        String expResult = String.format("CourseOffering [%s%c %c%d]", course.getCode(), section, term, year);
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
