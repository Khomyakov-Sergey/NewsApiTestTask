package ru.clevertec.newsapi.service.comment;

import org.springframework.data.domain.Pageable;
import ru.clevertec.newsapi.dto.comment.ResponseCommentDto;
import ru.clevertec.newsapi.dto.comment.RequestCommentDto;

import java.util.List;

public interface CommentService {
    Long createComment(RequestCommentDto requestCommentDto);

    Long updateComment(Long id, RequestCommentDto requestCommentDto);

    void deleteComment(Long id);

    ResponseCommentDto getComment(Long id);

    List<ResponseCommentDto> getCommentsByNewsId(Long newsId, Pageable pageable);

}
