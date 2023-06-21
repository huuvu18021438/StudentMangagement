package io.demo.student_management.rest.controller;

import io.demo.student_management.mapper.StudentMapper;
import io.demo.student_management.model.Student;
import io.demo.student_management.model.StudentCourse;
import io.demo.student_management.rest.reource.NewStudentResource;
import io.demo.student_management.rest.reource.StudentResource;
import io.demo.student_management.rest.reource.UpdateStudentResource;
import io.demo.student_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping
    public Flux<StudentResource> getAll() {
        return studentService.findAll().map(studentMapper::toResource);
    }

    @GetMapping("/{id}")
    public Mono<StudentResource> findById(@PathVariable final Long id) {
        return studentService.findById(id, true).map(studentMapper::toResource);
    }

    @PostMapping
    public Mono<Student> create(@RequestBody final NewStudentResource newStudentResource) {
        return studentService.create(studentMapper.toModel(newStudentResource));
    }

    @PutMapping("/{id}")
    public Mono<Student> update(@PathVariable final Long id, @RequestBody final UpdateStudentResource updateStudentResource) {
        return studentService.findById(id, true)
                .map(student -> studentMapper.update(updateStudentResource, student))
                .flatMap(studentService::update);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable final Long id) {
        return studentService.deleteById(id);
    }
}
