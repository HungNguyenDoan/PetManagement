package com.project.petmanagement.petmanagement.services;

import com.project.petmanagement.petmanagement.advices.DataNotFoundException;
import com.project.petmanagement.petmanagement.models.entity.CareActivityNotification;
import com.project.petmanagement.petmanagement.models.entity.Pet;
import com.project.petmanagement.petmanagement.payloads.requests.CareActivityNotificationRequest;
import com.project.petmanagement.petmanagement.repositories.CareActivityNotificationRepository;
import com.project.petmanagement.petmanagement.repositories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CareActivityNotificationService {
    private final PetRepository petRepository;
    private final CareActivityNotificationRepository careActivityNotificationRepository;

    public List<CareActivityNotification> getAllCareActivityNotification(){
        return careActivityNotificationRepository.findAll();
    }

    public List<CareActivityNotification> getCareActivityNotificationByPet(Pet pet){
        return careActivityNotificationRepository.findByPet(pet);
    }

    public CareActivityNotification getCareActivityNotificationDetails(Long careActivityNotificationId) throws DataNotFoundException {
        return careActivityNotificationRepository.findById(careActivityNotificationId).orElseThrow(() -> new DataNotFoundException("Can not find care activity notification with ID: " + careActivityNotificationId));
    }

    @Transactional(rollbackFor = {Exception.class})
    public CareActivityNotification addCareActivityNotification(CareActivityNotificationRequest careActivityNotificationRequest) throws DataNotFoundException {
        Pet pet = petRepository.findById(careActivityNotificationRequest.getPetId()).orElseThrow(() -> new DataNotFoundException("Can not find pet with ID: " + careActivityNotificationRequest.getPetId()));
        CareActivityNotification careActivityNotification = CareActivityNotification.builder()
                .pet(pet)
                .title(careActivityNotificationRequest.getTitle())
                .note(careActivityNotificationRequest.getNote())
                .notificationStatus(true)
                .build();
        return careActivityNotificationRepository.save(careActivityNotification);
    }

    @Transactional(rollbackFor = {Exception.class})
    public CareActivityNotification updateCareActivityNotification(Long careActivityNotificationId, CareActivityNotificationRequest careActivityNotificationRequest) throws DataNotFoundException {
        CareActivityNotification existingCareActivityNotification = careActivityNotificationRepository.findById(careActivityNotificationId).orElseThrow(() -> new DataNotFoundException("Can not find activity notification with ID: " + careActivityNotificationId));
        existingCareActivityNotification.setTitle(careActivityNotificationRequest.getTitle());
        existingCareActivityNotification.setNote(careActivityNotificationRequest.getNote());
        existingCareActivityNotification.setNotificationStatus(careActivityNotificationRequest.getNotificationStatus());
        return careActivityNotificationRepository.save(existingCareActivityNotification);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void deleteCareActivityNotification(Long careActivityNotificationId) throws DataNotFoundException {
        CareActivityNotification existingCareActivityNotification = careActivityNotificationRepository.findById(careActivityNotificationId).orElseThrow(() -> new DataNotFoundException("Can not find activity notification with ID: " + careActivityNotificationId));
        if (existingCareActivityNotification != null) {
            careActivityNotificationRepository.deleteById(careActivityNotificationId);
        }
    }

}


