package com.project.petmanagement.petmanagement.repositories;

import com.project.petmanagement.petmanagement.models.entity.RecurringSchedule;
import com.project.petmanagement.petmanagement.models.entity.WeeklyRepetition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeeklyRepetitionRepository extends JpaRepository<WeeklyRepetition, Long> {
    List<WeeklyRepetition> findByRecurringSchedule(RecurringSchedule recurringSchedule);
}
