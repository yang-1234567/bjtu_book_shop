package com.bjtu.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.bjtu.bean.Book;
import com.bjtu.mapper.BookMapper;
import org.springframework.stereotype.Service;

/**
 * @Auther: jzhang
 * @Date: 2019/9/24 09:56
 * @Description: 图书业务层
 */
@Service
public class BookService extends ServiceImpl<BookMapper, Book> {
}
