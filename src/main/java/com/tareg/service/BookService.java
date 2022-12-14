package com.tareg.service;

import com.tareg.entity.Book;
import org.springframework.stereotype.Component;



@Component
public interface BookService {

    public Book saveBook(Book book);

    public Book findByBookId(int bookId);
}
