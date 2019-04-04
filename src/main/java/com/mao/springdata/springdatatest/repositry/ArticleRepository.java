package com.mao.springdata.springdatatest.repositry;

import com.mao.springdata.springdatatest.bean.Article;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<Article,Integer> {
}
