package com.aaron.mock.service;

import com.aaron.mock.bean.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/18.
 */
public class MockBookServiceImpl implements BookService  {

    @Override
    public List<Book> findBookByAuthor(String author) {
        List<Book> books = new ArrayList<>();

        if ("mkyong".equals(author)) {
            books.add(new Book("mkyong in action"));
            books.add(new Book("abc in action"));
            books.add(new Book("bot"));
        }

        return books;
    }
}
