package io.demo.student_management.rest.reource;

import io.demo.student_management.model.Course;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class StudentResource {
    private Long id;

    private String name;

    private Date birthday;

    private String phone;

    private String email;

    private AddressResource address;

    private List<Course> courses;

    private LocalDateTime created_date;

    private LocalDateTime last_modified_date;
}
