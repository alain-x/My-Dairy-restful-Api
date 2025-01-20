package com.example.my_dairy.service;

import com.example.my_dairy.dto.DiaryRecordDto;

import java.util.List;

public interface DiaryRecordService {
    DiaryRecordDto createRecord(Long userId, DiaryRecordDto recordDto);

    List<DiaryRecordDto> getRecords(Long userId);

    DiaryRecordDto updateRecord(Long recordId, DiaryRecordDto recordDto);

    void deleteRecord(Long recordId);

    List<DiaryRecordDto> getAllRecords();
}
