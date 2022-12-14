package ru.clevertec.newsapi.service.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.newsapi.dto.comment.ResponseCommentDto;
import ru.clevertec.newsapi.dto.comment.RequestCommentDto;
import ru.clevertec.newsapi.entity.comment.Comment;
import ru.clevertec.newsapi.exception.EntityByIdNotFoundException;
import ru.clevertec.newsapi.mapper.comment.CommentListMapper;
import ru.clevertec.newsapi.mapper.comment.CommentMapper;
import ru.clevertec.newsapi.repository.comment.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class for comments with data processing.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    private final CommentListMapper commentListMapper;

    /**
     * This method creates new comment and transfers it in CommentRepository to save.
     * @param requestCommentDto - Comment information from request.
     * @return id - Comment identifier.
     */
    @Override
    @Transactional
    @CachePut(cacheNames = "commentsCache", key = "#requestCommentDto.username")
    public Long createComment(RequestCommentDto requestCommentDto) {
        Comment comment = commentMapper.toComment(requestCommentDto);
        return commentRepository.save(comment).getId();
    }

    /**
     * This method searches comment by the transferred id and updates information by using CommentRepository.
     * If it doesn`t exist throw EntityByIdNotFoundException.
     * @param id - Comment identifier.
     * @param requestCommentDto - Create information from request.
     * @return id - Comment identifier.
     */
    @Override
    @Transactional
    @CachePut(cacheNames = "commentsCache", key = "#id")
    public Long updateComment(Long id, RequestCommentDto requestCommentDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new EntityByIdNotFoundException(id));
        comment.setText(requestCommentDto.getText());
        comment.setDate(LocalDateTime.now());
        return comment.getId();
    }

    /**
     * This method searches comment by the transferred id. If it exists, deletes it by using CommentRepository.
     * @param id - Comment identifier.
     */
    @Override
    @CacheEvict(cacheNames = "commentsCache", key = "#id")
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    /**
     * This method searches comment by the transferred id using NewsRepository.
     * If it doesn`t exist throw EntityByIdNotFoundException.
     * @param id - Comment identifier.
     * @return ResponseCommentDto - Comment representation in DTO.
     */
    @Override
    @Cacheable(cacheNames = "commentsCache", key = "#id")
    public ResponseCommentDto getComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new EntityByIdNotFoundException(id));
        return commentMapper.toDto(comment);
    }

    /**
     * This method searches comments by the transferred news id using CommentRepository.
     * It supports different sorting by using pagination.
     * @param newsId - News identifier.
     * @return List<ResponseCommentDto> - List of all comments representation for definite news in DTO.
     */
    @Override
    @CachePut(cacheNames = "commentsCache", key = "#newsId")
    public List<ResponseCommentDto> getCommentsByNewsId(Long newsId, Pageable pageable) {
        List<Comment> comments = commentRepository.findAllByNews_Id(newsId, pageable).getContent();
        return commentListMapper.toDtoList(comments);
    }
}
