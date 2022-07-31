package ru.clevertec.newsapi.controller.news;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.newsapi.dto.NewsDto;
import ru.clevertec.newsapi.services.news.NewsService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("/news")
@RestController
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<NewsDto> getAllNews(@PageableDefault(size = 6)
                                    @SortDefault(sort = "date", direction = Sort.Direction.DESC)
                                    Pageable pageable) {
        return newsService.getAllNews(pageable);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<NewsDto> getAllNewsByDescription(@RequestParam String search) {
        return newsService.search(search);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public NewsDto getNews(@PathVariable Long id) {
        return newsService.getNews(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Long createNews(@RequestBody @Valid NewsDto newsDto) {
        return newsService.createNews(newsDto);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long updateNews(@PathVariable Long id, @RequestBody @Valid NewsDto newsDto) {
        return newsService.updateNews(id, newsDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
    }


}
