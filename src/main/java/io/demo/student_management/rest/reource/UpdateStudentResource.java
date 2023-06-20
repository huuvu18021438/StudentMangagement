package io.demo.student_management.rest.reource;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class UpdateStudentResource {
    private String name;
    private Date birthday;
    private String phone;
    private String email;
    private Long addressId;
    private List<Long> courses;
}
