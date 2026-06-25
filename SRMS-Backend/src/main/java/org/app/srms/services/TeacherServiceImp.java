package org.app.srms.services;

import org.app.srms.configuration.TeacherMapper;
import org.app.srms.dto.TeacherDto;
import org.app.srms.exceptions.ResourceNotFoundException;
import org.app.srms.model.Teacher;
import org.app.srms.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImp implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;


    public TeacherServiceImp(TeacherRepository teacherRepository,TeacherMapper teacherMapper){
        this.teacherRepository=teacherRepository;
        this.teacherMapper=teacherMapper;
    }

    @Override
    public List<TeacherDto> getAllTeachers(){
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::mapToDto)
                .toList();
    }
    @Override
    public TeacherDto getTeacherById(Long id){
        Teacher teacher= teacherRepository.findById( id).orElseThrow(()->new ResourceNotFoundException("Teacher Not Found" +id));
        return teacherMapper.mapToDto(teacher);
    }
    @Override

    public TeacherDto saveTeacher(TeacherDto teacherDto){
        Teacher teacher = teacherMapper.mapToEntity(teacherDto);
       return teacherMapper.mapToDto(teacherRepository.save(teacher));
    }

    @Override
    public TeacherDto updateTeacherById(TeacherDto teacherDto,Long id){
        Teacher old = teacherRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Teacher doesnt exist with id "+id));

        if (!old.getEmail().equals(teacherDto.getEmail())
                && teacherRepository.existsByEmail(teacherDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        old.setUserName(teacherDto.getUserName());
        old.setEmail(teacherDto.getEmail());
        old.setPassword(teacherDto.getPassword());



        Teacher updatedTeacher = teacherRepository.save(old);

        return teacherMapper.mapToDto(updatedTeacher);

    }

    @Override
    public void deleteTeacher(Long id){
        teacherRepository.delete(teacherRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("there should be teacher with id to be deleted"+id)));
    }

}
