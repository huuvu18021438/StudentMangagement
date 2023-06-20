package io.demo.student_management.mapper;

import io.demo.student_management.model.Teacher;
import io.demo.student_management.rest.reource.NewTeacherResource;
import io.demo.student_management.rest.reource.TeacherResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherResource toResource(Teacher teacher);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Teacher toModel(NewTeacherResource newTeacherResource);
}
