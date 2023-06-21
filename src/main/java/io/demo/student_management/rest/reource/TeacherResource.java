package io.demo.student_management.rest.reource;

import io.demo.student_management.model.Course;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class TeacherResource {
    private Long id;

    private String name;

    private String teacherType;

    private List<Course> courses;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
