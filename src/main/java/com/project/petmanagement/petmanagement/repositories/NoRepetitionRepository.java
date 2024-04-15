package com.project.petmanagement.petmanagement.repositories;

import com.project.petmanagement.petmanagement.models.entity.NoRepetition;
import com.project.petmanagement.petmanagement.models.entity.RecurringSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoRepetitionRepository extends JpaRepository<NoRepetition, Long> {
    NoRepetition findByRecurringSchedule(RecurringSchedule recurringSchedule);
}
