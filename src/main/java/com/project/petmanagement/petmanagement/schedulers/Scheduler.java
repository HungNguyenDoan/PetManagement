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
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class Scheduler {
    private final UserService userService;
    private final PetService petService;
    private final VaccinationNotificationService vaccinationNotificationService;
    private final FirebaseService firebaseService;

    @Scheduled(cron = "0 0 6 * * *")
    public void sendVaccinationNotification() {
        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            List<Pet> petList = petService.getPetsByUser(user);
            for (Pet pet : petList) {
                List<VaccinationNotification> vaccinationNotificationList = vaccinationNotificationService.getVaccinationNotificationByPet(pet);
                for (VaccinationNotification vaccinationNotification : vaccinationNotificationList) {
                    List<OneTimeSchedule> oneTimeScheduleList = vaccinationNotification.getSchedules();
                    for (OneTimeSchedule oneTimeSchedule : oneTimeScheduleList) {
                        LocalDate currentDate = LocalDate.now();
                        LocalDate scheduledDate = oneTimeSchedule.getDate().toLocalDate();
                        log.info("Current date: " + currentDate);
                        log.info("Scheduled date: " + scheduledDate);
                        if (currentDate.equals(scheduledDate)) {
                            log.info("FCM Token: " + user.getFcmToken());
                            log.info("Start to send notification on " + scheduledDate);
                            FCMNotification fcmNotification = FCMNotification.builder()
                                    .fcmToken(user.getFcmToken())
                                    .title(vaccinationNotification.getTitle())
                                    .body(vaccinationNotification.getNote())
                                    .build();
                            try {
                                firebaseService.pushNotification(fcmNotification);
                            } catch (FirebaseMessagingException e) {
                                log.error(e.getMessage());
                            }
                            log.info("Finish sending notification on " + scheduledDate);
                        }
                    }
                }
            }
        }
    }

    @Scheduled(fixedRate = 60000)
    public void sendCareActivityNotification() {
        log.info("Test");
    }
}
