package org.app.srms.services;

import org.app.srms.configuration.StudentMapper;
import org.app.srms.dto.StudentDto;
import org.app.srms.exceptions.DuplicateResourceException;
import org.app.srms.exceptions.ResourceNotFoundException;
import org.app.srms.model.Student;
import org.app.srms.repositories.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentSerivceImp implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final PasswordEncoder passwordEncoder;

    public StudentSerivceImp(StudentRepository studentRepository,
                             StudentMapper studentMapper,
                             PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<StudentDto> getAllStudents(String name, int page, int size, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Student> studentPage;

        if (name != null && !name.isBlank()) {
            studentPage = studentRepository.findByNameContainingIgnoreCase(name, pageable);
        } else {
            studentPage = studentRepository.findAll(pageable);
        }

        return studentPage.map(studentMapper::mapToDto);
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return studentMapper.mapToDto(student);
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {

        // Duplicate checks before hitting DB constraints
        if (studentRepository.existsByEmail(studentDto.getEmail())) {
            throw new DuplicateResourceException("Email already in use: " + studentDto.getEmail());
        }
        if (studentRepository.existsByUserName(studentDto.getUserName())) {
            throw new DuplicateResourceException("Username already taken: " + studentDto.getUserName());
        }

        Student student = studentMapper.mapToEntity(studentDto);

        // Always hash password — never store plain text
        student.setPassword(passwordEncoder.encode(studentDto.getPassword()));

        return studentMapper.mapToDto(studentRepository.save(student));
    }

    @Override
    public StudentDto updateStudentById(StudentDto studentDto, Long id) {

        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        // Check if new email is already used by a DIFFERENT student
        if (!existing.getEmail().equalsIgnoreCase(studentDto.getEmail())
                && studentRepository.existsByEmailAndIdNot(studentDto.getEmail(), id)) {
            throw new DuplicateResourceException("Email already in use: " + studentDto.getEmail());
        }

        // Check if new username is already used by a DIFFERENT student
        if (!existing.getUserName().equalsIgnoreCase(studentDto.getUserName())
                && studentRepository.existsByUserNameAndIdNot(studentDto.getUserName(), id)) {
            throw new DuplicateResourceException("Username already taken: " + studentDto.getUserName());
        }

        existing.setName(studentDto.getName());
        existing.setEmail(studentDto.getEmail());
        existing.setUserName(studentDto.getUserName());
        existing.setAge(studentDto.getAge());

        // Password update: only change it if a new one was provided
        if (studentDto.getPassword() != null && !studentDto.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(studentDto.getPassword()));
        }
        // if password field is blank/null in request, existing password stays unchanged

        return studentMapper.mapToDto(studentRepository.save(existing));
    }

    @Override
    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
}