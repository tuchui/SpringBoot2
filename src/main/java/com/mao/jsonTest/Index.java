package com.mao.jsonTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index {
    @RequestMapping("/bookIndex")
    public String bookIndex(){
        return "books";
    }
    @RequestMapping("/bookList")
    public String booksIndex(){
        return "booklist";
    }
}
