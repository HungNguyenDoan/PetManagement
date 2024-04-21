package com.project.petmanagement.petmanagement.services;

import com.project.petmanagement.petmanagement.advices.DataNotFoundException;
import com.project.petmanagement.petmanagement.models.entity.DailyActivity;
import com.project.petmanagement.petmanagement.models.entity.DailyActivityLog;
import com.project.petmanagement.petmanagement.payloads.requests.DailyActivityLogRequest;
import com.project.petmanagement.petmanagement.repositories.DailyActivityLogRepository;
import com.project.petmanagement.petmanagement.repositories.DailyActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyActivityService {
    private final DailyActivityRepository dailyActivityRepository;

    public List<DailyActivity> getAllDailyActivities  () {
        return dailyActivityRepository.findAll();
    }

    public DailyActivity getDailyActivityById(Long dailyActivityId) throws Exception {
        return dailyActivityRepository.findById(dailyActivityId).orElseThrow(() -> new DataNotFoundException("Can not find Daily Activity  with ID: " + dailyActivityId + ", or pet was deleted"));
    }
}
