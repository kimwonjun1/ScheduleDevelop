package com.capstone.scheduledevelop.repository;

import com.capstone.scheduledevelop.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {



}
