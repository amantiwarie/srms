package org.app.srms.services;

import org.app.srms.dto.StudentDto;
import org.springframework.data.domain.Page;

public interface StudentService {


    Page<StudentDto> getAllStudents(String name, int page, int size, String sortBy, String sortDir);

    StudentDto getStudentById(Long id);

    StudentDto createStudent(StudentDto studentDto);

    StudentDto updateStudentById(StudentDto studentDto, Long id);

    void deleteStudentById(Long id);
}