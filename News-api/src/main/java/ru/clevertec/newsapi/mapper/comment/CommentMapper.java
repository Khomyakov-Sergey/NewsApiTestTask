package ru.clevertec.newsapi.mapper.comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.newsapi.dto.comment.RequestCommentDto;
import ru.clevertec.newsapi.dto.comment.ResponseCommentDto;
import ru.clevertec.newsapi.entity.comment.Comment;

/**
 * Mapper class for comment. It for converts Comment to ResponseCommentDto and RequestCommentDto to Comment.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {

    /**
     * This method converts Comment to ResponseCommentDto.
     * @param comment - Entity Comment.
     * @return ResponseCommentDto - Comment representation in DTO.
     */
    ResponseCommentDto toDto(Comment comment);

    /**
     * This method converts RequestCommentDto to Comment.
     * @param requestCommentDto - Comment representation in DTO for creating comment.
     * @return Comment - Entity Comment.
     */
    @Mapping(source = "requestCommentDto.newsId", target = "news.id")
    Comment toComment(RequestCommentDto requestCommentDto);

}
