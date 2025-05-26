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

    // 생성자를 통한 ScheduleService 의존성 주입
    private final ScheduleService scheduleService;

    // 일정 생성 API
    // json 데이터 -> validation 진행 -> CreateScheduleRequestDto에 할당 -> CreateScheduleRequestDto의 title, content, username을 service layer의 save 메서드를 통해 DB에 저장
    // DB 저장된 데이터를 ScheduleResponseDto로 만들고 CREATED 상태코드와 함께 클라이언트로 리턴
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
    // service layer의 findAll 메서드를 통해 일정 전체 조회 로직 실행 후, 200 상태코드 + 조회한 일정 정보 리스트를 scheduleResponseDtoList로 리턴
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {

        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();

        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    // 일정 페이징 조회 API
    // requestParam으로 받은 페이지, 사이즈를 page, size 변수에 할당
    // service layer의 findPagedSchedules 메서드를 통해 일정 페이징 조회 로직 실행 후, 200 OK 상태코드 + 조회한 일정 정보를 pagedSchedules로 리턴
    @GetMapping("/paged")
    public ResponseEntity<Page<SchedulePageResponseDto>> findPagedSchedules(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        // 정렬 기준 - updatedAt 내림차순
        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());
        Page<SchedulePageResponseDto> pagedSchedules = scheduleService.findPagedSchedules(pageable);

        return new ResponseEntity<>(pagedSchedules, HttpStatus.OK);
    }

    // 일정 단건 조회 API
    // requestParam으로 받은 일정 ID를 PathVariable로 id 변수에 받음
    // service layer의 findById 메서드를 통해 일정 단건 조회 로직 실행 후, 200 OK 상태코드 + 조회한 일정 정보를 ScheduleWithUserNameResponseDto로 리턴
    @GetMapping("/{id}")
    public  ResponseEntity<ScheduleWithUserNameResponseDto> findById(@PathVariable Long id) {

        ScheduleWithUserNameResponseDto scheduleWithUserNameResponseDto = scheduleService.findById(id);

        return new ResponseEntity<>(scheduleWithUserNameResponseDto, HttpStatus.OK);
    }

    // 일정 수정 API
    // requestParam으로 받은 일정 ID를 PathVariable로 id 변수에 받음. RequestBody를 통해 json데이터를 UpdateScheduleRequestDto로 받음
    // service layer의 updateSchedule 메서드를 통해 일정(내용) 수정 로직 실행 루 200 OK 상태코드 리턴
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateContent(@PathVariable Long id, @RequestBody UpdateScheduleRequestDto updateScheduleRequestDto) {

        scheduleService.updateSchedule(id, updateScheduleRequestDto.getContent());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 일정 삭제 API
    // requestParam으로 받은 일정 ID를 PathVariable로 id 변수에 받음
    // service layer의 delete 메서드를 통해 일정 삭제 로직 실행 후 200 OK 상태코드 리턴
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        scheduleService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
