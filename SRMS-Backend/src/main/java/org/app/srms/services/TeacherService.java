package org.app.srms.services;


import org.app.srms.dto.TeacherDto;

import java.util.List;

public interface TeacherService {

    public List<TeacherDto> getAllTeachers();

    public TeacherDto getTeacherById(Long id);

    public TeacherDto saveTeacher(TeacherDto teacherDto);

    public  TeacherDto updateTeacherById(TeacherDto teacherDto,Long id);

    public void deleteTeacher(Long id);
}
