package io.demo.student_management.exception;

public class StudentNotFoundException extends NotFoundException{
    public StudentNotFoundException(Long id) {
        super(String.format("Student [%d] is not found", id));
    }
}
