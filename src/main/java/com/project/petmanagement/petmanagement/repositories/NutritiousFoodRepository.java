package com.project.petmanagement.petmanagement.repositories;

import com.project.petmanagement.petmanagement.models.entity.FoodType;
import com.project.petmanagement.petmanagement.models.entity.NutritiousFood;
import com.project.petmanagement.petmanagement.models.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NutritiousFoodRepository extends JpaRepository<NutritiousFood, Long> {
    List<NutritiousFood> findByFoodType(FoodType foodType);

    List<NutritiousFood> findBySpecies(Species species);

    List<NutritiousFood> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrNutritionContainingIgnoreCaseOrIngredientContainingIgnoreCase(String name, String description, String nutrition, String ingredient);

}
