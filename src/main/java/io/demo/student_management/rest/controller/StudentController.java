package io.demo.student_management.rest.controller;

import io.demo.student_management.model.Student;
import io.demo.student_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Slf4j
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public Flux<Student> getAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Student> findById(@PathVariable final Long id) {
        return studentService.findById(id, true);
    }

    @PostMapping
    public Mono<Student> create(@RequestBody final Student student) {
        return studentService.create(student);
    }

    @PutMapping("/{id}")
    public Mono<Student> update(@PathVariable final Long id, @RequestBody final Student student) {
        return studentService.update(student);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable final Long id) {
        return studentService.deleteById(id);
    }
}
