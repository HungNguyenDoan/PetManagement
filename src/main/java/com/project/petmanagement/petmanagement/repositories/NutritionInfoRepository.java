package com.project.petmanagement.petmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.petmanagement.petmanagement.models.NutritionInfo;
import java.util.List;


@Repository
public interface NutritionInfoRepository extends JpaRepository<NutritionInfo, Long> {
    // List<NutritionInfo> findByListFoodTypesId(Long foodTypeId);
}
