package io.demo.student_management.service;

import io.demo.student_management.exception.CourseNotFoundException;
import io.demo.student_management.model.Course;
import io.demo.student_management.repository.CourseRepository;
import io.demo.student_management.repository.StudentRepository;
import io.demo.student_management.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public Flux<Course> findAll() {
        return courseRepository.findAll();
    }

    public Mono<Course> create(Course course) {
        if (course.getId() != null) {
            return Mono.error(new IllegalArgumentException("When creating a course, the id must be null"));
        }
        return courseRepository.save(course);
    }

    public Mono<Course> update(Course course) {
        if (course.getId() == null) {
            return Mono.error(new IllegalArgumentException("When updating a course, the id must be provided"));
        }
        return verifyExistence(course.getId())
                .then(courseRepository.save(course));
    }

    public Mono<Course> findById(final Long id, final Boolean loadRelations) {
        final Mono<Course> courseMono = courseRepository.findById(id)
                .switchIfEmpty(Mono.error(new CourseNotFoundException(id)));

        return loadRelations ? courseMono.flatMap(this::loadRelations) : courseMono;
    }
    //Load the students of the course
    private Mono<Course> loadRelations(final Course course) {
        //Load the students
        Mono<Course> courseMono = Mono.just(course)
                .zipWith(studentRepository.getStudentsByCourseId(course.getId()).collectList())
                .map(result -> result.getT1().setStudents(result.getT2()));

        //Load the teacher
        courseMono = courseMono.zipWith(teacherRepository.findById(course.getTeacherId()))
                .map(result -> result.getT1().setTeacher(result.getT2()));

        return courseMono;
    }

    private Mono<Boolean> verifyExistence(final Long id) {
        return courseRepository.existsById(id).handle((exists, sink) -> {
            if (Boolean.FALSE.equals(exists)) {
                sink.error(new CourseNotFoundException(id));
            } else {
                sink.next(exists);
            }
        });
    }
}
