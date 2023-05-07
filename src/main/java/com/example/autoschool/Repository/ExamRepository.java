package com.example.autoschool.Repository;

import com.example.autoschool.Entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Integer> {

    @Transactional
    @Query("SELECT u FROM Exam u WHERE u.category = :category")
    public List<Exam> getExamByCategory(@Param("category") String category);
}
