package com.my.controller;


import com.my.pojo.EsArticle;
import com.my.repository.EsArticleRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@RequestMapping("/article")
@Controller
public class EsArticleController {

    @Autowired
    private EsArticleRepository esArticleRepository;

    @RequestMapping("/search")
    public ModelAndView search(String content,@PageableDefault(size = 6,page = 0) Pageable pageable){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.matchQuery("content",content)).withPageable(pageable);
        Page<EsArticle> esArticles = esArticleRepository.search(queryBuilder.build());
        ModelAndView mv = new ModelAndView();
        mv.addObject("articles",esArticles);
        mv.setViewName("list");
        return mv;
    }


    @RequestMapping("/preview/{id}")
    public ModelAndView preview(@PathVariable("id")String id){
        ModelAndView mv = new ModelAndView();
        Optional<EsArticle> esArticleOptional = esArticleRepository.findById(id);
        EsArticle esArticle = esArticleOptional.get();
        mv.addObject("article",esArticle);
        mv.setViewName("preview");
        return mv;
    }

    @RequestMapping("/save")
    public ModelAndView save(EsArticle article,ModelAndView mv){
        esArticleRepository.save(article);
        mv.setViewName("home");
        return mv;
    }

    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam(name = "id") String id , ModelAndView mv){
        esArticleRepository.deleteById(id);
        mv.setViewName("forward:manage");
        return mv;
    }


    @RequestMapping("/manage")
    public ModelAndView manage(@PageableDefault(size = 6,page = 0) Pageable pageable,ModelAndView mv){
        Page<EsArticle> articles = esArticleRepository.findAll(pageable);
        mv.addObject("articles",articles);
        mv.setViewName("manage");

        return mv;
    }


    @GetMapping("/list")
    public ModelAndView listType(@PageableDefault(size = 6,page = 0) Pageable pageable, @RequestParam(name = "type",required = false)String type){
        ModelAndView mv = new ModelAndView();
        if (type==null){
            Page<EsArticle> articles = esArticleRepository.findAll(pageable);
            mv.addObject("articles",articles);
            mv.setViewName("list");
        }else {
            Page<EsArticle> articles = esArticleRepository.findEsArticlesByType(type, pageable);
            mv.addObject("articles",articles);
            mv.addObject("type",type);
            mv.setViewName("typeList");
        };
        return mv;
    }

    @GetMapping("/write")
    public ModelAndView write(@RequestParam(name = "id")String id,ModelAndView mv){
        Optional<EsArticle> esArticle = esArticleRepository.findById(id);
        EsArticle article = esArticle.get();
        System.out.println(article);
        mv.addObject("article",article);
        mv.setViewName("editor");
        return mv;
    }

}
