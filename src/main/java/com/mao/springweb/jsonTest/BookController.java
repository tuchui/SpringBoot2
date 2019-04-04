package com.mao.springweb.jsonTest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    @RequestMapping("/findBook")
    public Book findBook(@RequestBody Book book){
        System.out.println(book);
        book.setAuthor("newjack");
      //  book.setName("springcloud");
        return book;
    }
@RequestMapping("/findBookList")
    public List<Book> findBooklits(){
        List<Book> list=new ArrayList<>();
        list.add(new Book("mao","book"));
        list.add(new Book("mao1","book1"));
        list.add(new Book("mao2","book2"));
        list.add(new Book("mao3","book3"));
        list.add(new Book("mao4","book4"));
        return list;
}

}
