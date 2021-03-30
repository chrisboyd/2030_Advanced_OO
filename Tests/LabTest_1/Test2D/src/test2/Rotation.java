package test2;

/**
 * A class that represents rotations in the 2D plane. A rotation
 * is defined by an angle of rotation and the units used to
 * represent the rotation.
 *
 */
public class Rotation {
	
	/**
	 * String for units in degrees.
	 */
	public static final String DEGREES = "degrees";
	
	/**
	 * String for units in radians.
	 */
	public static final String RADIANS = "radians";

	/**
	 * The angle of the rotation.
	 */
	private double angle;
	
    /**
     * The units of the angle.
     */
    private String units;
    
    public Rotation() {
    	this(0, "radians");
    }
    
    public Rotation(double angle, String units) {
    	switch (units) {
		case DEGREES:
			break;
		case RADIANS:
			break;
		default:
			throw new IllegalArgumentException("Invalid unit");
		}
    	
    	this.angle = angle;
    	this.units = units;
    }  
}
