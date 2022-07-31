package ru.clevertec.newsapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.newsapi.dto.CommentDto;
import ru.clevertec.newsapi.dto.request.RequestCommentDto;
import ru.clevertec.newsapi.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDto toDto(Comment comment);

    @Mapping(target = "news", source = "dto.newsDto")
    Comment toComment(RequestCommentDto dto);

}
