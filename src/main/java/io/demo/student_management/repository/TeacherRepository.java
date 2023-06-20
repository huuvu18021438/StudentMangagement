package io.demo.student_management.repository;

import io.demo.student_management.model.Teacher;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends R2dbcRepository<Teacher, Long> {
}
