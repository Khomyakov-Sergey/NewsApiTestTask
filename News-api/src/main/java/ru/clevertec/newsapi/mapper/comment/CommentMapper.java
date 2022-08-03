package ru.clevertec.newsapi.mapper.comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.newsapi.dto.comment.CommentDto;
import ru.clevertec.newsapi.dto.comment.CreateCommentDto;
import ru.clevertec.newsapi.entity.comment.Comment;

/**
 * Mapper class for comment. It for converts Comment to CommentDto and CreateCommentDto to Comment.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {

    /**
     * This method converts Comment to CommentDto.
     * @param comment - Entity Comment.
     * @return CommentDto - Comment representation in DTO.
     */
    CommentDto toDto(Comment comment);

    /**
     * This method converts CreateCommentDto to Comment.
     * @param createCommentDto - Comment representation for creating comment in DTO.
     * @return Comment - Entity Comment.
     */
    @Mapping(target = "news", source = "createCommentDto.newsDto")
    Comment toComment(CreateCommentDto createCommentDto);

}
