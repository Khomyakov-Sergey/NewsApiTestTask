package ru.clevertec.newsapi.mapper.comment;

import org.mapstruct.Mapper;
import ru.clevertec.newsapi.dto.comment.CommentDto;
import ru.clevertec.newsapi.entity.comment.Comment;

import java.util.List;

/**
 * Mapper class for comments. It for converts list of Comments to list of CommentDto and vice versa.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Mapper(componentModel = "spring", uses = CommentMapper.class)
public interface CommentListMapper {

    /**
     * This method converts list of Comments to list of CommentDto.
     * @param commentDtos - List of comments representation in DTO.
     * @return Comment - List of Entities Comment.
     */
    List<Comment> toCommentList(List<CommentDto> commentDtos);

    /**
     * This method converts list of Comments to list of CommentDto.
     * @param comments - List of Entities Comment.
     * @return List<CommentDto> -  List of comments representation in DTO.
     */
    List<CommentDto> toDtoList(List<Comment> comments);
}
