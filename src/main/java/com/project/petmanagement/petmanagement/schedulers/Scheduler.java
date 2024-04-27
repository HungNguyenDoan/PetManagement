package com.project.petmanagement.petmanagement.schedulers;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.project.petmanagement.petmanagement.models.entity.*;
import com.project.petmanagement.petmanagement.models.enums.FrequencyEnum;
import com.project.petmanagement.petmanagement.payloads.requests.FCMNotification;
import com.project.petmanagement.petmanagement.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class Scheduler {
    private final UserService userService;
    private final PetService petService;
    private final VaccinationNotificationService vaccinationNotificationService;
    private final CareActivityNotificationService careActivityNotificationService;
    private final FirebaseService firebaseService;

    @Scheduled(cron = "0 0 6 * * *")
    public void sendVaccinationNotification() {
        LocalDate currentDate = LocalDate.now();
        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            List<Pet> petList = petService.getPetsByUser(user);
            for (Pet pet : petList) {
                List<VaccinationNotification> vaccinationNotificationList = vaccinationNotificationService.getVaccinationNotificationByPet(pet);
                for (VaccinationNotification vaccinationNotification : vaccinationNotificationList) {
                    List<OneTimeSchedule> oneTimeScheduleList = vaccinationNotification.getSchedules();
                    for (OneTimeSchedule oneTimeSchedule : oneTimeScheduleList) {
                        if (!oneTimeSchedule.getVaccinationStatus()) {
                            LocalDate scheduledDate = oneTimeSchedule.getDate().toLocalDate();
                            String time = oneTimeSchedule.getTime();
                            if (scheduledDate.equals(currentDate)) {
                                log.info("User's ID: " + user.getId());
                                log.info("FCM Token: " + user.getFcmToken());
                                log.info("Pet's ID: " + pet.getId());
                                log.info("Date: " + scheduledDate);
                                FCMNotification fcmNotification = FCMNotification.builder().fcmToken(user.getFcmToken()).title(vaccinationNotification.getTitle()).body(time + ": " + vaccinationNotification.getNote()).build();
                                try {
                                    String message = firebaseService.pushNotification(fcmNotification);
                                    log.info(message);
                                } catch (FirebaseMessagingException e) {
                                    log.error(e.getMessage());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void sendCareActivityNotification() {
//        LocalDate currentDate = LocalDate.now();
        LocalDate currentDate = LocalDate.of(2024, 4, 28);
        String currentTime = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute();
        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            List<Pet> petList = petService.getPetsByUser(user);
            for (Pet pet : petList) {
                List<CareActivityNotification> careActivityNotificationList = careActivityNotificationService.getCareActivityNotificationByPet(pet);
                for (CareActivityNotification careActivityNotification : careActivityNotificationList) {
                    if (careActivityNotification.getNotificationStatus()) {
                        RecurringSchedule recurringSchedule = careActivityNotification.getSchedule();
                        FrequencyEnum frequency = recurringSchedule.getFrequency();
                        int step = recurringSchedule.getValue();
                        String scheduledTime = recurringSchedule.getTime();
                        boolean onTime = false;
                        if (frequency.compareTo(FrequencyEnum.NO_REPEAT) == 0) {
                            LocalDate scheduledDate = recurringSchedule.getDate().toLocalDate();
                            if (scheduledDate.equals(currentDate) && scheduledTime.equals(currentTime)) {
                                log.info("NO_REPEAT OK");
                                onTime = true;
                            }
                        } else if (frequency.compareTo(FrequencyEnum.DAILY) == 0) {
                            LocalDate fromDate = recurringSchedule.getFromDate().toLocalDate();
                            LocalDate toDate = recurringSchedule.getToDate().toLocalDate();
                            LocalDate date = fromDate;
                            while (!date.isAfter(toDate)) {
                                log.info("DAILY: " + date);
                                if (date.equals(currentDate) && scheduledTime.equals(currentTime)) {
                                    log.info("DAILY: OK");
                                    onTime = true;
                                    break;
                                } else if (date.isAfter(currentDate)) {
                                    break;
                                }
                                date = date.plusDays(step);
                            }
                        } else if (frequency.compareTo(FrequencyEnum.WEEKLY) == 0) {
                            List<DayOfWeek> scheduledDaysOfWeek = recurringSchedule.getDaysOfWeek();
                            Collections.sort(scheduledDaysOfWeek);
                            LocalDate fromDate = recurringSchedule.getFromDate().toLocalDate();
                            LocalDate toDate = recurringSchedule.getToDate().toLocalDate();
                            DayOfWeek dateToDayOfWeek = currentDate.getDayOfWeek();
                            if (scheduledDaysOfWeek.contains(dateToDayOfWeek)) {
                                List<LocalDate> dateList = new ArrayList<>();
                                LocalDate firstDayOfWeek = fromDate.with(TemporalAdjusters.nextOrSame(scheduledDaysOfWeek.get(0)));
                                dateList.add(firstDayOfWeek);
                                for (int i = 1; i < scheduledDaysOfWeek.size(); i++) {
                                    dateList.add(firstDayOfWeek.with(TemporalAdjusters.nextOrSame(scheduledDaysOfWeek.get(i))));
                                }
                                boolean stop = false;
                                for (LocalDate localDate : dateList) {
                                    if (stop) {
                                        break;
                                    }
                                    LocalDate tempDate = localDate;
                                    while (!tempDate.isAfter(toDate)) {
                                        log.info("WEEKLY: " + tempDate);
                                        if (tempDate.equals(currentDate) && scheduledTime.equals(currentTime)) {
                                            log.info("WEEKLY: OK");
                                            onTime = true;
                                            stop = true;
                                            break;
                                        } else if (tempDate.isAfter(currentDate)) {
                                            break;
                                        }
                                        tempDate = tempDate.plusWeeks(step);
                                    }
                                }
                            }
                        } else if (frequency.compareTo(FrequencyEnum.MONTHLY) == 0) {
                            List<Integer> scheduledDaysOfMonth = recurringSchedule.getDaysOfMonth();
                            Collections.sort(scheduledDaysOfMonth);
                            LocalDate fromDate = recurringSchedule.getFromDate().toLocalDate();
                            LocalDate toDate = recurringSchedule.getToDate().toLocalDate();
                            int dayOfMonth = currentDate.getDayOfMonth();
                            if (scheduledDaysOfMonth.contains(dayOfMonth)) {
                                List<LocalDate> dateList = new ArrayList<>();
                                if (fromDate.getDayOfMonth() <= scheduledDaysOfMonth.get(0)) {
                                    for (Integer i : scheduledDaysOfMonth) {
                                        dateList.add(LocalDate.of(fromDate.getYear(), fromDate.getMonth(), i));
                                    }
                                } else {
                                    for (Integer i : scheduledDaysOfMonth) {
                                        Month nextMonth = fromDate.getMonth().plus(1);
                                        dateList.add(LocalDate.of(!nextMonth.equals(Month.JANUARY) ? fromDate.getYear() : fromDate.getYear() + 1, nextMonth, i));
                                    }
                                }
                                boolean stop = false;
                                for (LocalDate localDate : dateList) {
                                    if (stop) {
                                        break;
                                    }
                                    LocalDate tempDate = localDate;
                                    while (!tempDate.isAfter(toDate)) {
                                        log.info("MONTHLY: " + tempDate);
                                        if (tempDate.equals(currentDate) && scheduledTime.equals(currentTime)) {
                                            log.info("MONTHLY: OK");
                                            onTime = true;
                                            stop = true;
                                            break;
                                        } else if (tempDate.isAfter(currentDate)) {
                                            break;
                                        }
                                        tempDate = tempDate.plusMonths(step);
                                    }
                                }
                            }
                        }
                        if (onTime) {
                            log.info("User's ID: " + user.getId());
                            log.info("FCM Token: " + user.getFcmToken());
                            log.info("Pet's ID: " + pet.getId());
                            FCMNotification fcmNotification = FCMNotification.builder().fcmToken(user.getFcmToken()).title(careActivityNotification.getTitle()).body(careActivityNotification.getNote()).build();
                            try {
                                String message = firebaseService.pushNotification(fcmNotification);
                                log.info(message);
                            } catch (FirebaseMessagingException e) {
                                log.error(e.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }
}
