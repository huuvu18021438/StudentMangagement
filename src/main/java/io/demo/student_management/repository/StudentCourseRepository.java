package io.demo.student_management.repository;

import io.demo.student_management.model.StudentCourse;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface StudentCourseRepository extends R2dbcRepository<StudentCourse, Long> {
    Flux<StudentCourse> findAllByStudentId(Long studentId);

    Mono<StudentCourse> deleteAllByStudentId(Long studentId);

}
