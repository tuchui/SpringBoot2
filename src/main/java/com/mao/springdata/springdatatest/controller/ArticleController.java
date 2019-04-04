package com.mao.springdata.springdatatest.controller;

import com.mao.springdata.springdatatest.bean.Article;
import com.mao.springdata.springdatatest.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    /*
    指定排序参数对象：根据id，进行降序查询

     */
    @RequestMapping("/sort")
    public Iterable<Article> sortArticle(){
        Sort  sort=new Sort(Sort.Direction.DESC,"id");
        Iterable<Article> articles=articleService.findAndSort(sort);
        return articles;
    }

    @RequestMapping("/pager")
    public Iterable<Article> sortArticle(int pageIndex){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        /*
        封装分页实体
       第一个参数 pageIndex 当前查询的是第几页 默认从第0页开始
       第二个参数 表示每页展示多少数据
       第三参数 分装排序对象
         */
        Pageable page= PageRequest.of(pageIndex-1,2,sort);
        Page<Article> articleDatas=articleService.findAll(page);
        System.out.println("查询总页数: "+articleDatas.getTotalPages());
        System.out.println("查询总记录数: "+articleDatas.getTotalElements());
        System.out.println("查询当前第几页: "+articleDatas.getNumber()+1);
        System.out.println("查询当前页面记录数: "+articleDatas.getNumberOfElements());
        //查询数据集合
        List<Article> articles=articleDatas.getContent();
        System.out.println("查询当前页面集合："+articles);
        return articles;
    }
}

