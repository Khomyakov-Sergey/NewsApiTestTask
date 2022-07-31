package ru.clevertec.newsapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.newsapi.dto.NewsDto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCommentDto {

    private Long id;

    private LocalDateTime date;
    @NotBlank
    private String text;
    @NotBlank
    private String username;
    private NewsDto newsDto;

}
