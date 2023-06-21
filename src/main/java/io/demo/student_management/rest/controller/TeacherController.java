package io.demo.student_management.rest.controller;

import io.demo.student_management.mapper.TeacherMapper;
import io.demo.student_management.model.Teacher;
import io.demo.student_management.rest.reource.NewTeacherResource;
import io.demo.student_management.rest.reource.TeacherResource;
import io.demo.student_management.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;

    @GetMapping
    public Flux<TeacherResource> getAll() {
        return teacherService.findAll().map(teacherMapper::toResource);
    }

    @GetMapping("/{id}")
    public Mono<TeacherResource> findById(@PathVariable final Long id) {
        return teacherService.findById(id).map(teacherMapper::toResource);
    }

    @PostMapping
    public Mono<Teacher> create(NewTeacherResource newTeacherResource) {
        return teacherService.create(teacherMapper.toModel(newTeacherResource));
    }
}
