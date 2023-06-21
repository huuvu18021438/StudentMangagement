package io.demo.student_management.mapper;

import io.demo.student_management.model.Student;
import io.demo.student_management.model.StudentCourse;
import io.demo.student_management.rest.reource.NewStudentResource;
import io.demo.student_management.rest.reource.StudentResource;
import io.demo.student_management.rest.reource.UpdateStudentResource;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {CourseMapper.class, AddressMapper.class})
public abstract class StudentMapper {
    @Autowired
    private CourseMapper courseMapper;

    public abstract StudentResource toResource(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    public abstract Student toModel(NewStudentResource newStudentResource);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    public abstract Student update(UpdateStudentResource updateStudentResource, @MappingTarget Student student);

    @AfterMapping
    public void afterMapping(UpdateStudentResource updateStudentResource, @MappingTarget Student student) {
        student.setCourses(courseMapper.toCourses(updateStudentResource.getCourses()));
    }

    public List<Student> toStudents(Collection<Long> studentIds) {
        if (studentIds == null) {
            return new ArrayList<>();
        }

        return studentIds.stream().map(studentId -> new Student().setId(studentId)).collect(Collectors.toList());
    }

    public Collection<Long> extractStudentIdsFromStudents(Collection<Student> students) {
        if (students == null) {
            return new LinkedHashSet<>();
        }

        return students.stream().map(Student::getId).collect(Collectors.toSet());
    }

    public Collection<Long> extractStudentIdsFromStudentCourse(Collection<StudentCourse> studentCourses) {
        if (studentCourses == null) {
            return new LinkedHashSet<>();
        }

        return studentCourses.stream().map(StudentCourse::getStudentId).collect(Collectors.toSet());
    }
}
