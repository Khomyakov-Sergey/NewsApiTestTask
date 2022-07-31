package ru.clevertec.newsapi.services.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.newsapi.dto.CommentDto;
import ru.clevertec.newsapi.dto.request.RequestCommentDto;
import ru.clevertec.newsapi.entity.Comment;
import ru.clevertec.newsapi.mapper.CommentListMapper;
import ru.clevertec.newsapi.mapper.CommentMapper;
import ru.clevertec.newsapi.repositories.comment.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;
    private final CommentListMapper commentListMapper;


    @Override
    @Transactional
    public Long createComment(RequestCommentDto commentDto) {
        Comment comment = commentMapper.toComment(commentDto);
        return commentRepository.save(comment).getId();
    }

    @Override
    @Transactional
    public Long updateComment(Long id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(NoSuchElementException::new);
        comment.setText(comment.getText());
        comment.setDate(LocalDateTime.now());
        return comment.getId();
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentDto getComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return commentMapper.toDto(comment);
    }

    @Override
    public List<CommentDto> getCommentsByNewsId(Long newsId, Pageable pageable) {
        List<Comment> comments = commentRepository.findAllByNews_Id(newsId, pageable).getContent();
        return commentListMapper.toDtoList(comments);
    }
}
