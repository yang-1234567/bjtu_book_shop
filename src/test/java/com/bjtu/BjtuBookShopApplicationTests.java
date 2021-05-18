package com.bjtu;

import com.bjtu.bean.Book;
import com.bjtu.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BjtuBookShopApplicationTests {

    @Autowired
    BookMapper bookMapper;

    @Test
    void contextLoads() {

        Book book = bookMapper.selectById(1);
        System.out.println(book.getImgUrl());
    }

}
