package com.example.casse_tete.service;

import com.example.casse_tete.model.Book;
import com.example.casse_tete.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks(){
        List <Book> bookList = bookRepo.findAll();
        if(bookList.isEmpty()){
            throw new RuntimeException("No books found");
        }
        return bookList;
    }

    public Book postBook(Book book) {
        if (book == null || book.getTitle() == null || book.getAuthor() == null) {
            throw new IllegalArgumentException("Book title and author must not be null");
        }
        return bookRepo.save(book);
    }
}
