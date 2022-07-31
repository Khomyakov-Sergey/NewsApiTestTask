package ru.clevertec.newsapi.mapper.comment;

import org.mapstruct.Mapper;
import ru.clevertec.newsapi.dto.comment.CommentDto;
import ru.clevertec.newsapi.entity.comment.Comment;

import java.util.List;

@Mapper(componentModel = "spring", uses = CommentMapper.class)
public interface CommentListMapper {
    List<Comment> toCommentList(List<CommentDto> dtos);
    List<CommentDto> toDtoList(List<Comment> comments);
}
