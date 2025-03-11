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

    public Book getBookById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Id must not be null");
        }
        return bookRepo.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void deleteBookById(Long id) {
        if (!bookRepo.existsById(id)) {
            throw new RuntimeException("Book not found with ID: " + id);
        }
        bookRepo.deleteById(id);
    }

    public Book updateBookById(Long id, Book newBookData) {
        if (id == null || newBookData == null || newBookData.getTitle() == null || newBookData.getAuthor() == null) {
            throw new IllegalArgumentException("Id, book title and author must not be null");
        }
        if (!bookRepo.existsById(id)) {
            throw new RuntimeException("Book not found with ID: " + id);
        }
        Book oldBookData = bookRepo.findById(id).get();
        oldBookData.setTitle(newBookData.getTitle());
        oldBookData.setAuthor(newBookData.getAuthor());
        return bookRepo.save(oldBookData);
    }
}
