package ru.clevertec.newsapi.repository.news;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.clevertec.newsapi.entity.news.News;

public interface NewsRepository extends JpaRepository<News, Long> {
    @Query(value = "SELECT * FROM news WHERE to_tsvector(news_title)|| to_tsvector(news_text) @@ plainto_tsquery(?1)", nativeQuery = true)
    Page<News> search(String keyword, Pageable pageable);

    News findNewsByTitle(String title);

    News findNewsByText(String text);
}
