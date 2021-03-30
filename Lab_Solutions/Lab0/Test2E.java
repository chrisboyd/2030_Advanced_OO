package eecs2030.test2;

import java.util.ArrayList;
import java.util.List;

/**
 * Test 2 version E.
 * 
 * @author EECS2030E Fall 2016
 *
 */
public class Test2E {
    /**
     * The maximum number of digits in a Java int.
     */
    public static final int MAX_DIGITS = 10;

    
    private Test2E() {
        // empty by design
    }
    
    /**
     * Computes the average value of three numbers.
     * 
     * @param a
     *            a number
     * @param b
     *            a number
     * @param c
     *            a number
     * @return the average of a, b, and c
     */
    public static double avg(int a, int b, int c) {
        return (0.0 + a + b + c) / 3.0;
    }

    /**
     * Given a list containing exactly 2 integers, swaps the positions of the
     * integers in the list. For example, given a list
     * 
     * <p>
     * <code>[-5, 9]</code>
     * 
     * <p>
     * <code>swap2</code> modifies the list so that it becomes
     * 
     * <p>
     * <code>[9, -5]</code>
     * 
     * @pre t is not null
     * 
     * @param t
     *            a list containing exactly 2 integers
     * @throws IllegalArgumentException
     *             if the list does not contain exactly 2 integers
     */
    public static void swap2(List<Integer> t) {
        if (t.size() != 2) {
            throw new IllegalArgumentException("list size != 2");
        }
        int t0 = t.get(0);
        int t1 = t.get(1);
        t.set(0, t1);
        t.set(1, t0);
    }

    /**
     * Returns a new list containing all of the values in the given list
     * <code>t</code> greater than <code>max</code>. An empty list is returned
     * if no value in <code>t</code> is greater than <code>max</code>.
     * The list <code>t</code> is not changed by this method.
     * For example, if <code>max == 5</code> then:
     * 
     * <pre>
     * t                    Test2E.allGreaterThan(t, max)
     * --------------------------------------------------
     * []                   []
     * [4]                  []
     * [9]                  [9]
     * [4, 5, 6, 7, 8]      [6, 7, 8]
     * </pre>
     * 
     * @pre t is not null
     * @param t
     *            a list of values
     * @param max
     *            all values in the returned list will be greater than
     *            <code>max</code>
     * @return a new list containing all of the values in <code>t</code> that
     *         are greater than <code>max</code>
     */
    public static List<Integer> allGreaterThan(List<Integer> t, int max) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i : t) {
            if (i > max) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * Given a list <code>t</code> whose elements are single digits, returns
     * the int value formed by joining the digits. 
     * The list <code>t</code> is not changed by this method.
     * For example, here are
     * some lists and their corresponding int values:
     * 
     * <pre>
     * []  (the empty list)          0
     * [4]                           4
     * [5, 2]                        52
     * [8, 7, 3]                     873
     * [-1, 0, 0, 0]                -1000
     * </pre>
     * 
     * <p>
     * If joining the digits of the list produces a positive value greater
     * than <code>Integer.MAX_VALUE</code> then <code>Integer.MAX_VALUE</code>
     * is returned.
     * 
     * <p>
     * If joining the digits of the list produces a negative value less
     * than <code>Integer.MIN_VALUE</code> then <code>Integer.MIN_VALUE</code>
     * is returned.
     * 
     * @pre1 t is not null
     * 
     * @pre2 the elements of t are integers consisting of
     *      exactly one digit 
     *      
     * @pre3 the first element of t may be negative or
     *      positive, but not zero
     *      
     * @pre4 all elements except the first are positive or zero
     *      
     * @param t
     *            a list of digits
     * @return the int value corresponding to the digits in t
     */
    public static int toInt(List<Integer> t) {

        long result = 0;
        int nDigits = t.size();
        if (nDigits == 0) {
            // do nothing
        } else {
            int firstDigit = t.get(0);
            if (nDigits > MAX_DIGITS && firstDigit > 0) {
                result = Integer.MAX_VALUE;
            }
            else if (nDigits > MAX_DIGITS && firstDigit < 0) {
                result = Integer.MIN_VALUE;
            }
            else {
                result = Math.abs(firstDigit);
                for (int i = 1; i < nDigits; i++) {
                    result = 10 * result + t.get(i);
                }
                if (firstDigit < 0) {
                    result = Math.max(Integer.MIN_VALUE, -result);
                } else {
                    result = Math.min(Integer.MAX_VALUE, result);
                }
            }
        }
        return (int) result;
    }

    
    public static void main(String[] args) {
        // avg
        int a = 1;
        int b = 2;
        int c = 3;
        System.out.println(
                String.format("average of %d, %d, and %d : ", a, b, c) + 
                Test2E.avg(a, b, c));
        
        // swap2
        List<Integer> t = new ArrayList<Integer>();
        t.add(3);
        t.add(5);
        String s = t.toString();
        Test2E.swap2(t);
        System.out.println(
                String.format("swap2(%s) : %s", s, t.toString()));
        
        // allGreaterThan
        t.clear();
        t.add(4);
        t.add(5);
        t.add(6);
        t.add(7);
        t.add(8);
        System.out.println(
                String.format("allGreaterThan(%s) : %s", 
                        t.toString(), Test2E.allGreaterThan(t, 5)));
        
        // toInt
        t.clear();
        t.add(1);
        t.add(2);
        t.add(3);
        System.out.println(
                String.format("toInt(%s) : %d", 
                        t.toString(), Test2E.toInt(t)));
    }
    
}
