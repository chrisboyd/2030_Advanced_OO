package eecs2030.lab4;

import java.time.LocalDate;
import java.util.HashSet;

/**
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
	 * Add a course to the set of completed courses
	 * 
	 * @param courseCode
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
		int d = this.getJoinDate().compareTo(other.getJoinDate());
		if (d == 0) {
			return this.getId().compareTo(other.getId());
		} else {
			return d;
		}
	}

	/**
	 * Two Student objects are equal if their {@code id}s are equal.
	 * 
	 * @return Whether this Student object equals 'obj'
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * returns a hash code based on the Student's id only
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return String.format("Student [%s (%s)]", name, id);
	}

}
