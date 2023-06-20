package io.demo.student_management.rest.reource;

import io.demo.student_management.model.Student;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CourseResource {
    private Long id;
    private String name;
    private int credits;
    private String description;

    private TeacherResource teacher;

    private List<Student> students;

    private LocalDateTime created_date;

    private LocalDateTime last_modified_date;
}
