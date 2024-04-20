package com.project.petmanagement.petmanagement.controllers;

import com.project.petmanagement.petmanagement.models.entity.DailyActivity;
import com.project.petmanagement.petmanagement.models.entity.DailyActivityLog;
import com.project.petmanagement.petmanagement.models.entity.Pet;
import com.project.petmanagement.petmanagement.models.entity.Species;
import com.project.petmanagement.petmanagement.payloads.responses.DataResponse;
import com.project.petmanagement.petmanagement.payloads.responses.ErrorResponse;
import com.project.petmanagement.petmanagement.services.DailyActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/daily_activity")
public class DailyActivityController {
    private final DailyActivityService dailyActivityService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllSpecies() {
        List<DailyActivity> dailyActivityList = dailyActivityService.getAllDailyActivity();
        if (!dailyActivityList.isEmpty()) {
            DataResponse dataResponse = DataResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("Get all daily activity successfully")
                    .data(dailyActivityList)
                    .build();
            return new ResponseEntity<>(dataResponse, HttpStatus.OK);
        }
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(500)
                .message("There is no daily activity in Database")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPetDetails(@PathVariable("id") Long dailyActivityId) {
        try {
            DailyActivity dailyActivity = dailyActivityService.getDailyById(dailyActivityId);
            DataResponse dataResponse = DataResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("Get pet details successfully")
                    .data(dailyActivity)
                    .build();
            return new ResponseEntity<>(dataResponse, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

}
