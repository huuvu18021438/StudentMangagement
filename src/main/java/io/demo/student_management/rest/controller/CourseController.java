package io.demo.student_management.rest.controller;

import io.demo.student_management.mapper.CourseMapper;
import io.demo.student_management.model.Course;
import io.demo.student_management.rest.reource.CourseResource;
import io.demo.student_management.rest.reource.NewOrUpdateCourseResource;
import io.demo.student_management.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @GetMapping
    public Flux<CourseResource> getAll() {
        return courseService.findAll().map(courseMapper::toResource);
    }

    @GetMapping("/{id}")
    public Mono<CourseResource> findById(@PathVariable final Long id) {
        return courseService.findById(id, true).map(courseMapper::toResource);
    }

    @PostMapping
    public Mono<Course> create(@RequestBody final NewOrUpdateCourseResource newOrUpdateCourseResource) {
        return courseService.create(courseMapper.toModel(newOrUpdateCourseResource));
    }

    @PutMapping("/{id}")
    public Mono<Course> update(@PathVariable final Long id, @RequestBody NewOrUpdateCourseResource newOrUpdateCourseResource) {
        return courseService.findById(id, true)
                .map(course -> courseMapper.update(newOrUpdateCourseResource, course))
                .flatMap(courseService::update);
    }
}
