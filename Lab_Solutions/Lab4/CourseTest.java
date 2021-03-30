/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eecs2030.lab4;

import java.util.Arrays;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class CourseTest {

    public CourseTest() {
    }

    /**
     * Test of Course(String code, String title) constructor, of class Course.
     */
    @Test
    public void testCourse() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Course(null, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Course("", null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Course(null, "");
        });
    }

    /**
     * Test of getCode method, of class Course. Using same string object
     */
    @Test
    public void testGetCode1() {
        String expResult = "EECS2030";
        Course instance = new Course(expResult, "");
        String result = instance.getCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCode method, of class Course. Using different string object
     */
    @Test
    public void testGetCode2() {
        Course instance = new Course("EECS2030", "");
        String expResult = "EECS2030";
        String result = instance.getCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCode method, of class Course. Using lower case input
     */
    @Test
    public void testGetCode3() {
        Course instance = new Course("eecs2030", "");
        String expResult = "EECS2030";
        String result = instance.getCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTitle method, of class Course.
     */
    @Test
    public void testGetTitle() {
        Course instance = new Course("", "Advanced Object Oriented Programming");
        String expResult = "Advanced Object Oriented Programming";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrerequisite and addPrerequisite methods, of class Course.
     */
    @Test
    public void testPrerequisite1() {
        Course instance = new Course("EECS2030", "Advanced Object Oriented Programming");
        String[] values = {"EECS1021", "EECS1020", "EECS1022", "EECS1720"};
        HashSet<String> expResult = new HashSet<>();
        expResult.addAll(Arrays.asList(values));
        instance.addPrerequisite("EECS1021");
        instance.addPrerequisite("EECS1020");
        instance.addPrerequisite("EECS1022");
        instance.addPrerequisite("EECS1720");
        HashSet<String> result = instance.getPrerequisite();
        assertEquals(expResult, result);
    }

    /**
     * Test for prerequisite composition, of class Course.
     */
    @Test
    public void testPrerequisite2() {
        Course instance = new Course("EECS2030", "Advanced Object Oriented Programming");
        HashSet<String> expResult = new HashSet<>();
        expResult.add("ONLY");
        instance.addPrerequisite("ONLY");
        HashSet<String> changed = instance.getPrerequisite();
        changed.add("ANOTHER");
        HashSet<String> result = instance.getPrerequisite();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Course.
     */
    @Test
    public void testCompareTo() {
        Course other = new Course("EECS2030", "Advanced Object Oriented Programming");
        Course instance = new Course("EECS2031", "Software Tools");

        int expResult = 1;
        int result = instance.compareTo(other);
        assertEquals(expResult, result);

        expResult = -1;
        result = other.compareTo(instance);
        assertEquals(expResult, result);

        other = new Course("EECS2031", "Software Tools");
        expResult = 0;
        result = instance.compareTo(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Course.
     */
    @Test
    public void testEquals() {
        // Test Null
        Object obj = null;
        Course instance = new Course("EECS2030", "Advanced Object Oriented Programming");
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
        obj = new Course("EECS2030", "Advanced Object Oriented Programming");
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        // Test inverse of equals gives same result
        expResult = true;
        result = obj.equals(instance);
        assertEquals(expResult, result);

        // Test differnt course
        obj = new Course("EECS2031", "Software Tools");
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

        // Test object with same code but different title
        obj = new Course("EECS2030", "Different Title");
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result, "Title shouldn't make them not equal");

        // Test with differnt code
        obj = new Course("DIFF", "Advanced Object Oriented Programming");
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Course.
     */
    @Test
    public void testHashCode() {
        Course instance = new Course("EECS2030", "Advanced Object Oriented Programming");
        Course sameCode = new Course("EECS2030", "Diff");
        Course sameTitle = new Course("Diff", "Advanced Object Oriented Programming");
        assertEquals(instance.hashCode(), instance.hashCode()); //Equal to itself
        assertEquals(instance.hashCode(), sameCode.hashCode()); //Equal if code is same
        assertNotEquals(instance.hashCode(), sameTitle.hashCode()); //Not Equal if code is not the same
    }

    /**
     * Test of toString method, of class Course.
     */
    @Test
    public void testToString() {
        Course instance = new Course("EECS2030", "Advanced Object Oriented Programming");
        String expResult = String.format("Course [%s - %s]", "EECS2030", "Advanced Object Oriented Programming");
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
