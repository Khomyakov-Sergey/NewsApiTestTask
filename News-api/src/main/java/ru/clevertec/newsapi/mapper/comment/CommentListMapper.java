package ru.clevertec.newsapi.mapper.comment;

import org.mapstruct.Mapper;
import ru.clevertec.newsapi.dto.comment.ResponseCommentDto;
import ru.clevertec.newsapi.entity.comment.Comment;

import java.util.List;

/**
 * Mapper class for comments. It for converts list of Comments to list of responseCommentDto and vice versa.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Mapper(componentModel = "spring", uses = CommentMapper.class)
public interface CommentListMapper {

    /**
     * This method converts list of responseCommentDto to list of Comments .
     * @param responseCommentDtos - List of comments representation in DTO.
     * @return Comment - List of Entities Comment.
     */
    List<Comment> toCommentList(List<ResponseCommentDto> responseCommentDtos);

    /**
     * This method converts list of Comments to list of responseCommentDto.
     * @param comments - List of Entities Comment.
     * @return List<CommentDto> -  List of comments representation in DTO.
     */
    List<ResponseCommentDto> toDtoList(List<Comment> comments);
}
