package com.project.petmanagement.petmanagement.services;

import com.project.petmanagement.petmanagement.advices.DataNotFoundException;
import com.project.petmanagement.petmanagement.models.entity.Pet;
import com.project.petmanagement.petmanagement.models.entity.VaccinationNotification;
import com.project.petmanagement.petmanagement.payloads.requests.VaccinationNotificationRequest;
import com.project.petmanagement.petmanagement.repositories.PetRepository;
import com.project.petmanagement.petmanagement.repositories.VaccinationNotificationRepository;
import com.project.petmanagement.petmanagement.repositories.VaccineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccinationNotificationService {
    private final PetRepository petRepository;
    private final VaccineRepository vaccineRepository;
    private final VaccinationNotificationRepository vaccinationNotificationRepository;

    public List<VaccinationNotification> getAllVaccinationNotification() {
        return vaccinationNotificationRepository.findAll();
    }

    public List<VaccinationNotification> getVaccinationNotificationByPet(Pet pet) {
        return vaccinationNotificationRepository.findByPet(pet);
    }

    public VaccinationNotification getVaccinationNotificationDetails(Long vaccinationNotificationId) throws DataNotFoundException {
        return vaccinationNotificationRepository.findById(vaccinationNotificationId).orElseThrow(() -> new DataNotFoundException("Can not find vaccination notification with ID: " + vaccinationNotificationId));
    }

    public VaccinationNotification addVaccinationNotification(VaccinationNotificationRequest vaccinationNotificationRequest) throws DataNotFoundException {
        VaccinationNotification vaccinationNotification = VaccinationNotification.builder()
                .pet(petRepository.findById(vaccinationNotificationRequest.getPetId()).orElseThrow(() -> new DataNotFoundException("Can not find pet with ID: " + vaccinationNotificationRequest.getPetId())))
                .vaccine(vaccineRepository.findById(vaccinationNotificationRequest.getVaccineId()).orElseThrow(() -> new DataNotFoundException("Can not find vaccine with ID: " + vaccinationNotificationRequest.getVaccineId())))
                .title(vaccinationNotificationRequest.getTitle())
                .note(vaccinationNotificationRequest.getNote())
                .build();
        return vaccinationNotificationRepository.save(vaccinationNotification);
    }

    public VaccinationNotification updateVaccinationNotification(Long vaccinationNotificationId, VaccinationNotificationRequest vaccinationNotificationRequest) throws DataNotFoundException {
        VaccinationNotification existingVaccinationNotification = vaccinationNotificationRepository.findById(vaccinationNotificationId).orElseThrow(() -> new DataNotFoundException("Can not find vaccination notification with ID: " + vaccinationNotificationId));
        existingVaccinationNotification.setTitle(vaccinationNotificationRequest.getTitle());
        existingVaccinationNotification.setVaccine(vaccineRepository.findById(vaccinationNotificationRequest.getVaccineId()).orElseThrow(() -> new DataNotFoundException("Can not find vaccine with ID: " + vaccinationNotificationRequest.getVaccineId())));
        existingVaccinationNotification.setNote(vaccinationNotificationRequest.getNote());
        return vaccinationNotificationRepository.save(existingVaccinationNotification);
    }

    public void deleteVaccinationNotification(Long vaccinationNotificationId) throws DataNotFoundException {
        VaccinationNotification existingVaccinationNotification = vaccinationNotificationRepository.findById(vaccinationNotificationId).orElseThrow(() -> new DataNotFoundException("Can not find vaccination notification with ID: " + vaccinationNotificationId));
        if (existingVaccinationNotification != null) {
            vaccinationNotificationRepository.deleteById(vaccinationNotificationId);
        }
    }
}
