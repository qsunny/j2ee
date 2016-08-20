package com.aaron.mock.dao;


import com.aaron.mock.bean.Book;

import java.util.List;

/**
 * Created by Administrator on 2016/8/18.
 */
public interface BookDao {
    List<Book> findBookByAuthor(String author);
}
