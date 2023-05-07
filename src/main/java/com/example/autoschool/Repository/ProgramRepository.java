package com.example.autoschool.Repository;

import com.example.autoschool.Entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProgramRepository extends JpaRepository<Program, String> {


    @Transactional
    @Query("SELECT u FROM Program u WHERE u.UserEmail = :UserEmail")
    public Iterable<Program> getAllByUserEmail(@Param("UserEmail") String UserEmail);


    @Query("SELECT u.program FROM Program u WHERE u.UserEmail = :UserEmail")
     String getProgramProgramByUserEmail(@Param("UserEmail") String email);

    @Modifying
    @Transactional
    @Query("delete from Program where UserEmail = :UserEmail")
    void deleteUsersByUserEmail(@Param("UserEmail") String UserEmail);
}
