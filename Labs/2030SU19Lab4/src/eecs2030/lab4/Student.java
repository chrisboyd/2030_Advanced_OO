package eecs2030.lab4;

import java.time.LocalDate;
import java.util.HashSet;

/**
 * This class models a student.
 * 
 * @author EECS2030 Summer 2019
 *
 */
public class Student implements Comparable<Student> {

	private final String id;
	private final String name;
	private final LocalDate joinDate;
	private String email;
	private final HashSet<String> completedCourses;

	/**
	 * @param id       the id to set
	 * @param name     the name to set
	 * @param joinDate the joinDate to set
	 * @param email    the email to set
	 */
	public Student(String id, String name, LocalDate joinDate, String email) {
		this.id = id;
		this.name = name;
		this.joinDate = joinDate;
		this.email = email;
		this.completedCourses = new HashSet<String>();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the joinDate
	 */
	public LocalDate getJoinDate() {
		return joinDate;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the set of completed courses
	 */
	public HashSet<String> getCompletedCourses() {
		return completedCourses;
	}

	/**
	 * Add the courseCode to the set of completed courses
	 * 
	 * @param courseCode the code of the completed course
	 */
	public void completeCourse(String courseCode) {
		completedCourses.add(courseCode);

	}

	/**
	 * Compare this Student object and other Student object.
	 * 
	 * A Student 's1' is "smaller" than another 's2' if s1's join date is before
	 * s2's. If both have the same join date, then one with a smaller id in the
	 * alphabetical order is considered as "smaller".
	 * 
	 * When being sorted in an increasing order (using Arrays.sort), the smaller
	 * Student object appears earlier than the larger one.
	 * 
	 * @param other the other Student to compare this Student to
	 * @return Positive value if this is larger than other, negative if this is
	 *         smaller than other, zero otherwise.
	 */
	@Override
	public int compareTo(Student other) {
		if (this.joinDate.isEqual(other.getJoinDate()))
			return this.getName().compareTo(other.getName());
		else
			return this.getJoinDate().compareTo(other.getJoinDate());
	}

	/**
	 * Two Student objects are equal if their {@code id}s are equal.
	 * 
	 * @return Whether this Student object equals 'obj'
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		return this.getId().equals(((Student) obj).getId());
	}

	/**
	 * returns a hash code based on the Student's id only
	 */
	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return String.format("Student [%s (%s)]", name, id);
	}

}
