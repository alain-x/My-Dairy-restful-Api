package com.example.my_dairy.impliment;

import com.example.my_dairy.dto.DiaryRecordDto;
import com.example.my_dairy.entities.DiaryRecord;
import com.example.my_dairy.repository.DiaryRecordRepo;
import com.example.my_dairy.service.DiaryRecordService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryRecordImpl implements DiaryRecordService {

    private final DiaryRecordRepo recordRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public DiaryRecordImpl(DiaryRecordRepo recordRepo, ModelMapper modelMapper) {
        this.recordRepo = recordRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public DiaryRecordDto createRecord(Long userId, DiaryRecordDto recordDto) {
        DiaryRecord recordEntity = modelMapper.map(recordDto, DiaryRecord.class);
        recordEntity.setUserById(userId);
        recordEntity.setCreatedOn(LocalDateTime.now());
        DiaryRecord savedRecord = recordRepo.save(recordEntity);
        return modelMapper.map(savedRecord, DiaryRecordDto.class);
    }

    @Override
    public List<DiaryRecordDto> getAllRecords() {
        return recordRepo.findAll().stream()
                .map(record -> modelMapper.map(record, DiaryRecordDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DiaryRecordDto> getRecords(Long userId) {
        // Check if any record exists for the given userId
        if (!recordRepo.existsByUserId(userId)) {
            // Throw an exception with HTTP 404 status
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + userId + " not found.");
        }

        // Fetch and map records
        return recordRepo.findByUserIdOrderByCreatedOnDesc(userId)
                .stream()
                .map(record -> modelMapper.map(record, DiaryRecordDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public DiaryRecordDto updateRecord(Long recordId, DiaryRecordDto recordDto) {
        DiaryRecord existingRecord = recordRepo.findById(recordId).orElse(null);
        if (existingRecord == null) {
            return null;
        }
        existingRecord.setTitle(recordDto.getTitle());
        existingRecord.setDescription(recordDto.getDescription());
        existingRecord.setCreatedOn(LocalDateTime.now());
        DiaryRecord updatedRecord = recordRepo.save(existingRecord);
        return modelMapper.map(updatedRecord, DiaryRecordDto.class);
    }

    @Override
    public void deleteRecord(Long recordId) {
        recordRepo.deleteById(recordId);
    }
}
