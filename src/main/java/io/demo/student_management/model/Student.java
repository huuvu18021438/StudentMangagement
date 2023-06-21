package io.demo.student_management.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Table("student")
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Student {

    @Id
    private Long id;

    private String name;

    private LocalDate birthday;

    private String phone;

    private String email;

    private Long addressId;

    @Transient
    private Address address;

    @Transient
    private List<Course> courses;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
