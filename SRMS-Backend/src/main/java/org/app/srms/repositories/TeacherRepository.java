package org.app.srms.repositories;

import org.app.srms.model.Student;
import org.app.srms.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);


    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByUserNameAndIdNot(String userName, Long id);


}
