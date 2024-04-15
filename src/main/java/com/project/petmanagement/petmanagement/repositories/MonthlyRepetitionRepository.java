package com.project.petmanagement.petmanagement.repositories;

import com.project.petmanagement.petmanagement.models.entity.MonthlyRepetition;
import com.project.petmanagement.petmanagement.models.entity.RecurringSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyRepetitionRepository extends JpaRepository<MonthlyRepetition, Long> {
    List<MonthlyRepetition> findByRecurringSchedule(RecurringSchedule recurringSchedule);
}
