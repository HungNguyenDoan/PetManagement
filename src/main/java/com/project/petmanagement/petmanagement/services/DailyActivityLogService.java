package com.project.petmanagement.petmanagement.services;

import com.project.petmanagement.petmanagement.advices.DataNotFoundException;
import com.project.petmanagement.petmanagement.models.entity.Breed;
import com.project.petmanagement.petmanagement.models.entity.DailyActivity;
import com.project.petmanagement.petmanagement.models.entity.DailyActivityLog;
import com.project.petmanagement.petmanagement.models.entity.Pet;
import com.project.petmanagement.petmanagement.payloads.requests.DailyActivityLogRequest;
import com.project.petmanagement.petmanagement.payloads.requests.PetRequest;
import com.project.petmanagement.petmanagement.repositories.DailyActivityLogRepository;
import com.project.petmanagement.petmanagement.repositories.DailyActivityRepository;
import com.project.petmanagement.petmanagement.repositories.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyActivityLogService {
    private final DailyActivityLogRepository dailyActivityLogRepository;
    private final PetRepository petRepository;
    private final DailyActivityRepository dailyActivityRepository;

    public List<DailyActivityLog> getDailyActivityLogs() {
        return dailyActivityLogRepository.findAll();
    }

    public DailyActivityLog getDailyActivityLogById(Long dailyActivityLogId) throws Exception {
        return dailyActivityLogRepository.findById(dailyActivityLogId).orElseThrow(() -> new DataNotFoundException("Can not find daily activity log  with ID: " + dailyActivityLogId + ", or daily activity log was deleted"));
    }
    public List<DailyActivityLog> getDailyActivityLogsByPet(Long petId) throws Exception {
        try {
            return dailyActivityLogRepository.findByPetId(petId);
        } catch (Exception e) {
            throw new Exception("Can not find daily activity log  with pet ID: " + petId + ", or daily activity log was deleted" );
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public DailyActivityLog addDailyActivityLog(DailyActivityLogRequest request) throws Exception {
        Pet pet = petRepository.findById(request.getPetId()).orElseThrow(() -> new DataNotFoundException("Can not find daily activity log with ID: " + request.getPetId()));
        DailyActivity dailyActivity = dailyActivityRepository.findById(request.getDailyActivityId()).orElseThrow(() -> new DataNotFoundException("Can not find daily activity log with ID: " + request.getDailyActivityId()));
        DailyActivityLog dailyActivityLog = DailyActivityLog.builder()
                .date(request.getDate())
                .time(request.getTime())
                .image(request.getImage())
                .title(request.getTitle())
                .note(request.getNote())
                .pet(pet)
                .dailyActivity(dailyActivity)
                .build();
        return dailyActivityLogRepository.save(dailyActivityLog);
    }

    @Transactional(rollbackFor = {Exception.class})
    public DailyActivityLog updateDailyActivityLog(DailyActivityLogRequest request) throws Exception {
        DailyActivityLog dailyActivityLog = dailyActivityLogRepository.findById(request.getId()).orElseThrow(() -> new DataNotFoundException("Can not find daily activity log with ID: " + request.getId()));
        if(dailyActivityLog != null) {
            Pet pet = petRepository.findById(request.getPetId()).orElseThrow(() -> new DataNotFoundException("Can not find daily activity log with ID: " + request.getPetId()));
            DailyActivity dailyActivity = dailyActivityRepository.findById(request.getDailyActivityId()).orElseThrow(() -> new DataNotFoundException("Can not find daily activity log with ID: " + request.getDailyActivityId()));
            dailyActivityLog.setDate(request.getDate());
            dailyActivityLog.setTime(request.getTime());
            dailyActivityLog.setTitle(request.getTitle());
            dailyActivityLog.setImage(request.getImage());
            dailyActivityLog.setNote(request.getNote());
            dailyActivityLog.setPet(pet);
            dailyActivityLog.setDailyActivity(dailyActivity);
            return dailyActivityLogRepository.save(dailyActivityLog);
        }
        return null;
    }

    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<String> deleteDailyActivityLog( Long dailyActivityLogId) {
        try {
            dailyActivityLogRepository.deleteById(dailyActivityLogId);
            return ResponseEntity.ok("Record deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Can not find daily activity log with ID: " + dailyActivityLogId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the record");
        }
    }

}
