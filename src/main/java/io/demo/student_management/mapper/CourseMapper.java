package io.demo.student_management.mapper;

import io.demo.student_management.model.Course;
import io.demo.student_management.model.StudentCourse;
import io.demo.student_management.rest.reource.CourseResource;
import io.demo.student_management.rest.reource.NewOrUpdateCourseResource;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, TeacherMapper.class})
public abstract class CourseMapper {
    @Autowired
    private StudentMapper studentMapper;

    public abstract CourseResource toResource(Course course);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    public abstract Course toModel(NewOrUpdateCourseResource newCourseResource);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    public abstract Course update(NewOrUpdateCourseResource UpdateCourseResource, @MappingTarget Course course);

    public List<Course> toCourses(Collection<Long> courseIds) {
        if (courseIds == null) {
            return new ArrayList<>();
        }

        return courseIds.stream()
                .map(courseId -> new Course().setId(courseId))
                .collect(Collectors.toList());
    }

    public Collection<Long> extractCourseIdsFromCourses(Collection<Course> courses) {
        if (courses == null) {
            return new LinkedHashSet<>();
        }

        return courses.stream().map(Course::getId).collect(Collectors.toSet());
    }

    public Collection<Long> extractCourseIdsFromStudentCourse(Collection<StudentCourse> studentCourses) {
        if (studentCourses == null) {
            return new LinkedHashSet<>();
        }

        return studentCourses.stream().map(StudentCourse::getCourseId).collect(Collectors.toSet());
    }

    public Collection<StudentCourse> toCoursesOfStudent(Long studentId, Collection<Course> courses) {
        if (courses == null) {
            return new LinkedHashSet<>();
        }

        return courses.stream().map(courseId -> new StudentCourse(studentId, courseId.getId()))
                .collect(Collectors.toSet());
    }
}
