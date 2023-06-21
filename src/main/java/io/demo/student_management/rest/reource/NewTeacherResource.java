package io.demo.student_management.rest.reource;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NewTeacherResource {
    private String name;

    private String teacherType;
}
