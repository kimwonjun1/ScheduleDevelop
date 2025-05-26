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

    // 생성자를 통한 CommentRepository, UserRepository, ScheduleRepository 의존성 주입
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    // 댓글 생성 비즈니스 로직
    @Transactional
    public CommentResponseDto save(CommentRequestDto commentRequestDto) {

        User findUser = userRepository.findByIdOrElseThrow(commentRequestDto.getUserId());
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(commentRequestDto.getScheduleId());

        Comment comment = new Comment(commentRequestDto.getContent(), findUser, findSchedule);
        Comment saved = commentRepository.save(comment);

        return toDto(saved);
    }

    // 댓글 전체 조회 비즈니스 로직
    public List<CommentResponseDto> findAll() {
        List<Comment> comments = commentRepository.findAll();

        return comments.stream()
                .map(CommentResponseDto::toDto)
                .toList();
    }
    // 댓글 단건 조회 비즈니스 로직
    public CommentWithUserNameResponseDto findById(Long id) {

        Comment findComment = commentRepository.findCommentByIdOrElseThrow(id);
        User writer = findComment.getUser();

        return new CommentWithUserNameResponseDto(findComment.getId(), findComment.getContent(), writer.getUsername());
    }

    // 댓글 수정 비즈니스 로직
    @Transactional
    public void updateComment(Long id, String content) {

        Comment comment = commentRepository.findCommentByIdOrElseThrow(id);
        comment.updateContent(content);
    }

    // 댓글 삭제 비즈니스 로직
    public void delete(Long id) {

        Comment findComment = commentRepository.findCommentByIdOrElseThrow(id);
        commentRepository.delete(findComment);
    }

    // Comment entity -> CommentResponseDto 변환 메서드
    private CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getId(),
                comment.getSchedule().getId()
        );
    }
}
