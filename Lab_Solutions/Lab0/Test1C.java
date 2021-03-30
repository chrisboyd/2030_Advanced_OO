package eecs2030.test1;

/**
 * Test 1 for EECS 2030E Lab 1.
 * 
 * @author EECS2030E
 *
 */
public class Test1C {

    /**
     * The value 2&pi; 
     */
    public static final double TWO_PI = 2.0 * Math.PI;
    
    private Test1C() {
        // empty by design
    }
    
    /**
     * Returns the circumference of a circle having the given
     * radius. It is assumed that the radius is greater than
     * or equal to zero.
     * 
     * <p>
     * The circumference of a circle having radius <i>r</i>
     * is equal to 2&pi;<i>r</i>
     * 
     * @param radius the radius of the circle
     * @return the circumference of the circle with the given radius
     */
    public static double circumference(double radius) {
        return Test1C.TWO_PI * radius;
    }
    
}
