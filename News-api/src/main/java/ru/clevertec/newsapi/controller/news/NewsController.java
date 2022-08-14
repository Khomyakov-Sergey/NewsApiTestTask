package ru.clevertec.newsapi.controller.news;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.newsapi.dto.news.RequestNewsDto;
import ru.clevertec.newsapi.dto.news.ResponseNewsDto;
import ru.clevertec.newsapi.service.news.NewsService;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for news. It gets request and redirects it to the service class.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Slf4j
@RequestMapping("/news")
@Api(tags = {"News controller"})
@Tag(name = "News controller", description = "Controller for operations with news")
@RestController
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    /**
     * This method gets all news with using service layer. Also supporting pageable view.
     * @return List<ResponseNewsDto> - List of news.
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all news from database with some kind of sorting news")
    public List<ResponseNewsDto> getAllNews(@PageableDefault(size = 6)
                                            @SortDefault(sort = "news_created_at", direction = Sort.Direction.DESC)
                                            Pageable pageable) {
        return newsService.getAllNews(pageable);
    }

    /**
     * This method search all news with keyword among two columns in news(text and title).
     * @return List<ResponseNewsDto> - List of news.
     */
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all news from the database by keyword")
    public List<ResponseNewsDto> getAllNewsByKeyword(@RequestParam String keyword, Pageable pageable) {
        return newsService.search(keyword, pageable);
    }

    /**
     * This method gets news identifier and tries to find news with using service layer. Also supporting pageable view
     * for list of comments inside news.
     * @param id - News identifier.
     * @return ResponseNewsDto - News representation.
     */
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get news from the database by id with some kind of sorting comments")
    public ResponseNewsDto getNews(@PathVariable Long id,
                                   @PageableDefault(size = 6)
                                   @SortDefault(sort = "news_created_at", direction = Sort.Direction.DESC)
                           Pageable pageable) {
        return newsService.getNews(id, pageable);
    }

    /**
     * This method gets NewsDto and tries to create news with using service layer.
     * @param newsDto - News information from request.
     * @return id - News identifier.
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create news with request body")
    public Long createNews(@RequestBody @Valid RequestNewsDto newsDto) {
        return newsService.createNews(newsDto);
    }

    /**
     * This method gets news identifier, NewsDto and tries to update news with using service layer.
     * @param id - News identifier.
     * @param requestNewsDto - News information from request.
     * @return id - News identifier.
     */
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update news by id and request body")
    public Long updateNews(@PathVariable Long id, @RequestBody @Valid RequestNewsDto requestNewsDto) {
        return newsService.updateNews(id, requestNewsDto);
    }

    /**
     * This method gets news identifier and tries to delete it with using service layer.
     * @param id - News identifier.
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete news by id")
    public void deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
    }


}
