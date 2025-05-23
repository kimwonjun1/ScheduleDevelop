package com.capstone.scheduledevelop.service;

import com.capstone.scheduledevelop.dto.ScheduleResponseDto;
import com.capstone.scheduledevelop.dto.ScheduleWithUserNameResponseDto;
import com.capstone.scheduledevelop.entity.Schedule;
import com.capstone.scheduledevelop.entity.User;
import com.capstone.scheduledevelop.repository.ScheduleRepository;
import com.capstone.scheduledevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(String title, String content, String username) {

        User findUser = userRepository.findUserByUsernameOrElseThrow(username);

        Schedule schedule = new Schedule(title, content);
        schedule.setUser(findUser);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContent());
    }

    public List<ScheduleResponseDto> findAll() {

        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();

    }

    public ScheduleWithUserNameResponseDto findById(Long id) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        User user = findSchedule.getUser();

        return new ScheduleWithUserNameResponseDto(findSchedule.getTitle(), findSchedule.getContent(), user.getUsername());
    }
}
