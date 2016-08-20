package com.aaron.mock.service;

import com.aaron.mock.bean.Book;

/**
 * Created by Administrator on 2016/8/18.
 */
public class FakeBookValidatorService implements BookValidatorService{
    @Override
    public boolean isValid(Book book) {
        if (book == null)
            return false;

        if ("bot".equals(book.getName())) {
            return false;
        } else {
            return true;
        }
    }
}
