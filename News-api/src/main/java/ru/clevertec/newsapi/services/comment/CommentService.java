package ru.clevertec.newsapi.services.comment;

import org.springframework.data.domain.Pageable;
import ru.clevertec.newsapi.dto.CommentDto;
import ru.clevertec.newsapi.dto.request.RequestCommentDto;

import java.util.List;

public interface CommentService {
    Long createComment(RequestCommentDto requestCommentDto);

    Long updateComment(Long id, CommentDto requestCommentDto);

    void deleteComment(Long id);

    CommentDto getComment(Long id);

    List<CommentDto> getCommentsByNewsId(Long newsId, Pageable pageable);

}
