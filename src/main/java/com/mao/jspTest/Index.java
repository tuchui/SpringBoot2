package com.mao.jspTest;

import com.mao.thymeleafTest.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Index {
    @RequestMapping("/jspTest")
    public String index(Model model){
        List<Book> booke=new ArrayList<>();
        booke.add(new Book("mao","mao"));
        booke.add(new Book("mao2","mao2"));
        booke.add(new Book("mao3","mao3"));
        model.addAttribute("books",booke);
        System.out.println("book");
        return "index";
    }
}
