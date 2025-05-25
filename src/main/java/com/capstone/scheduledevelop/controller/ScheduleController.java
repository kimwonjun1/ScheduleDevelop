package com.capstone.scheduledevelop.controller;

import com.capstone.scheduledevelop.dto.*;
import com.capstone.scheduledevelop.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody @Valid CreateScheduleRequestDto createScheduleRequestDto) {

        ScheduleResponseDto scheduleResponseDto =
                scheduleService.save(
                        createScheduleRequestDto.getTitle(),
                        createScheduleRequestDto.getContent(),
                        createScheduleRequestDto.getUsername()
                );

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();

        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ScheduleWithUserNameResponseDto> findById(@PathVariable Long id) {

        ScheduleWithUserNameResponseDto scheduleWithUserNameResponseDto = scheduleService.findById(id);

        return new ResponseEntity<>(scheduleWithUserNameResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateContent(@PathVariable Long id, @RequestBody UpdateScheduleRequestDto updateScheduleRequestDto) {

        scheduleService.updateSchedule(id, updateScheduleRequestDto.getContent());

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        scheduleService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
