package io.demo.student_management.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table ("address")
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Address {
    @Id
    private Long id;
    private String country;
    private String city;
    private String street;

    @CreatedDate
    private LocalDateTime created_date;

    @LastModifiedDate
    private LocalDateTime last_modified_date;
}
