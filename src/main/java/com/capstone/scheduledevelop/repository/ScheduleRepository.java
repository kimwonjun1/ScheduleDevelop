package com.capstone.scheduledevelop.repository;

import com.capstone.scheduledevelop.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // ID에 맞는 일정 조회 후 없는 경우 NOT_FOUND 상태코드 리턴
    default Schedule findByIdOrElseThrow(Long id) {
    return findById(id)
            .orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

}
