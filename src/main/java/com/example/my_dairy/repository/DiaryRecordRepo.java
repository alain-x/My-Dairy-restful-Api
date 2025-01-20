package com.example.my_dairy.repository;

import com.example.my_dairy.entities.DiaryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRecordRepo extends JpaRepository<DiaryRecord, Long> {
    List<DiaryRecord> findByUserIdOrderByCreatedOnDesc(Long userId);
    boolean existsByUserId(Long userId);
}

