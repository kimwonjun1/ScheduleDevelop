package com.capstone.scheduledevelop.controller;

import com.capstone.scheduledevelop.dto.*;
import com.capstone.scheduledevelop.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;


    // 일정 생성 API 
    // json 데이터
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

    // 일정 전체 조회 API
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();

        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    // 일정 페이징 조회 API
    @GetMapping("/paged")
    public ResponseEntity<Page<SchedulePageResponseDto>> findPagedSchedules(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        // 정렬 기준 - updatedAt 내림차순
        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());
        Page<SchedulePageResponseDto> pagedSchedules = scheduleService.findPagedSchedules(pageable);

        return new ResponseEntity<>(pagedSchedules, HttpStatus.OK);
    }

    // 일정 단건 조회 API
    @GetMapping("/{id}")
    public  ResponseEntity<ScheduleWithUserNameResponseDto> findById(@PathVariable Long id) {

        ScheduleWithUserNameResponseDto scheduleWithUserNameResponseDto = scheduleService.findById(id);

        return new ResponseEntity<>(scheduleWithUserNameResponseDto, HttpStatus.OK);
    }

    // 일정 수정 API
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateContent(@PathVariable Long id, @RequestBody UpdateScheduleRequestDto updateScheduleRequestDto) {

        scheduleService.updateSchedule(id, updateScheduleRequestDto.getContent());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 일정 삭제 API
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        scheduleService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
