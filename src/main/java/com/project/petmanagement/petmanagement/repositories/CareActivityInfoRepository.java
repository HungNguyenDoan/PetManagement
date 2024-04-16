package com.project.petmanagement.petmanagement.repositories;

import com.project.petmanagement.petmanagement.models.entity.CareActivityInfo;
import com.project.petmanagement.petmanagement.models.entity.CareActivityNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareActivityInfoRepository extends JpaRepository<CareActivityInfo, Long> {
    List<CareActivityInfo> findByCareActivityNotification(CareActivityNotification careActivityNotification);
}
