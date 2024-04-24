package com.project.petmanagement.petmanagement.schedulers;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.project.petmanagement.petmanagement.models.entity.OneTimeSchedule;
import com.project.petmanagement.petmanagement.models.entity.Pet;
import com.project.petmanagement.petmanagement.models.entity.User;
import com.project.petmanagement.petmanagement.models.entity.VaccinationNotification;
import com.project.petmanagement.petmanagement.payloads.requests.FCMNotification;
import com.project.petmanagement.petmanagement.services.FirebaseService;
import com.project.petmanagement.petmanagement.services.PetService;
import com.project.petmanagement.petmanagement.services.UserService;
import com.project.petmanagement.petmanagement.services.VaccinationNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class Scheduler {
    private final UserService userService;
    private final PetService petService;
    private final VaccinationNotificationService vaccinationNotificationService;
    private final FirebaseService firebaseService;

    // @Scheduled(fixedRate = 120000L)
    @Scheduled(cron = "0 0 6 ? * * *")
    public void sendVaccinationNotification() {
        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            List<Pet> petList = petService.getPetsByUser(user);
            for (Pet pet : petList) {
                List<VaccinationNotification> vaccinationNotificationList = vaccinationNotificationService.getVaccinationNotificationByPet(pet);
                for (VaccinationNotification vaccinationNotification : vaccinationNotificationList) {
                    for (OneTimeSchedule oneTimeSchedule : vaccinationNotification.getSchedules()) {
                        LocalDate currentDate = LocalDate.now();
                        LocalDate scheduledDate = oneTimeSchedule.getDate().toLocalDate();
                        if (currentDate.equals(scheduledDate)) {
                            FCMNotification fcmNotification = FCMNotification.builder()
                                    .fcmToken(user.getFcmToken())
                                    .title(vaccinationNotification.getTitle())
                                    .body(vaccinationNotification.getNote())
                                    .build();
                            try {
                                firebaseService.pushNotification(fcmNotification);
                            } catch (FirebaseMessagingException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        }
    }

    @Scheduled()
    public void sendCareActivityNotification() {

    }
}
