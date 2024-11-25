package org.sid.electromenager.dao;

import org.sid.electromenager.entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

public interface ArticleRepository extends JpaRepository<Article, Long>{
	Page<Article> findByNameContains(String mc, PageRequest pageable);


}
