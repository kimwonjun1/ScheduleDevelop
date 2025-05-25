package com.capstone.scheduledevelop.repository;

import com.capstone.scheduledevelop.entity.Comment;
import com.capstone.scheduledevelop.entity.Schedule;
import com.capstone.scheduledevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findCommentById(Long id);

    default Comment findCommentByIdOrElseThrow(Long id) {
        return findCommentById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }
}
