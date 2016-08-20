package com.aaron.mock.service;

import com.aaron.mock.bean.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/8/18.
 */
public class AuthorServiceTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getTotalBooks() throws Exception {
        //1. Setup
        AuthorServiceImpl obj = new AuthorServiceImpl();
        /*
        BookServiceImpl bookService = new BookServiceImpl();
        bookService.setBookDao(new BookDaoImpl()); //Where Dao connect to?
        obj.setBookService(bookService);
        */
        obj.setBookService(new MockBookServiceImpl());
        obj.setBookValidatorService(new FakeBookValidatorService());


        //2. Test method
        int qty = obj.getTotalBooks("mkyong");

        //3. Verify result
        assertThat(qty, is(2));
    }

    @Test
    public void test_total_book_by_mockito() {

        //1. Setup
        List<Book> books = Arrays.asList(
                new Book("mkyong in action"),
                new Book("abc in action"),
                new Book("bot"));

        BookServiceImpl mockito = mock(BookServiceImpl.class);

        //if the author is "mkyong", then return a 'books' object.
        when(mockito.findBookByAuthor("mkyong")).thenReturn(books);

        AuthorServiceImpl obj = new AuthorServiceImpl();
        obj.setBookService(mockito);
        obj.setBookValidatorService(new FakeBookValidatorService());

        //2. Test method
        int qty = obj.getTotalBooks("mkyong");

        //3. Verify result
        assertThat(qty, is(2));

    }

}