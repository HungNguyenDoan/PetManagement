package com.project.petmanagement.petmanagement.repositories;

import com.project.petmanagement.petmanagement.models.entity.DailyRepetition;
import com.project.petmanagement.petmanagement.models.entity.RecurringSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyRepetitionRepository extends JpaRepository<DailyRepetition, Long> {
    List<DailyRepetition> findByRecurringSchedule(RecurringSchedule recurringSchedule);
}
