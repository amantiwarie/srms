package org.app.srms.configuration;

import org.app.srms.dto.TeacherDto;
import org.app.srms.model.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {

    public TeacherDto mapToDto(Teacher teacher){
        TeacherDto teacherDto= new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setUserName(teacher.getUserName());
        teacherDto.setPassword(teacher.getPassword());
        teacherDto.setEmail(teacher.getEmail());

        return teacherDto;
    }

    public Teacher mapToEntity(TeacherDto teacherDto){
        Teacher teacher = new Teacher();
        teacher.setId(teacher.getId());
        teacher.setPassword(teacherDto.getPassword());
        teacher.setUserName(teacherDto.getUserName());
        teacher.setEmail(teacherDto.getEmail());
        return teacher;
    }
}
