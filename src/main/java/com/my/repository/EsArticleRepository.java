package com.my.repository;

import com.my.pojo.EsArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsArticleRepository extends ElasticsearchRepository<EsArticle,String> {


    Page<EsArticle> findEsArticlesByContentContaining(String content,Pageable pageable);


    Page<EsArticle> findEsArticlesByType(String type, Pageable pageable);
}
