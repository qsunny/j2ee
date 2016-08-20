package com.aaron.mock.service;


import com.aaron.mock.bean.Book;

import java.util.List;

/**
 * Created by Administrator on 2016/8/18.
 */
public interface BookService {
    List<Book> findBookByAuthor(String author);
}
