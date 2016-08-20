package com.aaron.mock.service;

import com.aaron.mock.bean.Book;
import com.aaron.mock.dao.BookDao;


import java.util.List;

/**
 * Created by Administrator on 2016/8/18.
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> findBookByAuthor(String name) {
        return bookDao.findBookByAuthor(name);
    }

}
