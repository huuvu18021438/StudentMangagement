package io.demo.student_management.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Table("course")
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Course {
    @Id
    private Long id;

    private String name;

    private int credits;

    private String description;

    private Long teacherId;

    @Transient
    private Teacher teacher;

    @Transient
    private List<Student> students;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
