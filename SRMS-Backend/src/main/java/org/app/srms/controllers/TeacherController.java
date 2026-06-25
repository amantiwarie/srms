package org.app.srms.controllers;


import jakarta.validation.Valid;
import org.app.srms.dto.TeacherDto;
import org.app.srms.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
    @RequestMapping("/api/teachers")
    public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());

    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }


    @PostMapping()
    public ResponseEntity<TeacherDto> saveTeacher(@Valid @RequestBody TeacherDto teacherDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(teacherService.saveTeacher(teacherDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable Long id,
                                                    @Valid @RequestBody TeacherDto teacherDto) {
        return ResponseEntity.ok(teacherService.updateTeacherById(teacherDto, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<TeacherDto> deleteTeacher(@PathVariable Long id){
        teacherService.deleteTeacher( id);
        return ResponseEntity.noContent().build();
    }

}
