package com.project.petmanagement.petmanagement.repositories;

import com.project.petmanagement.petmanagement.models.entity.OneTimeSchedule;
import com.project.petmanagement.petmanagement.models.entity.VaccinationNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OneTimeScheduleRepository extends JpaRepository<OneTimeSchedule, Long> {
    List<OneTimeSchedule> findByVaccinationNotification(VaccinationNotification vaccinationNotification);
}
