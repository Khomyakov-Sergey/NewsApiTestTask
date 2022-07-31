package ru.clevertec.newsapi.service.comment;

import org.springframework.data.domain.Pageable;
import ru.clevertec.newsapi.dto.comment.CommentDto;
import ru.clevertec.newsapi.dto.comment.CreateCommentDto;

import java.util.List;

public interface CommentService {
    Long createComment(CreateCommentDto createCommentDto);

    Long updateComment(Long id, CommentDto requestCommentDto);

    void deleteComment(Long id);

    CommentDto getComment(Long id);

    List<CommentDto> getCommentsByNewsId(Long newsId, Pageable pageable);

}
