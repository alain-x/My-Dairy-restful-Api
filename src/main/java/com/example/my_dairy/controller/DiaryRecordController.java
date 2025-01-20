package com.example.my_dairy.controller;

import com.example.my_dairy.dto.DiaryRecordDto;
import com.example.my_dairy.service.DiaryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class DiaryRecordController {

    private final DiaryRecordService diaryRecordService;

    @Autowired
    public DiaryRecordController(DiaryRecordService diaryRecordService) {
        this.diaryRecordService = diaryRecordService;
    }

    @PostMapping("/create")
    public ResponseEntity<DiaryRecordDto> createRecord(
            @RequestParam Long userId, @RequestBody DiaryRecordDto diaryRecordDto) {
        return ResponseEntity.ok(diaryRecordService.createRecord(userId, diaryRecordDto));
    }

    @GetMapping("/getDiary")
    public ResponseEntity<List<DiaryRecordDto>> getRecordsByUserId(@RequestParam Long userId) {
        List<DiaryRecordDto> records = diaryRecordService.getRecords(userId);
        return ResponseEntity.ok(records);
    }
    @GetMapping("/getAllDiary")
    public ResponseEntity<List<DiaryRecordDto>> getAllRecords() {
        return ResponseEntity.ok(diaryRecordService.getAllRecords());
    }


    @PatchMapping("/{id}")
    public ResponseEntity<DiaryRecordDto> updateRecord(
            @PathVariable Long id, @RequestBody DiaryRecordDto diaryRecordDto) {
        return ResponseEntity.ok(diaryRecordService.updateRecord(id, diaryRecordDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id, @RequestParam Long userId) {
        diaryRecordService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}
