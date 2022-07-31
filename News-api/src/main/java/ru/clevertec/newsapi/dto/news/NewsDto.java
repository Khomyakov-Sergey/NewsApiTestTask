package ru.clevertec.newsapi.dto.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.newsapi.dto.comment.CommentDto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {
    private Long id;
    private LocalDateTime date;
    @NotBlank
    private String title;
    @NotBlank
    private String text;
    private List<CommentDto> comments;
}
