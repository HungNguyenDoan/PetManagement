package com.project.petmanagement.petmanagement.services;

import com.project.petmanagement.petmanagement.advices.DataNotFoundException;
import com.project.petmanagement.petmanagement.models.entity.OneTimeSchedule;
import com.project.petmanagement.petmanagement.models.entity.VaccinationNotification;
import com.project.petmanagement.petmanagement.payloads.requests.OneTimeScheduleRequest;
import com.project.petmanagement.petmanagement.repositories.OneTimeScheduleRepository;
import com.project.petmanagement.petmanagement.repositories.VaccinationNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OneTimeScheduleService {
    private final VaccinationNotificationRepository vaccinationNotificationRepository;
    private final OneTimeScheduleRepository oneTimeScheduleRepository;

    public List<OneTimeSchedule> getOneTimeScheduleListByVaccinationNotification(Long vaccinationNotificationId) throws DataNotFoundException {
        VaccinationNotification vaccinationNotification = vaccinationNotificationRepository.findById(vaccinationNotificationId).orElseThrow(() -> new DataNotFoundException("Can not find vaccination notification with ID: " + vaccinationNotificationId));
        return oneTimeScheduleRepository.findByVaccinationNotification(vaccinationNotification);
    }

    public List<OneTimeSchedule> addOneTimeScheduleList(List<OneTimeScheduleRequest> oneTimeScheduleRequestList, Long vaccinationNotificationId) throws DataNotFoundException {
        VaccinationNotification vaccinationNotification = vaccinationNotificationRepository.findById(vaccinationNotificationId).orElseThrow(() -> new DataNotFoundException("Can not find vaccination notification with ID: " + vaccinationNotificationId));
        List<OneTimeSchedule> oneTimeScheduleList = new ArrayList<>();
        for (OneTimeScheduleRequest oneTimeScheduleRequest : oneTimeScheduleRequestList) {
            oneTimeScheduleList.add(OneTimeSchedule.builder()
                    .vaccinationNotification(vaccinationNotification)
                    .date(oneTimeScheduleRequest.getDate())
                    .time(oneTimeScheduleRequest.getTime())
                    .vaccinationStatus(false)
                    .build());
        }
        return oneTimeScheduleRepository.saveAll(oneTimeScheduleList);
    }

    public List<OneTimeSchedule> updateOneTimeScheduleList(List<OneTimeScheduleRequest> oneTimeScheduleRequestList) throws DataNotFoundException {
        List<OneTimeSchedule> oneTimeScheduleList = new ArrayList<>();
        for (OneTimeScheduleRequest oneTimeScheduleRequest : oneTimeScheduleRequestList) {
            OneTimeSchedule existingOneTimeSchedule = oneTimeScheduleRepository.findById(oneTimeScheduleRequest.getId()).orElseThrow(() -> new DataNotFoundException("Can not find one time schedule with ID: " + oneTimeScheduleRequest.getId()));
            existingOneTimeSchedule.setDate(oneTimeScheduleRequest.getDate());
            existingOneTimeSchedule.setTime(oneTimeScheduleRequest.getTime());
            existingOneTimeSchedule.setVaccinationStatus(oneTimeScheduleRequest.getStatus());
            oneTimeScheduleList.add(oneTimeScheduleRepository.save(existingOneTimeSchedule));
        }
        return oneTimeScheduleList;
    }

    public void deleteOneTimeSchedule(Long oneTimeScheduleId) throws DataNotFoundException {
        OneTimeSchedule existingOneTimeSchedule = oneTimeScheduleRepository.findById(oneTimeScheduleId).orElseThrow(() -> new DataNotFoundException("Can not find one time schedule with ID: " + oneTimeScheduleId));
        if (existingOneTimeSchedule != null) {
            oneTimeScheduleRepository.deleteById(oneTimeScheduleId);
        }
    }
}
