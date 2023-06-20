package io.demo.student_management.service;

import io.demo.student_management.exception.StudentNotFoundException;
import io.demo.student_management.mapper.CourseMapper;
import io.demo.student_management.model.Student;
import io.demo.student_management.model.StudentCourse;
import io.demo.student_management.repository.AddressRepository;
import io.demo.student_management.repository.CourseRepository;
import io.demo.student_management.repository.StudentCourseRepository;
import io.demo.student_management.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentCourseRepository studentCourseRepository;
    private final AddressRepository addressRepository;
    private final CourseMapper courseMapper;

    public Flux<Student> findAll() {
        return studentRepository.findAll();
    }

    @Transactional
    public Mono<Student> create(Student student) {
        if (student.getId() != null) {
            return Mono.error(new IllegalArgumentException("When creating an student, the id must be null"));
        }

        return studentRepository.save(student)
                .flatMap(saveStudent ->
                        studentCourseRepository.saveAll(courseMapper
                                .toCoursesOfStudent(saveStudent.getId(), saveStudent.getCourses())).collectList()
                                .then(Mono.just(saveStudent)));
    }

    @Transactional
    public Mono<Student> update(Student student) {
        if (student.getId() == null) {
            return Mono.error(new IllegalArgumentException("When updating an student, the id and the version must be provided"));
        }

        return verifyExistence(student.getId())
                .then(studentCourseRepository.findAllByStudentId(student.getId()).collectList())
                .flatMap(currentStudentCourse -> {
                    final Collection<Long> existCourseId = courseMapper.extractCourseIdsFromStudentCourse(currentStudentCourse);
                    final Collection<Long> courseToSave = courseMapper.extractCourseIdsFromCourses(student.getCourses());

                    final Collection<StudentCourse> removeStudentCourse = currentStudentCourse.stream()
                            .filter(studentCourse -> !existCourseId.contains(studentCourse.getCourseId()))
                            .toList();

                    final Collection<StudentCourse> addStudentCourse = courseToSave.stream()
                            .filter(courseId -> !existCourseId.contains(courseId))
                            .map(courseId -> new StudentCourse(student.getId(), courseId))
                            .toList();

                    return studentCourseRepository.deleteAll(removeStudentCourse)
                            .then(studentCourseRepository.saveAll(addStudentCourse).collectList());

                })
                .then(studentRepository.save(student));
    }

    public Mono<Void> deleteById(final Long id) {
        return findById(id, false)
                .zipWith(studentCourseRepository.deleteAllByStudentId(id))
                .map(Tuple2::getT1)
                .flatMap(studentRepository::delete);
    }

    public Mono<Student> findById(final Long id, final Boolean loadRelations) {
        final Mono<Student> studentMono = studentRepository.findById(id)
                .switchIfEmpty(Mono.error(new StudentNotFoundException(id)));

        return loadRelations ? studentMono.flatMap(this::loadRelations) : studentMono;
    }

    private Mono<Student> loadRelations(final Student student) {
        //Load the address
        Mono<Student> studentMono = Mono.just(student)
                .zipWith(addressRepository.findById(student.getAddressId()))
                .map(result -> result.getT1().setAddress(result.getT2()));

        //Load the courses
        studentMono = studentMono.zipWith(courseRepository.getCoursesByStudentId(student.getId()).collectList())
                .map(result -> result.getT1().setCourses(result.getT2()));

        return studentMono;
    }

    private Mono<Boolean> verifyExistence(Long id) {
        return studentRepository.existsById(id).handle((exists, sink) -> {
            if (Boolean.FALSE.equals(exists)) {
                sink.error(new StudentNotFoundException(id));
            } else {
                sink.next(exists);
            }
        });
    }
}
