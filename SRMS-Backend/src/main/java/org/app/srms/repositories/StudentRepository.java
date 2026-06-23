package org.app.srms.repositories;

import org.app.srms.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {


    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);


    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByUserNameAndIdNot(String userName, Long id);

    Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);
}