/**
 * 
 */
package eecs2030.lab4;

import java.time.LocalDate;

/**
 * This class models the enrollment of a student in one of the offered courses.
 * 
 * @author EECS2030 Summer 2019
 *
 */
public class StudentEnrollement {
	private final Student student;
	private final CourseOffering courseOffering;
	private final LocalDate enrollDate;

	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @return the courseOffering
	 */
	public CourseOffering getCourseOffering() {
		return courseOffering;
	}

	/**
	 * @return the enrollDate
	 */
	public LocalDate getEnrollDate() {
		return enrollDate;
	}

	/**
	 * @param student        a reference to the student will be enrolled
	 * @param courseOffering a reference to the current course offering
	 * @param enrollDate     the date on which the student enrolled
	 */
	public StudentEnrollement(Student student, CourseOffering courseOffering, LocalDate enrollDate) {
		this.student = student;
		this.courseOffering = courseOffering;
		this.enrollDate = enrollDate;
	}

}
