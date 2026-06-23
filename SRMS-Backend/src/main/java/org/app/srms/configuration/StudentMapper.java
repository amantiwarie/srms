package org.app.srms.configuration;

import org.app.srms.dto.StudentDto;
import org.app.srms.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {


    public StudentDto mapToDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setUserName(student.getUserName());
        dto.setEmail(student.getEmail());
        dto.setAge(student.getAge());

        return dto;
    }


    public Student mapToEntity(StudentDto dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setUserName(dto.getUserName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());
        student.setPassword(dto.getPassword());
        return student;
    }
}