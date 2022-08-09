package ru.clevertec.newsapi.dto.news;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * DTO class for creating news.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestNewsDto {
    @NotBlank
    private String title;
    @NotBlank
    private String text;
}
