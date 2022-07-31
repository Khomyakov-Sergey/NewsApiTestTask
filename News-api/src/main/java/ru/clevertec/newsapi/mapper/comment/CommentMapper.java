package ru.clevertec.newsapi.mapper.comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.newsapi.dto.comment.CommentDto;
import ru.clevertec.newsapi.dto.comment.CreateCommentDto;
import ru.clevertec.newsapi.entity.comment.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDto toDto(Comment comment);

    @Mapping(target = "news", source = "dto.newsDto")
    Comment toComment(CreateCommentDto dto);

}
