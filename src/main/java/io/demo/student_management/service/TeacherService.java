package io.demo.student_management.service;

import io.demo.student_management.model.Teacher;
import io.demo.student_management.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public Flux<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Mono<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    @Transactional
    public Mono<Teacher> create(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
}
