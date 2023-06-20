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

@Table("teacher")
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Teacher {
    @Id
    private Long id;
    private String name;
    private String teacherType;

    @Transient
    private List<Course> courses;

    @CreatedDate
    private LocalDateTime created_date;

    @LastModifiedDate
    private LocalDateTime last_modified_date;
}
