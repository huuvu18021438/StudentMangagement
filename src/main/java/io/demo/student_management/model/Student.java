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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
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
    private Date birthday;
    private String phone;
    private String email;
    private Long addressId;

    @Transient
    private Address address;

    @Transient
    private List<Course> courses;

    @CreatedDate
    private LocalDateTime created_date;

    @LastModifiedDate
    private LocalDateTime last_modified_date;
}
