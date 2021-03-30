/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eecs2030.lab4;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StudentTest {

    LocalDate date = LocalDate.now();

    public StudentTest() {
    }

    /**
     * Test of getId method, of class Student.
     */
    @Test
    public void testGetId() {
        Student instance = new Student("123", "John Jacob Jingleheimer Smith",
                date, "none@none.no");
        String expResult = "123";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Student.
     */
    @Test
    public void testGetName() {
        Student instance = new Student("123", "John Jacob Jingleheimer Smith",
                date, "none@none.no");
        String expResult = "John Jacob Jingleheimer Smith";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class Student.
     */
    @Test
    public void testGetEmail() {
        Student instance = new Student("123", "John Jacob Jingleheimer Smith",
                date, "none@none.no");
        String expResult = "none@none.no";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJoinDate method, of class Student.
     */
    @Test
    public void testGetJoinDate() {
        Student instance = new Student("123", "John Jacob Jingleheimer Smith",
                LocalDate.of(2018, 9, 1), "none@none.no");
        LocalDate expResult = LocalDate.of(2018, 9, 1);
        LocalDate result = instance.getJoinDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class Student.
     */
    @Test
    public void testSetEmail() {
        String email = "new@none.no";
        Student instance = new Student("123", "John Jacob Jingleheimer Smith",
                date, "none@none.no");
        instance.setEmail(email);
        String expResult = "new@none.no";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompletedCourses method, of class Student.
     */
    @Test
    public void testGetCompletedCourses() {
        Student instance = new Student("123", "John Jacob Jingleheimer Smith",
                date, "none@none.no");
        HashSet<String> expResult = new HashSet<>(
                Arrays.asList(new String[]{"EECS1021", "EECS1020"}));
        instance.completeCourse("EECS1021");
        instance.completeCourse("EECS1020");
        HashSet<String> result = instance.getCompletedCourses();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Student.
     */
    @Test
    public void testCompareTo() {
        // Same State
        Student other = new Student("111", "John Jacob Jingleheimer Smith",
                LocalDate.of(2018, 9, 2), "none@none.no");
        Student instance = new Student("111", "John Jacob Jingleheimer Smith",
                LocalDate.of(2018, 9, 2), "none@none.no");
        int result = instance.compareTo(other);
        assertTrue(result == 0);

        // Other join date Later
        other = new Student("444", "John Jacob Jingleheimer Smith",
                LocalDate.of(2018, 9, 3), "none@none.no");
        result = instance.compareTo(other);
        assertTrue(result < 0);

        // Other join date Eariler
        other = new Student("444", "John Jacob Jingleheimer Smith",
                LocalDate.of(2018, 9, 1), "none@none.no");
        result = instance.compareTo(other);
        assertTrue(result > 0);

        // Other id Bigger
        other = new Student("444", "John Jacob Jingleheimer Smith",
                LocalDate.of(2018, 9, 2), "none@none.no");
        result = instance.compareTo(other);
        assertTrue(result < 0);

        // Other id Smaller
        other = new Student("000", "John Jacob Jingleheimer Smith",
                LocalDate.of(2018, 9, 2), "none@none.no");
        result = instance.compareTo(other);
        assertTrue(result > 0);
    }

    /**
     * Test of equals method, of class Student.
     */
    @Test
    public void testEquals() {
        // Test Null
        Object obj = null;
        Student instance = new Student("123", "John Jacob Jingleheimer Smith",
                LocalDate.of(2019, 9, 1), "none@none.no");
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

        // Test object with same state
        obj = new Student("123", "John Jacob Jingleheimer Smith",
                LocalDate.of(2019, 9, 1), "none@none.no");
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        // Test inverse of equals gives same result
        expResult = true;
        result = obj.equals(instance);
        assertEquals(expResult, result);

        // Test differnt state
        obj = new Student("555", "Just John",
                LocalDate.of(2019, 9, 5), "n5@none.no");
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        // Test object with id but other values changed
        obj = new Student("123", "Just John",
                LocalDate.of(2019, 9, 5), "n5@none.no");
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        // Test with only id different
        obj = new Student("555", "John Jacob Jingleheimer Smith",
                LocalDate.of(2019, 9, 1), "none@none.no");
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Student.
     */
    @Test
    public void testHashCode() {
        Student instance = new Student("123", "John Jacob Jingleheimer Smith",
                date, "none@none.no");
        Student sameId = new Student("123", "Just John",
                date, "n5@none.no");
        Student sameOthers = new Student("555", "John Jacob Jingleheimer Smith",
                date, "none@none.no");
        assertEquals(instance.hashCode(), instance.hashCode()); //Equal to itself
        assertEquals(instance.hashCode(), sameId.hashCode()); //Equal if id is same
        assertNotEquals(instance.hashCode(), sameOthers.hashCode()); //Not Equal if id is not the same
    }

    /**
     * Test of toString method, of class Student.
     */
    @Test
    public void testToString() {
        Student instance = new Student("123", "John Jacob Jingleheimer Smith",
                date, "none@none.no");
        String expResult = String.format("Student [%s (%s)]", "John Jacob Jingleheimer Smith", "123");
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
