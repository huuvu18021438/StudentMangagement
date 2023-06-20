package io.demo.student_management.rest.reource;

import lombok.Data;

@Data
public class NewOrUpdateCourseResource {
    private String name;
    private int credits;
    private String description;
    private Long teacherId;
}
