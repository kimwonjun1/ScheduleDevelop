package com.capstone.scheduledevelop.service;

import com.capstone.scheduledevelop.dto.SchedulePageResponseDto;
import com.capstone.scheduledevelop.dto.ScheduleResponseDto;
import com.capstone.scheduledevelop.dto.ScheduleWithUserNameResponseDto;
import com.capstone.scheduledevelop.entity.Comment;
import com.capstone.scheduledevelop.entity.Schedule;
import com.capstone.scheduledevelop.entity.User;
import com.capstone.scheduledevelop.repository.ScheduleRepository;
import com.capstone.scheduledevelop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    // 생성자를 통한 UserRepository, ScheduleRepository 의존성 주입
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    // 일정 생성 비즈니스 로직
    public ScheduleResponseDto save(String title, String content, String username) {

        User findUser = userRepository.findUserByUsernameOrElseThrow(username);

        Schedule schedule = new Schedule(title, content);
        schedule.setUser(findUser);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContent());
    }

    // 일정 전체 조회 비즈니스 로직
    public List<ScheduleResponseDto> findAll() {

        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();

    }

    // 일정 페이징 조회 비즈니스 로직
    // 페이징 조건에 따라 Schedule 목록을 가져와 SchedulePageResponseDto 로 변환하여 반환
    public Page<SchedulePageResponseDto> findPagedSchedules(Pageable pageable) {

        return scheduleRepository.findAll(pageable)
                .map(SchedulePageResponseDto::new);
    }

    // 일정 단건 조회 비즈니스 로직
    public ScheduleWithUserNameResponseDto findById(Long id) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        User writer = findSchedule.getUser();

        return new ScheduleWithUserNameResponseDto(findSchedule.getTitle(), findSchedule.getContent(), writer.getUsername());
    }

    // 일정 수정 비즈니스 로직
    @Transactional
    public void updateSchedule(Long id, String content) {

        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        schedule.updateContent(content);
    }

    // 일정 삭제 비즈니스 로직
    public void delete(Long id) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(findSchedule);

    }

}
