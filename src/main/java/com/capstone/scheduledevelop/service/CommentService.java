package com.capstone.scheduledevelop.service;

import com.capstone.scheduledevelop.dto.*;
import com.capstone.scheduledevelop.entity.Comment;
import com.capstone.scheduledevelop.entity.Schedule;
import com.capstone.scheduledevelop.entity.User;
import com.capstone.scheduledevelop.repository.CommentRepository;
import com.capstone.scheduledevelop.repository.ScheduleRepository;
import com.capstone.scheduledevelop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentResponseDto save(CommentRequestDto commentRequestDto) {

        User findUser = userRepository.findByIdOrElseThrow(commentRequestDto.getUserId());
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(commentRequestDto.getScheduleId());

        Comment comment = new Comment(commentRequestDto.getContent(), findUser, findSchedule);
        Comment saved = commentRepository.save(comment);

        return toDto(saved);
    }

    public List<CommentResponseDto> findAll() {
        List<Comment> comments = commentRepository.findAll();

        return comments.stream()
                .map(CommentResponseDto::toDto)
                .toList();
    }

    public CommentWithUserNameResponseDto findById(Long id) {

        Comment findComment = commentRepository.findCommentByIdOrElseThrow(id);
        User writer = findComment.getUser();

        return new CommentWithUserNameResponseDto(findComment.getId(), findComment.getContent(), writer.getUsername());
    }

    @Transactional
    public void updateComment(Long id, String content) {

        Comment comment = commentRepository.findCommentByIdOrElseThrow(id);
        comment.updateContent(content);
    }

    public void delete(Long id) {

        Comment findComment = commentRepository.findCommentByIdOrElseThrow(id);

        commentRepository.delete(findComment);

    }

    private CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getId(),
                comment.getSchedule().getId()
        );
    }
}
