package io.demo.student_management.rest.reource;

import lombok.Data;

@Data
public class AddressResource {
    private Long id;
    private String country;
    private String city;
    private String street;
}
