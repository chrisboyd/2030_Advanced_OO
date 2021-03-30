package eecs2030.lab4;

import java.time.LocalDate;

/**
 * @author EECS2030 Summer 2019
 *
 */
public class CourseOffering {
	/**
	 * the maximum number of students allowed to enroll in this course offering
	 */
	private final int capacity;
	/**
	 * reference to the course object
	 */
	private final Course course;
	/**
	 * one character representing the term this course is offered in. Accepted
	 * values are 'F' for Fall term, 'W' for Winter term, and 'S' for Summer term.
	 */
	private final char term;
	/**
	 * one character representing the section of this course offering.
	 */
	private final char section;
	/**
	 * the year the course is offered in
	 */
	private final int year;
	/**
	 * the date after which enrollment in the course is not allowed
	 */
	private final LocalDate openUntil;

	/**
	 * Initialize the CourseOffering object with the values form the parameters.
	 * 
	 * @param course    a reference to the course
	 * @param capacity  max course capacity
	 * @param term      one of the letters {'F', 'W', 'S'}
	 * @param section   one character representing the section
	 * @param year      the year the course is offered in
	 * @param openUntil the date after which enrollment in the course is not allowed
	 * @throws IllegalArgumentException if the {@code course} is null or the
	 *                                  {@code term} is not one of the acceptable
	 *                                  values {'F', 'W', 'S'}
	 */
	public CourseOffering(Course course, int capacity, char term, char section, int year, LocalDate openUntil) {
		if (course == null)
			throw new IllegalArgumentException("Invalide course reference");
		if (term != 'F' && term != 'W' && term != 'S')
			throw new IllegalArgumentException("Invalide term code");
		this.course = course;
		this.capacity = capacity;
		this.term = term;
		this.section = section;
		this.year = year;
		this.openUntil = openUntil;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @return the term
	 */
	public char getTerm() {
		return term;
	}

	/**
	 * @return the section
	 */
	public char getSection() {
		return section;
	}

	/**
	 * @return the section
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @return the openUntil
	 */
	public LocalDate getOpenUntil() {
		return openUntil;
	}

	/**
	 * Returns a hash code based on the offered course's {@code course},
	 * {@code section}, {@code term} and {@code year}.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + section;
		result = prime * result + term;
		result = prime * result + year;
		return result;
	}

	/**
	 * Two CourseOffering objects are equal if they have equal {@code course},
	 * {@code section}, {@code term} and {@code year}.
	 * 
	 * @return Whether this CourseOffering object equals 'obj'
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseOffering other = (CourseOffering) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (section != other.section)
			return false;
		if (term != other.term)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("CourseOffering [%s%c %c%d]", course.getCode(), section, term, year);
	}
}
