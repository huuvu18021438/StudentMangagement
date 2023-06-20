package io.demo.student_management.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("student_course")
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class StudentCourse {

    public StudentCourse(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @Id
    private Long id;

    private Long studentId;

    private Long courseId;
}
