package com.primitive.savetown.Repository.UserPersonalInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTeacherRepository extends JpaRepository<UserTeacher, Long> {
    Optional<UserTeacher> findByLoginId(String loginId);
}
