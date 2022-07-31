package ru.clevertec.newsapi.repositories.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.clevertec.newsapi.entity.News;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    @Query(value = "SELECT * FROM news WHERE to_tsvector(title)|| to_tsvector(text) @@ plainto_tsquery(?1)", nativeQuery = true)
    List<News> search(String keyword);
}
