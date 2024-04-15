package com.project.petmanagement.petmanagement.repositories;

import com.project.petmanagement.petmanagement.models.entity.NutritiousFood;
import com.project.petmanagement.petmanagement.models.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NutritiousFoodRepository extends JpaRepository<NutritiousFood, Long> {
    @Query(value = "select nf from nutritious_food nf " +
            "left join food_types_nutritious_food ftnf on ftnf.nutritious_food_id = nf.id " +
            "left join food_types ft on ftnf.food_type_id = ft.id " +
            "where ft.id = :foodTypeId", nativeQuery = true)
    List<NutritiousFood> findByFoodType(@Param("foodTypeId") Long foodTypeId);

    List<NutritiousFood> findBySpecies(Species species);

    List<NutritiousFood> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrNutritionContainingIgnoreCaseOrIngredientContainingIgnoreCase(String name, String description, String nutrition, String ingredient);

}
