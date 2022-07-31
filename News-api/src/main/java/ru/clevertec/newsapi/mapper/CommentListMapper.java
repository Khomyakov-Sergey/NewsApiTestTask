package ru.clevertec.newsapi.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.newsapi.dto.CommentDto;
import ru.clevertec.newsapi.entity.Comment;

import java.util.List;

@Mapper(componentModel = "spring", uses = CommentMapper.class)
public interface CommentListMapper {
    List<Comment> toCommentList(List<CommentDto> dtos);
    List<CommentDto> toDtoList(List<Comment> comments);
}
