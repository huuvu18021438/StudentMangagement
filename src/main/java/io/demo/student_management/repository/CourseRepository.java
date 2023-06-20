package io.demo.student_management.repository;

import io.demo.student_management.model.Course;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CourseRepository extends R2dbcRepository<Course, Long> {
    @Query("SELECT c.* FROM course c JOIN student_course sc ON c.id = sc.courseId WHERE sc.id = :studentId")
    Flux<Course> getCoursesByStudentId(Long studentId);
}
