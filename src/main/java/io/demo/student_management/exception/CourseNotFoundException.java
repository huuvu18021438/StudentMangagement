package io.demo.student_management.exception;

public class CourseNotFoundException extends NotFoundException {
    public CourseNotFoundException(Long id) {
        super(String.format("Course [%d] is not found", id));
    }
}
