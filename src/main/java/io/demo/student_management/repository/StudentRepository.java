package io.demo.student_management.repository;

import io.demo.student_management.model.Student;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StudentRepository extends R2dbcRepository<Student, Long> {
    @Query("SELECT s.* FROM student s JOIN student_course sc ON s.id = sc.student_id WHERE sc.id = :courseId")
    Flux<Student> getStudentsByCourseId(Long courseId);
}
